package com.junolabs.usm.support;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.persistence.dao.IConnectionManager;
import com.junolabs.usm.persistence.dao.FactoryDAO;
import com.junolabs.usm.persistence.dao.TransactionManagerDAO;
import com.junolabs.usm.services.TransactionManagerService;

public class TransactionManager implements TransactionManagerService, TransactionManagerDAO  {
	
	
	private Map<Long, IConnectionManager> mapDAOManager;
	
	// --- Singleton ---
	
	private static TransactionManager INSTANCE = null;
	 
    private TransactionManager() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new TransactionManager();
            INSTANCE.mapDAOManager = new HashMap<Long, IConnectionManager>();
        }
        
        long idCurrentTread = Thread.currentThread().getId();
        
		if (!INSTANCE.mapDAOManager.containsKey(idCurrentTread)){		
			IConnectionManager connectionManager = FactoryDAO.getFactoryDAO().getConnectionManager();
			INSTANCE.mapDAOManager.put(idCurrentTread, connectionManager);
		}
		
    }
 
    public static TransactionManager getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---
    
    public void initTransaction() {
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		IConnectionManager connectionManager = this.mapDAOManager.get(idCurrentTread);
    		connectionManager.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				this.mapDAOManager.get(idCurrentTread).setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new BusinessException(e2);
			}
			throw new BusinessException(e1);
		}
    }
    
    // --- -------- ---
    
    public void commitTransaction(){
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
    	finally{
    		try {
				this.mapDAOManager.get(idCurrentTread).setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException(e);
			}
    	}
    }
    
    // --- -------- ---
    
    public void rollbackTransaction(){
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
    	finally{
    		try {
				this.mapDAOManager.get(idCurrentTread).setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException(e);
			}
    	}
    }
    
    // --- -------- ---
    
    public void finish(){
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).close();
			this.mapDAOManager.remove(idCurrentTread);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
    }

	@Override
	public IConnectionManager getConnectionManager() {
		long idCurrentTread = Thread.currentThread().getId();
		return this.mapDAOManager.get(idCurrentTread);
	}
    
    // --- -------- ---
}
