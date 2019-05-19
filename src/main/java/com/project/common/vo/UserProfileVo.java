package com.project.common.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserProfileVo {

	private String username;
	private Collection<? extends GrantedAuthority> role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getRole() {
		return role;
	}

	public void setRole(Collection<? extends GrantedAuthority> role) {
		this.role = role;
	}

}
