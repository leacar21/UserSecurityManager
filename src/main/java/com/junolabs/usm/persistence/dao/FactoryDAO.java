package com.junolabs.usm.persistence.dao;

import com.junolabs.usm.persistence.dao.mysql.FactoryMySQLDAO;
import com.junolabs.usm.persistence.dao.postgres.FactoryPostgresDAO;

public abstract class FactoryDAO{
    
	private static final String POSTGRES = "Postgres";
	private static final String MYSQL = "MySQL";
	
	private static String DATA_BASE_MANAGER = MYSQL;
	
	public abstract IConnectionManager getConnectionManager();
    public abstract UserDAO getUserDAO();
    public abstract AccountDAO getAccountDAO();
    
    public static FactoryDAO getFactoryDAO() {
    	  
		switch (DATA_BASE_MANAGER) {
		  case MYSQL : 
			  return FactoryMySQLDAO.getInstance();
		  case POSTGRES : 
		      return FactoryPostgresDAO.getInstance();      
		  default : 
			  return FactoryMySQLDAO.getInstance();
		}
    }
    
}
