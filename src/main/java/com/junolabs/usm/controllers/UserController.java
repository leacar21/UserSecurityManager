package com.junolabs.usm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.model.Account;
import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.mysql.support.PersistenceError;
import com.junolabs.usm.services.AccountManagerService;
import com.junolabs.usm.support.HTTPMethod;
import com.junolabs.usm.support.MVCRequest;
import com.junolabs.usm.support.ResponseProccess;

public class UserController extends CRUDController {
	
    public UserController() {
        super();
        System.out.println("constructor UserController");
    }

	@Override
	protected void renderShow(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("renderShow");
		System.out.println("==========");
		
		AccountManagerService accountManagerService = AccountManagerService.getInstance();
		
		User user = new User();
		user.setFirstName("Leandro");
		user.setLastName("Carrasco");
		user.setBirthDate(new Date());
		user.setEmail("a@b.com");
		
		Account account = new Account();
		account.setName("lcarrasco2");
		account.setPassword("abc123");
		account.setUser(user);
		
		try {
			accountManagerService.createAccount(account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void renderList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected void renderCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("renderShow");
		System.out.println("==========");
		
		try {
		
			AccountManagerService accountManagerService = AccountManagerService.getInstance();
			
			// --- USER ---
			
			String fistName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			String birthDateStr = request.getParameter("birthDate");			
			SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = textFormat.parse(birthDateStr);

			User user = new User();
			user.setFirstName(fistName);
			user.setLastName(lastName);
			user.setBirthDate(birthDate);
			user.setEmail(email);
			
			// --- ACCOUNT ---
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			Account account = new Account();
			account.setName(name);
			account.setPassword(password);
			account.setUser(user);
		
			accountManagerService.createAccount(account);
		} catch (BusinessException be) {
			if (be.getErrorCode() == PersistenceError.DUPLICATE_KEY){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void renderEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected void renderDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected ResponseProccess  proccessShow(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("SHOW PROCCESS");
		
		return null;
	}

	@Override
	protected ResponseProccess proccessList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("LIST PROCCESS");
		
		return null;
	}

	@Override
	protected ResponseProccess proccessCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		User user = new User();
		user.setFirstName("Lea");
		user.setLastName("Car");
		
		ResponseProccess responseProccess = new ResponseProccess();
		responseProccess.setErrorCode("");
		responseProccess.setData(user);
		return responseProccess;
	}

	@Override
	protected ResponseProccess  proccessEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("EDIT PROCCESS");
		
		return null;
	}

	@Override
	protected ResponseProccess  proccessDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("DELETE PROCCESS");

		return null;
	}
    
    public void particularMethod(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		printWriter.println("PARTICULAR METHOD");
    }

}
