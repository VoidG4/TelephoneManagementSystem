package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Program;
import com.model.Seller;
import com.util.DbUtil;

import java.util.List;
import java.util.ArrayList;

public class SellerDao {
	List<Program> programs = new ArrayList<>();
	Connection connection;
	static String err = "n/a";
	
	public SellerDao() {
		connection = DbUtil.getConnection();
	}
	
	public int addSeller(Seller newSeller) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into phonebills_database.sellers (username, password, email, name, surname, admins_username, salt) values (?, ?, ?, ?, ?, ?, ?);");
			statement.setString(1, newSeller.getUsername());
			statement.setString(2, newSeller.getPassword());
			statement.setString(3, newSeller.getEmail());
			statement.setString(4, newSeller.getName());
			statement.setString(5, newSeller.getSurname());
			statement.setString(6, newSeller.getAdminUsername());
			statement.setString(7, newSeller.getSalt());
			statement.executeUpdate();
			
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int deleteSeller(String username) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from phonebills_database.sellers where username=?;");
			statement.setString(1, username);
			int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0 ? 0 : 1;
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
	}
	
	public int displayPrograms() {
		try {
		    programs.clear();
			PreparedStatement statement = connection.prepareStatement("select * from phonebills_database.programs;");
	        ResultSet resultSet = statement.executeQuery();
	        
			
			while (resultSet.next()) {
				int id = resultSet.getInt("idprograms");
				int speechTime = resultSet.getInt("speechTime");
				int smsNumber = resultSet.getInt("smsNumber");
				int dataNumber = resultSet.getInt("dataNumber");
				float cost = resultSet.getFloat("cost");
				String description = resultSet.getString("description");
				String nameOfProgram = resultSet.getString("name");
				int duration = resultSet.getInt("duration");
				
				Program newProgram = new Program(id, nameOfProgram, speechTime, smsNumber, dataNumber, cost, description, duration);
				programs.add(newProgram);
			}
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int MatchClientProgram(String number, int program) {
	    try {
	    	int number_id = 0;
	    	PreparedStatement selectStatement = connection.prepareStatement("select idnumbers from phonebills_database.numbers where phoneNumber=?;");
	    	selectStatement.setString(1, number);
	    	try(ResultSet resultSet = selectStatement.executeQuery()){
	    		if(resultSet.next()) {
	    			number_id = resultSet.getInt(1);
	    		}
	    	}
	    	
	    	PreparedStatement statement = connection.prepareStatement("insert into phonebills_database.number_has_programs (numbers_idnumbers, programs_idprograms) values (?,?);");
	    	statement.setInt(1, number_id);
	    	statement.setInt(2, program);
	    	statement.executeUpdate();
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 1;
	    }

	    return 0;
	}

	
	public String giveErr() {
		return err;
	}
	
	public List<Program> getPrograms(){
		return programs;
	}
}
