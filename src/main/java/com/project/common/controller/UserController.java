package com.project.common.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.vo.UserProfileVo;

@RestController
@RequestMapping("/api/user")
public class UserController {

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

		userProfile.setUsername(username);
		userProfile.setRole(roles);
		return userProfile;
	}

}
