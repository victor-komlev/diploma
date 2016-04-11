package com.vkomlev.diploma.stats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.exception.DuplicatedResultException;
import com.vkomlev.diploma.stats.repositories.EmployeeRepository;

@Service
public class MainStatsProcessingService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public String addNewEmployee(Employee employee) throws DuplicatedResultException {
        if (employeeRepo.getEmployeeByName(employee.getName()) == null) {
            Employee savedGuy = employeeRepo.save(employee);
            return savedGuy.getId();
        } else {
            throw new DuplicatedResultException("Employee " + employee.getName() + " already exist");
        }
    }

    public Employee getEmployeeById(String employeeId) {
        return employeeRepo.findOne(employeeId);
    }
}
