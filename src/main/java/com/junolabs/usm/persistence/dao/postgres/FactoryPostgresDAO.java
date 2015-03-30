package com.junolabs.usm.persistence.dao.postgres;

import com.junolabs.usm.persistence.dao.AccountDAO;
import com.junolabs.usm.persistence.dao.IConnectionManager;
import com.junolabs.usm.persistence.dao.FactoryDAO;
import com.junolabs.usm.persistence.dao.UserDAO;

public class FactoryPostgresDAO extends FactoryDAO {
	
	// --- Singleton ---
	private static FactoryPostgresDAO INSTANCE = null;
	 
    private FactoryPostgresDAO() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new FactoryPostgresDAO();
        }
    }
 
    public static FactoryDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
    // --- -------- ---
    
	public IConnectionManager getConnectionManager() {
		return new ConnectionManagerPostgres();
	}
    
	public UserDAO getUserDAO() {
		return UserPostgresDAO.getInstance();
	}

	public AccountDAO getAccountDAO() {
		return AccountPostgresDAO.getInstance();
	}
	
}
