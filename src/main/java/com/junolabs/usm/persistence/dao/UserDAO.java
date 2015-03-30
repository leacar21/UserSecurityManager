package com.junolabs.usm.persistence.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.AbstractDAO;

public abstract class UserDAO extends AbstractDAO  {
	public abstract User getById(long id);
	public abstract User getByEmail(String email);
	public abstract User getByAccountName(String email);
	public abstract List<User> getAll();
	
	public abstract User create(User user) throws Exception;
	public abstract User update(User user);
	public abstract void delete(long id);
}
