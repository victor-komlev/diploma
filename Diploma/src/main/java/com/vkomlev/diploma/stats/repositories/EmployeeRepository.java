package com.vkomlev.diploma.stats.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vkomlev.diploma.stats.entities.Employee;

@Transactional
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee getEmployeeByName(String name);
}
