package com.vkomlev.diploma.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.entities.SingleAction;
import com.vkomlev.diploma.stats.entities.SingleAction.ActionType;
import com.vkomlev.diploma.stats.entities.Task;

@Service
public class TestService {
    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);

    @Resource(name = "restClient")
    private EmployeeService service;

    @Value("${employee.user.name}")
    private String userName;

    @Value("${application.time.unit}")
    private String timeUnit;

    @Value("${employee.time.range}")
    private int timeRange;

    @Value("${employee.task.pattern}")
    private String employeeTaskPattern;

    private long msMultiplyer = 1000; // 1 second by default

    public void startTestService() {
        // init time...
        switch (timeUnit) {
            case "sec":
                msMultiplyer = 1000;
                break;
            case "min":
                msMultiplyer = 1000 * 60; // times will be in minutes
                break;
            case "hour":
                msMultiplyer = 1000 * 60 * 60; // times will be in hours
                break;
            default:
                msMultiplyer = 1000;
                break;
        }
        LOG.info("Every digit in config will be multiplied by {} milliseconds", msMultiplyer);

        LOG.info("Starting test with user name {}", userName);
        Employee employee = service.getEmployee(userName);
        if (employee == null) {
            LOG.info("Employee not found, creating new one...");
            Employee employeeToCreate = new Employee();
            employeeToCreate.setName(userName);
            employeeToCreate.setAge(23);
            employee = service.saveEmployee(employeeToCreate);
        }

        LOG.info("Continue with employee {}", employee);

        List<String> actionStrings = Arrays.asList(employeeTaskPattern.split("-"));
        List<Pair<SingleAction, Long>> workFlow = new ArrayList<>();
        for (String actionString : actionStrings) {
            String[] work = actionString.split(":");

            SingleAction action = new SingleAction();
            action.setPerformerEmployeeId(employee.getId());
            action.setType(ActionType.valueOf(work[0]));

            Long millsToExecute = msMultiplyer * Long.valueOf(work[1]);

            workFlow.add(Pair.of(action, millsToExecute));

        }
        int interation = 1;
        while (true) {
            Task task = new Task();
            task.setOwnerId(employee.getId());
            task.setTaskLabel(
                    employee.getName() + " task #" + interation + " " + new Timestamp(System.currentTimeMillis()));

            task.setTaskDetails(employee.getName() + " Is performing some task...\nThis is task #" + interation
                    + " wich is performed by this user...\nBy the way, today is "
                    + new Timestamp(System.currentTimeMillis()));
            task = service.createTask(task);

            LOG.info("Task created {}", task);

            for (Pair<SingleAction, Long> work : workFlow) {
                SingleAction singleAction = work.getFirst();
                singleAction.setParentTaskId(task.getId());
                long averageTimeToExecute = work.getSecond();

                long timeToExecute = ThreadLocalRandom.current().nextLong(
                        (averageTimeToExecute - timeRange * msMultiplyer),
                        (averageTimeToExecute + timeRange * msMultiplyer) + 1);

                LOG.info("Performing work {} estimated time in mills {} ...", singleAction.getType(),
                        averageTimeToExecute);
                long startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(timeToExecute);
                } catch (InterruptedException e) {
                }
                singleAction.setTimeTaken(new Long(System.currentTimeMillis() - startTime));
                singleAction = service.logWork(singleAction);
                LOG.info("Work {} completed!!! Time talen in mills {}", singleAction.getType(),
                        singleAction.getTimeTaken());
            }
            service.closeTask(task);
            LOG.info("Task {} Completed!!");
            interation++;
        }
    }
}
