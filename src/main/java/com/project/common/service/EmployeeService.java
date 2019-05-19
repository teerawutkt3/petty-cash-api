package com.project.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.common.entities.User;
import com.project.common.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	private UserRepository userRepository;

	public String getEmployeeId() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		String username = auth.getName();
		User user = userRepository.findByUsername(username);
		return user.getEmployee_id();
	}	
}
	