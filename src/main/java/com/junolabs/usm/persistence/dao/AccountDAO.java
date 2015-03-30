package com.junolabs.usm.persistence.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.Account;

public abstract class AccountDAO extends AbstractDAO {
	public abstract Account getById(long id);
	public abstract Account getByEmail(String email);
	public abstract Account getByAccountName(String email);
	public abstract List<Account> getAll(HttpServletRequest request);
	
	public abstract Account create(Account account);
	public abstract Account update(Account account);
	public abstract void delete(long id);
}
