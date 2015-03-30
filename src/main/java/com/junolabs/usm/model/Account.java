package com.junolabs.usm.model;

import java.util.List;

public class Account extends GenericEntity {
	private String name;
	private String password;
	private User user;
	private List<Role> roles;
	private List<Permission> permisioms;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Permission> getPermisioms() {
		return permisioms;
	}
	public void setPermisioms(List<Permission> permisioms) {
		this.permisioms = permisioms;
	}

}
