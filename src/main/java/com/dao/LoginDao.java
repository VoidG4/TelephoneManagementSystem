package com.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DbUtil;

public class LoginDao {
	Connection connection;
	SystemDao systemDao;
	static String err = "n/a", customerPhone = "n/a";
	
	public LoginDao() {
		connection = DbUtil.getConnection();
		systemDao = new SystemDao();
	}
	
	public int findUser(String username, String password, String property) {
		try {
			PreparedStatement statement = null;
			int exitStatus = 1;
	
			if (property.equals("Administrator")) { 
				statement = connection.prepareStatement("select username, password from phonebills_database.admins where username = ? and password = ?;");
				exitStatus = 0; 
			} else if (property.equals("Client")) { 
				statement = connection.prepareStatement("select username, password from phonebills_database.clients where username = ? and password = ?;");
				exitStatus = 2; 
			} else if (property.equals("Seller")) { 
				statement = connection.prepareStatement("select username, password from phonebills_database.sellers where username = ? and password = ?;"); 
				exitStatus = 3; 
			}
			password=password+systemDao.getSalt(username, property);
			MessageDigest digest;
			try {
					digest = MessageDigest.getInstance("SHA-1");
					byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
					password=systemDao.bytesToHex(encodedhash);	
			}catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				String dbUsername = result.getString("username");
	            String dbPassword = result.getString("password");
	            
	            if (username.equals(dbUsername) && password.equals(dbPassword)){
	                return exitStatus;
	            }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}
	
	public String giveErr() {
		return err;
	}
}
