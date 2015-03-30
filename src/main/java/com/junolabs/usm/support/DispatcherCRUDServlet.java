package com.junolabs.usm.support;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/crud/*")
public class DispatcherCRUDServlet extends HttpServlet {
	
	private static final String PACKAGE = "com.junolabs.usm.controllers.";
	private static final String CONTROLLER = "Controller";
	
	private static final long serialVersionUID = 1L;
       
    public DispatcherCRUDServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proccessRequest(HTTPMethod.GET, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.proccessRequest(HTTPMethod.POST, request, response);
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	
	private void proccessRequest(HTTPMethod httpMethod, HttpServletRequest request, HttpServletResponse response){
		
		
		MVCRequest mvcRequest = this.getMVCRequest(request);
		
			try {
				String className = PACKAGE + mvcRequest.getController() + CONTROLLER;
				Class<?> concreteClass = Class.forName(className);
				Object controller = concreteClass.newInstance();
				Method method = concreteClass.getMethod(mvcRequest.getAction(), HTTPMethod.class, MVCRequest.class, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(controller, httpMethod, mvcRequest, request, response);	
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	
	private MVCRequest getMVCRequest(HttpServletRequest request){
		MVCRequest mvcRequest = new MVCRequest();
		
		//-------
		
		String path = request.getPathInfo();
		 
		Pattern patron = Pattern.compile("/(.*)/(.*)"); // Se parsea "/controller_name/action_name"
		Matcher matcher = patron.matcher(path);

		matcher.find();

		String controller = matcher.group(1);
		String action = matcher.group(2);
		
		controller = controller.substring(0, 1).toUpperCase() + controller.substring(1);
	
		//-------
		
		mvcRequest.setController(controller);
		mvcRequest.setAction(action);
		
		return mvcRequest;
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------
	


}
