package com.junolabs.usm.support;

import java.io.BufferedReader;
import java.util.Map;

public class RESTRequest {
	private String resource;
	private String id;
	private Map<String, String[]> queryParams;
	private BufferedReader json;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String[]> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, String[]> queryParams) {
		this.queryParams = queryParams;
	}

	public BufferedReader getJson() {
		return json;
	}

	public void setJson(BufferedReader json) {
		this.json = json;
	}

}
