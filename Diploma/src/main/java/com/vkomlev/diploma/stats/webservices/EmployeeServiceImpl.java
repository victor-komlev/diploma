package com.vkomlev.diploma.stats.webservices;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.entities.SingleAction;
import com.vkomlev.diploma.stats.entities.SingleAction.ActionType;
import com.vkomlev.diploma.stats.entities.Task;
import com.vkomlev.diploma.stats.entities.Task.State;
import com.vkomlev.diploma.stats.exception.DuplicatedResultException;
import com.vkomlev.diploma.stats.exception.IncorrectTaskException;
import com.vkomlev.diploma.stats.exception.UserNotFoundException;
import com.vkomlev.diploma.stats.repositories.EmployeeRepository;
import com.vkomlev.diploma.stats.repositories.SingleActionRepository;
import com.vkomlev.diploma.stats.repositories.TaskRepository;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private SingleActionRepository singleActionRepo;

    @Override
    public Employee getEmployee(String employeeName) {
        Employee result = null;
        if (employeeName != null) {
            result = getEmployeeByName(employeeName);
        } else {
            LOG.warn("Id cannot bee null!");
        }
        return result;
    }

    private Employee getEmployeeByName(String employeeName) {
        return employeeRepo.getEmployeeByName(employeeName);
    }

    @Override
    public Employee saveEmployee(Employee employee) throws DuplicatedResultException {
        String guyId = addNewEmployee(employee);
        return getEmployeeById(guyId);
    }

    @Override
    public Task closeTask(Task task) throws IncorrectTaskException {
        if (task.getId() == null) {
            throw new IncorrectTaskException("No Id is set to the task");
        }
        Task taskToClose = taskRepo.getTaskById(task.getId());
        if (taskToClose == null) {
            throw new IncorrectTaskException("No task in DB " + task);
        }
        if (State.CLOSED.equals(taskToClose.getState())) {
            throw new IncorrectTaskException(task + " Is closed already!!!!");
        }
        taskToClose.setState(State.CLOSED);
        return taskRepo.save(taskToClose);
    }

    @Override
    public SingleAction logWork(SingleAction action) throws IncorrectTaskException, UserNotFoundException {
        if (action.getParentTaskId() == null) {
            throw new IncorrectTaskException("No task Id is set to the work");
        }
        Task targetTask = taskRepo.getTaskById(action.getParentTaskId());
        if (targetTask == null) {
            throw new IncorrectTaskException("No task in DB with ID " + action.getParentTaskId());
        }
        if (State.CLOSED.equals(targetTask.getState())) {
            throw new IncorrectTaskException(targetTask + " Is closed already!!!!");
        }
        if (action.getPerformerEmployeeId() == null
                || employeeRepo.getEmployeeById(action.getPerformerEmployeeId()) == null) {
            throw new UserNotFoundException("No user with ID " + action.getPerformerEmployeeId());
        }
        action.setId(UUID.randomUUID().toString());
        return singleActionRepo.save(action);
    }

    @Override
    public Task createTask(Task task) throws IncorrectTaskException, UserNotFoundException {
        if (task.getId() != null) {
            throw new IncorrectTaskException("You cannot save task again! Please, make sure that ID is null");
        }
        if (task.getOwnerId() == null || employeeRepo.getEmployeeById(task.getOwnerId()) == null) {
            throw new UserNotFoundException("No user with ID " + task.getOwnerId());
        }
        task.setState(Task.State.ACTIVE);
        task.setId(UUID.randomUUID().toString());
        task.setCreatedAt(System.currentTimeMillis());
        Task savedTask = taskRepo.save(task);
        return savedTask;
    }

    @Override
    public Employee getEmployeeTemplate() {
        Employee result = new Employee();
        result.setId(null);
        result.setName("name");
        result.setAge(0);
        return result;
    }

    @Override
    public Task getTaskTemplate() {
        Task task = new Task();
        task.setId(null);
        task.setOwnerId("OwnerId");
        task.setState(State.ACTIVE);
        task.setTaskLabel("Task Label");
        task.setTaskDetails("Task Details");
        return task;
    }

    @Override
    public SingleAction getSingleActionTemplate() {
        SingleAction singleAction = new SingleAction();
        singleAction.setId(null);
        singleAction.setParentTaskId("parentTaskId");
        singleAction.setPerformerEmployeeId("performerEmployeeId");
        singleAction.setTimeTaken(new Long(123456789L));
        singleAction.setType(ActionType.WORK);
        return singleAction;
    }

    private String addNewEmployee(Employee employee) throws DuplicatedResultException {
        if (employeeRepo.getEmployeeByName(employee.getName()) == null) {
            employee.setId(UUID.randomUUID().toString());
            Employee savedGuy = employeeRepo.save(employee);
            return savedGuy.getId();
        } else {
            throw new DuplicatedResultException("Employee " + employee.getName() + " already exist");
        }
    }

    private Employee getEmployeeById(String employeeId) {
        return employeeRepo.getEmployeeById(employeeId);
    }

}
