package com.vkomlev.diploma.stats.services;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mera.cs.eventprocessing.notification.INotificationPublisher;
import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.entities.SingleAction;
import com.vkomlev.diploma.stats.entities.SingleAction.ActionType;
import com.vkomlev.diploma.stats.entities.Task;
import com.vkomlev.diploma.stats.eventprocessing.message.CompanyBroadcastEvent;
import com.vkomlev.diploma.stats.eventprocessing.message.EmployeeBroadcastEvent;
import com.vkomlev.diploma.stats.repositories.EmployeeRepository;
import com.vkomlev.diploma.stats.repositories.SingleActionRepository;
import com.vkomlev.diploma.stats.repositories.TaskRepository;
import com.vkomlev.diploma.stats.types.ActionAverageTime;
import com.vkomlev.diploma.stats.types.EmployeesAverageActionTime;
import com.vkomlev.diploma.stats.types.EmployeesTaskTimeAverage;
import com.vkomlev.diploma.stats.types.TaskAverageTime;
import com.vkomlev.diploma.stats.types.TimePerTask;

@Service
@Transactional
public class MainStatsProcessingService {
    private static final Logger LOG = LoggerFactory.getLogger(MainStatsProcessingService.class);

    @Autowired
    private MongoTemplate mongo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private SingleActionRepository actionRepo;

    @Autowired
    private INotificationPublisher publisher;

    @Value("${task.ttl}")
    private long taskTimeToLeave;

    public TimePerTask getTimeForTask(String taskId) {
        TimePerTask result = null;
        try {
            Aggregation agg = newAggregation(match(Criteria.where("_id").is(taskId)),
                    lookup("singleAction", "_id", "parentTaskId", "action"), unwind("action"),
                    group("action.parentTaskId").sum("action.timeTaken").as("totalTime"));
            AggregationResults<TimePerTask> groupResults = mongo.aggregate(agg, Task.class, TimePerTask.class);
            result = groupResults.getUniqueMappedResult();
        } catch (Exception e) {
            LOG.error("", e);
        }
        return result;
    }

    public EmployeesAverageActionTime getEmployeesAverageTimeForActionType(String employeeId, String actionType) {
        EmployeesAverageActionTime result = null;
        try {
            Aggregation agg = newAggregation(
                    match(Criteria.where("type").is(actionType).and("performerEmployeeId").is(employeeId)),
                    lookup("employee", "performerEmployeeId", "_id", "employee"), unwind("employee"),
                    group("performerEmployeeId").avg("timeTaken").as("avgTime").first("employee.name")
                            .as("employeeName").first("type").as("actionType"));
            AggregationResults<EmployeesAverageActionTime> groupResults = mongo.aggregate(agg, SingleAction.class,
                    EmployeesAverageActionTime.class);
            result = groupResults.getUniqueMappedResult();
        } catch (Exception e) {
            LOG.error("", e);
        }
        return result;
    }

    public ActionAverageTime getAverageTimeForActionType(String actionType) {
        ActionAverageTime result = null;
        try {
            Aggregation agg = newAggregation(match(Criteria.where("type").is(actionType)),
                    group("type").avg("timeTaken").as("avgTime"));
            AggregationResults<ActionAverageTime> groupResults = mongo.aggregate(agg, SingleAction.class,
                    ActionAverageTime.class);
            result = groupResults.getUniqueMappedResult();
        } catch (Exception e) {
            LOG.error("", e);
        }
        return result;
    }

    public EmployeesTaskTimeAverage getEmployeesTaskAverageTime(String employeeId) {
        EmployeesTaskTimeAverage result = null;
        try {
            Aggregation agg = newAggregation(match(Criteria.where("ownerId").is(employeeId)),
                    lookup("singleAction", "_id", "parentTaskId", "action"), unwind("action"),
                    group("action.parentTaskId").sum("action.timeTaken").as("totalTime").first("ownerId").as("ownerId"),
                    lookup("employee", "ownerId", "_id", "employee"), unwind("employee"),
                    group("ownerId").avg("totalTime").as("avgTime").first("employee.name").as("employeeName"));
            AggregationResults<EmployeesTaskTimeAverage> groupResults = mongo.aggregate(agg, Task.class,
                    EmployeesTaskTimeAverage.class);
            result = groupResults.getUniqueMappedResult();
        } catch (Exception e) {
            LOG.error("", e);
        }
        return result;
    }

    public TaskAverageTime getAverageTimeForTask() {
        TaskAverageTime result = null;
        try {
            Aggregation agg = newAggregation(lookup("singleAction", "_id", "parentTaskId", "action"), unwind("action"),
                    group("action.parentTaskId").sum("action.timeTaken").as("totalTime"),
                    group().avg("totalTime").as("avgTime"));
            AggregationResults<TaskAverageTime> groupResults = mongo.aggregate(agg, Task.class, TaskAverageTime.class);
            result = groupResults.getUniqueMappedResult();
        } catch (Exception e) {
            LOG.error("", e);
        }
        return result;
    }

    @Scheduled(fixedDelay = 1000)
    public void publishNotifications() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeesAverageActionTime> perEmployeeActionTimes = new ArrayList<>();
        List<EmployeesTaskTimeAverage> perEmployeeTaskTimes = new ArrayList<>();
        EmployeeBroadcastEvent employeeEvent = new EmployeeBroadcastEvent();
        employeeEvent.setEmployeesAverageActionTime(perEmployeeActionTimes);
        employeeEvent.setEmployeesTaskTimeAverage(perEmployeeTaskTimes);
        for (Employee employee : employees) {
            for (ActionType actionType : Arrays.asList(ActionType.values())) {
                EmployeesAverageActionTime employeesAverageActionTime = getEmployeesAverageTimeForActionType(
                        employee.getId(), actionType.toString());
                if (employeesAverageActionTime != null) {
                    perEmployeeActionTimes.add(employeesAverageActionTime);
                }
            }
            EmployeesTaskTimeAverage employeesTaskTimeAverage = getEmployeesTaskAverageTime(employee.getId());
            if (employeesTaskTimeAverage != null) {
                perEmployeeTaskTimes.add(employeesTaskTimeAverage);
            }
        }
        LOG.info("Sending event to publisher : {}", employeeEvent);
        publisher.publishEvent(employeeEvent);

        CompanyBroadcastEvent companyEvent = new CompanyBroadcastEvent();
        companyEvent.setTaskAverageTime(getAverageTimeForTask());
        List<ActionAverageTime> actionAverageTimes = new ArrayList<>();
        companyEvent.setPerActionAverageTimes(actionAverageTimes);
        for (ActionType actionType : Arrays.asList(ActionType.values())) {
            ActionAverageTime actionAverageTime = getAverageTimeForActionType(actionType.toString());
            if (actionAverageTime != null) {
                actionAverageTimes.add(actionAverageTime);
            }
        }
        LOG.info("Sending event to publisher : {}", employeeEvent);
        publisher.publishEvent(companyEvent);
    }

    @Scheduled(fixedDelay = 1000)
    public void cleanUpTasks() {
        List<Task> tasksToDestroy = taskRepo.findByCreatedAtLessThan(System.currentTimeMillis() - taskTimeToLeave);
        for (Task task : tasksToDestroy) {
            if (task.getState().equals(Task.State.CLOSED)) {
                actionRepo.delete(actionRepo.getSingleActionByParentTaskId(task.getId()));
                taskRepo.delete(task);
            }
        }
    }
}
