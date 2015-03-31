package com.junolabs.usm.controllers.rest;

import java.io.IOException;

import com.junolabs.usm.model.User;
import com.junolabs.usm.support.RESTRequest;
import com.junolabs.usm.support.ResponseProccess;

public class UserRESTController extends RESTController {

	@Override
	protected ResponseProccess proccessShow(RESTRequest restRequest) throws IOException {
		User user = new User();
		user.setFirstName("Leandro");
		user.setLastName("Carrasco");
		user.setEmail("carrascoleandro@gmail.com");

		ResponseProccess responseProccess = new ResponseProccess();
		responseProccess.setData(user);
		responseProccess.setErrorCode("");
		return responseProccess;
	}

	@Override
	protected ResponseProccess proccessList(RESTRequest restRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResponseProccess proccessCreate(RESTRequest restRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResponseProccess proccessEdit(RESTRequest restRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResponseProccess proccessEditValue(RESTRequest restRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResponseProccess proccessDelete(RESTRequest restRequest) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
