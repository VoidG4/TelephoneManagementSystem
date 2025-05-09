package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model.Administrator;
import com.util.DbUtil;

public class AdministratorDao {
	Connection connection;
	static String err = "n/a";
	
	public AdministratorDao() {
		connection = DbUtil.getConnection();
	}
	
	public int addAdministrator(Administrator newAdministrator) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into phonebills_database.admins (username, password, email, name, surname, salt) values (?, ?, ?, ?, ?, ?);");
			statement.setString(1, newAdministrator.getUsername());
			statement.setString(2, newAdministrator.getPassword());
			statement.setString(3, newAdministrator.getEmail());
			statement.setString(4, newAdministrator.getName());
			statement.setString(5, newAdministrator.getSurname());
			statement.setString(6, newAdministrator.getSalt());
			statement.executeUpdate();
			
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public String giveErr() {
		return err;
	}
}
