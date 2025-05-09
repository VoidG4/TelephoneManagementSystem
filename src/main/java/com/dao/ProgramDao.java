package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model.Program;
import com.util.DbUtil;

public class ProgramDao {
	Connection connection;
	static String err = "n/a";
	
	public ProgramDao() {
		connection = DbUtil.getConnection();
	}
	
	public int addUpdateProgram(Program newProgram, String action) {
		try {
			PreparedStatement statement = null;
			if(action.equals("addProgram")) {
				statement = connection.prepareStatement("insert into phonebills_database.programs (speechTime, smsNumber, dataNumber, cost, description, duration, name) values (?, ?, ?, ?, ?, ?, ?);");
			} else if(action.equals("updateProgram")){
				statement = connection.prepareStatement("update phonebills_database.programs set speechTime=?, smsNumber=?, dataNumber=?, cost=?, description=?, duration=? where name=?;");
			}
			statement.setInt(1, newProgram.getSpeechTime());
			statement.setInt(2, newProgram.getSmsNumber());
			statement.setInt(3, newProgram.getDataNumber());
			statement.setFloat(4, newProgram.getCost());
			statement.setString(5, newProgram.getDescription());
			statement.setInt(6, newProgram.getDuration());
			statement.setString(7, newProgram.getNameOfProgram());
			statement.executeUpdate();
			
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int deleteProgram(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from phonebills_database.programs where name=?;");
			statement.setString(1, name);
			int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0 ? 0 : 1;
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
	}
	
	public String giveErr() {
		return err;
	}
}
