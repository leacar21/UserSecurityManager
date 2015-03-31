package com.junolabs.usm.controllers.rest;

import com.junolabs.usm.model.User;
import com.junolabs.usm.support.RESTRequest;
import com.junolabs.usm.support.ResponseProccess;

public class UserRESTController implements IRESTController {

	public ResponseProccess proccessShow(RESTRequest restRequest) {
		User user = new User();
		user.setFirstName("Leandro");
		user.setLastName("Carrasco");
		user.setEmail("carrascoleandro@gmail.com");

		ResponseProccess responseProccess = new ResponseProccess();
		responseProccess.setData(user);
		responseProccess.setErrorCode("");
		return responseProccess;
	}

	public ResponseProccess proccessList(RESTRequest restRequest) {
		return null;
	}

	public ResponseProccess proccessCreate(RESTRequest restRequest) {
		return null;
	}

	public ResponseProccess proccessEdit(RESTRequest restRequest) {
		return null;
	}

	public ResponseProccess proccessEditValue(RESTRequest restRequest) {
		return null;
	}

	public ResponseProccess proccessDelete(RESTRequest restRequest) {
		return null;
	}

}
