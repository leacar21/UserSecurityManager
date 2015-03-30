package com.junolabs.usm.persistence.dao.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.persistence.dao.ConnectionManager;

public class ConnectionManagerMySQL extends ConnectionManager {
	
	public ConnectionManagerMySQL(){
		
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "root");
	    connectionProps.put("password", "abc123");
	    
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} 
	    
	    try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usm", connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

}
