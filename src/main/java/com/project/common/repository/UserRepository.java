package com.project.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.common.entities.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);
}
