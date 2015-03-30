package com.junolabs.usm.model;

import java.util.List;

public class Option extends GenericEntity {
	private String name;
	private System system;
	private List<AccessType> accessTypeEnableds;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public System getSystem() {
		return system;
	}
	public void setSystem(System system) {
		this.system = system;
	}
	public List<AccessType> getAccessTypeEnableds() {
		return accessTypeEnableds;
	}
	public void setAccessTypeEnableds(List<AccessType> accessTypeEnableds) {
		this.accessTypeEnableds = accessTypeEnableds;
	}

}
