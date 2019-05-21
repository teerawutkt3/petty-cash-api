package com.project.common.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.persistence.entities.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);
}
