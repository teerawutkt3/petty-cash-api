package com.project.common.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserLoginUtil {

	static Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	public static String getUsername() {
		System.out.println(auth);
		return auth.getName();
	}

	public  static Collection<? extends GrantedAuthority> getRole() {
		return auth.getAuthorities();
	}
} 
