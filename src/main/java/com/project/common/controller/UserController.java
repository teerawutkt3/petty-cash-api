package com.project.common.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.utils.UserLoginUtil;
import com.project.common.vo.UserProfileVo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@GetMapping("/403")
	public String error403() {
		String username = UserLoginUtil.getUsername();
		Collection<? extends GrantedAuthority> role = UserLoginUtil.getRole();
		return "Error : 403 ! Username : " + username + " Role : " + role.toString();
	}

	@GetMapping("/profile")
	public UserProfileVo profile() {
		UserProfileVo userProfile = new UserProfileVo();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		String username = auth.getName();
		Collection<? extends GrantedAuthority> role = UserLoginUtil.getRole();

		userProfile.setUsername(username);
		userProfile.setRole(role);
		return userProfile;
	}

}
