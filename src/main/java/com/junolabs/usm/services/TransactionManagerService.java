package com.junolabs.usm.services;

public interface TransactionManagerService {
	public void initTransaction();
    public void commitTransaction();
    public void rollbackTransaction();
    public void finish();
}
