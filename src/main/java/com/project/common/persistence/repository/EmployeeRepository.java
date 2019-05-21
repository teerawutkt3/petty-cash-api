package com.project.common.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.persistence.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
