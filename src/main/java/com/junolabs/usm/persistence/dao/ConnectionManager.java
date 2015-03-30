package com.junolabs.usm.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionManager implements IConnectionManager {

	protected Connection connection;
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return connection.prepareStatement(sql, autoGeneratedKeys);
	}
	
	public Statement statement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}

	public void commit() throws SQLException {
		connection.commit();
	}

	public void rollback() throws SQLException {
		connection.rollback();
	}

	public void close() throws SQLException {
		connection.close();
	}

}