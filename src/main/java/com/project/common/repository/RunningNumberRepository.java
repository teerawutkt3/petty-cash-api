package com.project.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.entities.RunningNumber;

public interface RunningNumberRepository extends CrudRepository<RunningNumber, Integer> {

	RunningNumber findByYear(String year);
}
