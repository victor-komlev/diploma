package com.vkomlev.diploma.stats.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.exception.DuplicatedResultException;
import com.vkomlev.diploma.stats.services.MainStatsProcessingService;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private MainStatsProcessingService statsService;

    @Override
    public Employee getEmployee(String employeeId) {
        Employee result = null;
        if (employeeId != null) {
            result = statsService.getEmployeeById(employeeId);
        } else {
            LOG.warn("Id cannot bee null!");
        }
        return result;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws DuplicatedResultException {
        String guyId = statsService.addNewEmployee(employee);
        return statsService.getEmployeeById(guyId);
    }

    @Override
    public Employee getEmployeeTemplate() {
        Employee result = new Employee();
        result.setId(null);
        result.setName("name");
        result.setAge(0);
        return result;
    }

}
