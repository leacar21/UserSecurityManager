package com.junolabs.usm.support.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.junolabs.usm.controllers.rest.IRESTController;
import com.junolabs.usm.controllers.rest.UserRESTController;
import com.junolabs.usm.support.HTTPMethod;
import com.junolabs.usm.support.ResponseProccess;

@WebServlet("/api/*")
public class DispatcherRESTServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PACKAGE = "com.junolabs.usm.controllers.rest.";
	private static final String REST_CONTROLLER = "RESTController";

	private static final String SHOW = "Show";
	private static final String LIST = "List";
	private static final String CREATE = "Create";
	private static final String EDIT = "Edit";
	private static final String EDIT_VALUE = "EditValue";
	private static final String DELETE = "Delete";

	//--------------------------------------------------------------------------------------------------
	
	private static final String RESOURCE_USER = "User";
	
	@Inject
	private UserRESTController userRESTController;
	
	private Map<String, IRESTController> mapControllers = new HashMap<String, IRESTController>();
	
	//--------------------------------------------------------------------------------------------------
	
	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

	public DispatcherRESTServlet() {
		super();
	}

	@Override
	public void init(){
		mapControllers.put(RESOURCE_USER, userRESTController);
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String httpVerb = request.getMethod();
		HTTPMethod httpMethod = HTTPMethod.valueOf(httpVerb);
		this.proccessRequest(httpMethod, request, response);
	}

	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

	private void proccessRequest(HTTPMethod httpMethod, HttpServletRequest request, HttpServletResponse response) {

		RESTRequest restRequest = this.getRESTRequest(request);

		try {
			Object pesponseProccess = this.callOperation(httpMethod, restRequest);

			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(out, pesponseProccess);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

	private RESTRequest getRESTRequest(HttpServletRequest request) {
		RESTRequest restRequest = new RESTRequest();

		Pattern regExAllPattern = Pattern.compile("/([a-z]*)");
		Pattern regExIdPattern = Pattern.compile("/([a-z]*)/([0-9]*)");

		// -------

		String pathInfo = request.getPathInfo();

		String resource = null;
		String id = null;

		// ---- RESOURCE ----

		Matcher matcher;
		matcher = regExAllPattern.matcher(pathInfo);
		if (matcher.find()) {
			resource = matcher.group(0);
			resource = resource.substring(1);
			resource = resource.substring(0, 1).toUpperCase() + resource.substring(1);
		}

		// ---- ID ----

		matcher = regExIdPattern.matcher(pathInfo);
		if (matcher.find()) {
			id = matcher.group(2);
		}

		// ---- JSON ----

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = request.getReader();
			restRequest.setJson(bufferedReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ---- QUERY PARAMS ----
		Map<String, String[]> params = request.getParameterMap();

		// -----------------------

		restRequest.setResource(resource);
		restRequest.setId(id);
		restRequest.setJson(bufferedReader);
		restRequest.setQueryParams(params);

		return restRequest;
	}

	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

	/**
	 * Seg�n httpMethod, llama a la operaci�n de render (o visualizacion de la
	 * p�gina) o a la de procesamiento
	 */
	private Object callOperation(HTTPMethod httpMethod, RESTRequest restRequest) {

		String methodName = getMethodName(httpMethod, restRequest);
		
		try {
			String className = PACKAGE + restRequest.getResource() + REST_CONTROLLER;
			Class<?> concreteClass = Class.forName(className);
			Object controller = mapControllers.get(restRequest.getResource()); //concreteClass.newInstance();
			Method method = concreteClass.getMethod(methodName, RESTRequest.class);
			Object responseProccess = (Object) (method.invoke(controller, restRequest));

			return responseProccess;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

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

	// --------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------

}
