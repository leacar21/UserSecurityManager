package com.junolabs.usm.controllers.rest;

import com.junolabs.usm.model.User;
import com.junolabs.usm.support.rest.RESTRequest;


public class UserRESTController implements IRESTController {

	public Object proccessShow(RESTRequest restRequest) {
		User user = new User();
		user.setFirstName("Leandro");
		user.setLastName("Carrasco");
		user.setEmail("carrascoleandro@gmail.com");
		return user;
	}

	public Object proccessList(RESTRequest restRequest) {
		return null;
	}

	public Object proccessCreate(RESTRequest restRequest) {
		return null;
	}

	public Object proccessEdit(RESTRequest restRequest) {
		return null;
	}

	public Object proccessEditValue(RESTRequest restRequest) {
		return null;
	}

	public Object proccessDelete(RESTRequest restRequest) {
		return null;
	}

}
