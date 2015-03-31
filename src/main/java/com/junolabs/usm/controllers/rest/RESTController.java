package com.junolabs.usm.controllers.rest;

import java.io.IOException;
import java.lang.reflect.Method;

import com.junolabs.usm.support.HTTPMethod;
import com.junolabs.usm.support.RESTRequest;
import com.junolabs.usm.support.ResponseProccess;

public abstract class RESTController {

	// Controlador generico para REST
	// Implementa el Patron Themplate Method

	// -------------------------------------------------------------------------------------------------------------------------

	private static final String SHOW = "Show";
	private static final String LIST = "List";
	private static final String CREATE = "Create";
	private static final String EDIT = "Edit";
	private static final String EDIT_VALUE = "EditValue";
	private static final String DELETE = "Delete";

	// -------------------------------------------------------------------------------------------------------------------------

	// Las operaciones de procesamiento deben retornar un JSON
	protected abstract ResponseProccess proccessShow(RESTRequest restRequest) throws IOException;

	protected abstract ResponseProccess proccessList(RESTRequest restRequest) throws IOException;

	protected abstract ResponseProccess proccessCreate(RESTRequest restRequest) throws IOException;

	protected abstract ResponseProccess proccessEdit(RESTRequest restRequest) throws IOException;

	protected abstract ResponseProccess proccessEditValue(RESTRequest restRequest) throws IOException;

	protected abstract ResponseProccess proccessDelete(RESTRequest restRequest) throws IOException;

	// -------------------------------------------------------------------------------------------------------------------------

	/**
	 * Según httpMethod, llama a la operación de render (o visualizacion de la
	 * página) o a la de procesamiento
	 */
	public ResponseProccess callOperation(HTTPMethod httpMethod, RESTRequest restRequest) {

		String methodName = getMethodName(httpMethod, restRequest);

		try {
			Method method = this.getClass().getDeclaredMethod(methodName, RESTRequest.class);
			ResponseProccess responseProccess = (ResponseProccess) (method.invoke(this, restRequest));

			return responseProccess;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retorna el nombre del metodo a llamar
	 */
	private String getMethodName(HTTPMethod httpMethod, RESTRequest restRequest) {

		String methodName = "proccess";

		if (httpMethod.equals(HTTPMethod.GET)) {
			if (restRequest.getId() == null) {
				methodName = methodName + LIST;
			} else {
				methodName = methodName + SHOW;
			}
		} else if (httpMethod.equals(HTTPMethod.POST)) {
			methodName = methodName + CREATE;
		} else if (httpMethod.equals(HTTPMethod.PUT)) {
			methodName = methodName + EDIT;
		} else if (httpMethod.equals(HTTPMethod.PATCH)) {
			methodName = methodName + EDIT_VALUE;
		} else if (httpMethod.equals(HTTPMethod.DELETE)) {
			methodName = methodName + DELETE;
		}

		return methodName;
	}
}
