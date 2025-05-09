package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DbUtil;

public class SystemDao {
	private Connection connection;
	
	public SystemDao() {
        connection = DbUtil.getConnection();
    }
	
	public String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public String getAlphaNumericString(int n) 
    { 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            int index = (int)(AlphaNumericString.length() * Math.random()); 
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
	public String getSalt(String username, String property) {
		String salt=null;
		PreparedStatement preparedStatement = null;
        try {
        	if (property.equals("Administrator")) {
                preparedStatement = connection.prepareStatement("select salt from admins where username=?");
        	} else if (property.equals("Seller")) {
                preparedStatement = connection.prepareStatement("select salt from sellers where username=?");
        	} else {
                preparedStatement = connection.prepareStatement("select salt from clients where username=?");
        	}
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	salt=rs.getString("salt");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salt;
	}
}
