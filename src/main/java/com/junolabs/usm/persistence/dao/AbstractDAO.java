package com.junolabs.usm.persistence.dao;

import com.junolabs.usm.support.TransactionManager;


public abstract class AbstractDAO {
	protected TransactionManagerDAO transactionManagerDAO;
	
	protected AbstractDAO() {
    	this.transactionManagerDAO = TransactionManager.getInstance();
    }
}
