package com.junolabs.usm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.junolabs.usm.support.HTTPMethod;
import com.junolabs.usm.support.MVCRequest;
import com.junolabs.usm.support.ResponseProccess;

public abstract class CRUDController {
	
	// Controlador generico para CRUD
	// Implementa el Patron Themplate Method
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	private static final String SHOW = "Show";
	private static final String LIST = "List";
	private static final String CREATE = "Create";
	private static final String EDIT = "Edit";
	private static final String DELETE = "Delete";
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	// Estas operaciones cargan parametros genericos que sirven para todos los CRUD de las entidades
	public void show(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, mvcRequest, SHOW);
    }
	
	public void list(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, mvcRequest, LIST);
    }
	
	public void create(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, mvcRequest, CREATE);
    }
	
	public void edit(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, mvcRequest, EDIT);
    }
	
	public void delete(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, mvcRequest, DELETE);
    }
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	// Las operaciones de renderizado deben retornar un HTML
	protected abstract void renderShow(HttpServletRequest request, HttpServletResponse response);
	protected abstract void renderList(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void renderCreate(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void renderEdit(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void renderDelete(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	// Las operaciones de procesamiento deben retornar un JSON
	protected abstract ResponseProccess proccessShow(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract ResponseProccess proccessList(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract ResponseProccess proccessCreate(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract ResponseProccess proccessEdit(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract ResponseProccess proccessDelete(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Según httpMethod, llama a la operación de render (o visualizacion de la página) o a la de procesamiento
	 */
	private void callFunction(HTTPMethod httpMethod, HttpServletRequest request, HttpServletResponse response, MVCRequest mvcRequest, String action){
		
		try {
			if (httpMethod == HTTPMethod.GET){
				//CRUDController.class.getMethods();

				//this.getClass().getDeclaredMethods();
				Method method = this.getClass().getDeclaredMethod("render" + action, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, request, response);
				
				forward(mvcRequest.getController(), mvcRequest.getAction(), request, response);
				
			} else if (httpMethod == HTTPMethod.POST){
				Method method = this.getClass().getDeclaredMethod("proccess" + action, HttpServletRequest.class, HttpServletResponse.class);
				Object jsonResponseObject = method.invoke(this, request, response);
				
				response.setContentType("application/json");   
				PrintWriter out = response.getWriter();
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.writeValue(out, jsonResponseObject);
				out.flush();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void forward(String controller, String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("controller", controller.toLowerCase());
		request.setAttribute("action", action);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/templates/crudTemplate.jsp");
		requestDispatcher.forward(request, response);
	}
}
