package com.junolabs.usm.controllers.rest;

import com.junolabs.usm.support.RESTRequest;
import com.junolabs.usm.support.ResponseProccess;

public interface IRESTController {

	ResponseProccess proccessShow(RESTRequest restRequest);

	ResponseProccess proccessList(RESTRequest restRequest);

	ResponseProccess proccessCreate(RESTRequest restRequest);

	ResponseProccess proccessEdit(RESTRequest restRequest);

	ResponseProccess proccessEditValue(RESTRequest restRequest);

	ResponseProccess proccessDelete(RESTRequest restRequest);
}
