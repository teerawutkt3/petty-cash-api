package com.project.common.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.persistence.entities.RunningNumber;

public interface RunningNumberRepository extends CrudRepository<RunningNumber, Integer> {

	RunningNumber findByYear(String year);
}
