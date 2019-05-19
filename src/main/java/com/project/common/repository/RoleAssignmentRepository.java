package com.project.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.common.entities.RoleAssignment;

public interface RoleAssignmentRepository extends CrudRepository<RoleAssignment, Integer> {
	
	List<RoleAssignment> findByUserId(Integer userId);
}
