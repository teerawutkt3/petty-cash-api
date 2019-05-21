package com.project.common.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.common.persistence.entities.RoleAssignment;

public interface RoleAssignmentRepository extends CrudRepository<RoleAssignment, Integer> {
	
	List<RoleAssignment> findByUserId(Integer userId);
}
