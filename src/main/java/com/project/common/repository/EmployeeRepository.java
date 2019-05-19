package com.project.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
