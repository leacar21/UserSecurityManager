package com.junolabs.usm.persistence.dao.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.IConnectionManager;
import com.junolabs.usm.persistence.dao.UserDAO;

public class UserPostgresDAO extends UserDAO {
	
	// --- Singleton ---
	
	private static UserPostgresDAO INSTANCE = null;
	 
    private UserPostgresDAO() {
    	super();
    }
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new UserPostgresDAO();
        }
    }
 
    public static UserPostgresDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---

	public User getById(long id) {
		// TODO Auto-generated method stub
		System.out.println("getById");
		
		return null;
	}

	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByAccountName(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public User create(User user) throws Exception {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"USERS\" (\"FIRST_NAME\", \"LAST_NAME\", \"EMAIL\", \"BIRTH_DATE\") VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setDate(4, new java.sql.Date(user.getBirthDate().getTime()));
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			while (rs.next()) {
			   generatedKey = rs.getInt(1);
			}
			user.setId(generatedKey);

			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}

	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
    
    // --- -------- ---
	    
	    
}
