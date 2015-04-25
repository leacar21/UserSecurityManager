package com.junolabs.usm.controllers.rest;

import com.junolabs.usm.support.rest.RESTRequest;

public interface IRESTController {

	Object proccessShow(RESTRequest restRequest);

	Object proccessList(RESTRequest restRequest);

	Object proccessCreate(RESTRequest restRequest);

	Object proccessEdit(RESTRequest restRequest);

	Object proccessEditValue(RESTRequest restRequest);

	Object proccessDelete(RESTRequest restRequest);
}
