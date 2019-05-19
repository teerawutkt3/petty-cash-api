package com.project.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.common.entities.Role;
import com.project.common.entities.RoleAssignment;
import com.project.common.repository.RoleAssignmentRepository;
import com.project.common.repository.RoleRepository;
import com.project.common.repository.UserRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleAssignmentRepository roleAssignment;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.project.common.entities.User user = userRepository.findByUsername(username);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		if (user == null) {
		      throw new UsernameNotFoundException("No user found for "+ username + ".");
		    }else  {
			List<RoleAssignment> roleAssignments = roleAssignment.findByUserId(user.getId());

			if (!roleAssignments.isEmpty()) {
				for (RoleAssignment roleAssign : roleAssignments) {

					Optional<Role> role = roleRepository.findById(roleAssign.getRoleId());
					authorities.add(new SimpleGrantedAuthority(role.get().getRoleName()));
				}
			}
		}

		User userDetail = new User(user.getUsername(), user.getPassword(), authorities); // "{noop}"+
		return userDetail;
	}

}
