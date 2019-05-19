package com.project.common.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.entities.Employee;
import com.project.common.entities.User;
import com.project.common.repository.EmployeeRepository;
import com.project.common.repository.UserRepository;
import com.project.common.vo.UserProfileVo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/403")
	public String error403() {
//		String username = UserLoginUtil.getUsername();
//		Collection<? extends GrantedAuthority> role = UserLoginUtil.getRole();
		return "Error : 403 ! Username : ";
	}

	@GetMapping("/profile")
	public UserProfileVo profile() {
		UserProfileVo userProfile = new UserProfileVo();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Collection<? extends GrantedAuthority> role = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority r : role) {
			roles.add(r.getAuthority());
		}

		Employee employee = null;
		User user = userRepository.findByUsername(username);
		Optional<Employee> emp = employeeRepository.findById(Integer.valueOf(user.getEmployee_id()));
		if (emp.isPresent()) {
			employee = emp.get();
			userProfile.setName(employee.getFirshname() + " " + employee.getSurname());
			userProfile.setDepartment(employee.getDepartment());
		}

		userProfile.setUsername(username);
		userProfile.setRole(roles);
		return userProfile;
	}

}
