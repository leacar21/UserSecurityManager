package com.junolabs.usm.model;

import java.util.List;

public class Role extends GenericEntity {
	private String Code;
	private String Name;
	private System system;
	private List<Permission> permisioms;
	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public System getSystem() {
		return system;
	}
	public void setSystem(System system) {
		this.system = system;
	}
	public List<Permission> getPermisioms() {
		return permisioms;
	}
	public void setPermisioms(List<Permission> permisioms) {
		this.permisioms = permisioms;
	}

}
