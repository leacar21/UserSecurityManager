package com.junolabs.usm.exceptions;

import com.junolabs.usm.persistence.dao.mysql.support.PersistenceError;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private PersistenceError errorCode;
	
	public BusinessException(Exception exception){
		super(exception.getMessage());
		if (exception instanceof MySQLIntegrityConstraintViolationException){
			this.setErrorCode(PersistenceError.DUPLICATE_KEY);
		}
		else{
			this.setErrorCode(PersistenceError.DEFAULT);
		}
	}
	
	public BusinessException(String message, PersistenceError persistenceError){
		super(message);
		this.setErrorCode(persistenceError);
	}
	
	public PersistenceError getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(PersistenceError errorCode) {
		this.errorCode = errorCode;
	}

}
