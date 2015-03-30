package com.junolabs.usm.persistence.dao.postgres;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.persistence.dao.ConnectionManager;

public class ConnectionManagerPostgres extends ConnectionManager {
	
	public ConnectionManagerPostgres(){
		
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "abc123");
	    
	    try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} 
	    
	    try {
			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usm", connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}	

}
