package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;

import com.model.Bill;
import com.model.Call;
import com.model.Client;
import com.model.Program;
import com.util.DbUtil;

public class ClientDao {
	List<Program> programs = new ArrayList<>();
	List<Integer> programs_id = new ArrayList<>();
	List<LocalDate> dates = new ArrayList<>();
	List<Bill> bills = new ArrayList<>();
	List<Call> calls = new ArrayList<>();
	Connection connection;
	static String err = "n/a";
	
	public ClientDao() {
		connection = DbUtil.getConnection();
	}
	
	public int addClient(Client newClient) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into phonebills_database.clients (username, password, email, name, surname, afm , sellers_username, salt) values (?, ?, ?, ?, ?, ?, ?, ?);");
			statement.setString(1, newClient.getUsername());
			statement.setString(2, newClient.getPassword());
			statement.setString(3, newClient.getEmail());
			statement.setString(4, newClient.getName());
			statement.setString(5, newClient.getSurname());
			statement.setString(6, newClient.getAFM());
			statement.setString(7, newClient.getSellerUsername());
			statement.setString(8, newClient.getSalt());
			statement.executeUpdate();
			
			PreparedStatement statement2 = connection.prepareStatement("insert into phonebills_database.numbers (phoneNumber,clients_username) values (?,?);");
			statement2.setString(1, newClient.getPhoneNumber().toString());
			statement2.setString(2, newClient.getUsername());
			statement2.executeUpdate();

		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int deleteClient(String username) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from phonebills_database.clients where username=?;");
			statement.setString(1, username);
			int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0 ? 0 : 1;
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
	}
	
	public int displayClientsPrograms(String number) {
		try {
			int number_id = 0;
	    	PreparedStatement selectStatement = connection.prepareStatement("select idnumbers from phonebills_database.numbers where phoneNumber=?;");
	    	selectStatement.setString(1, number);
	    	try(ResultSet resultSet = selectStatement.executeQuery()){
	    		if(resultSet.next()) {
	    			number_id = resultSet.getInt(1);
	    		}
	    	}
	    	
	    	PreparedStatement selectStatement2 = connection.prepareStatement("select programs_idprograms, date from phonebills_database.number_has_programs where numbers_idnumbers=?;");
	    	selectStatement2.setInt(1, number_id);
	    	ResultSet resultSet = selectStatement2.executeQuery();
	    	
	    	programs_id.clear();
	    	while (resultSet.next()) {
	    		programs_id.add(resultSet.getInt(1));
	    		Timestamp timestamp = resultSet.getTimestamp("date");
	    		if (timestamp != null) {
                    LocalDate date = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dates.add(date);
                }
	    	}
	    	
	    	programs.clear();
	    	float total = 0;
	    	int counter = 0;
	    	for (int program_id : programs_id) {
	    		PreparedStatement statement = connection.prepareStatement("select * from phonebills_database.programs where idprograms = ?;");
		        statement.setInt(1, program_id);
		        
	    		ResultSet result = statement.executeQuery();
		        
				if (result.next()) {
					int id = result.getInt("idprograms");
					int speechTime = result.getInt("speechTime");
					int smsNumber = result.getInt("smsNumber");
					int dataNumber = result.getInt("dataNumber");
					float cost = result.getFloat("cost");
					String description = result.getString("description");
					String nameOfProgram = result.getString("name");
					int duration = result.getInt("duration");
					
					LocalDate currentDate = LocalDate.now();
					LocalDate thirtyDaysAgo = currentDate.minusDays(duration);
					
					if (!dates.get(counter).isBefore(thirtyDaysAgo)){
						Program newProgram = new Program(id, nameOfProgram, speechTime, smsNumber, dataNumber, cost, description, duration);
						programs.add(newProgram);
						total += cost;
					}
				}
				counter++;
	    	}
	    	
	    	Month month = dates.get(counter).getMonth();
			String monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			
			
			PreparedStatement checkStatement = connection.prepareStatement("select count(*) from phonebills_database.bills where month = ? and numbers_idnumbers = ?;");
			checkStatement.setString(1, monthName);
			checkStatement.setInt(2, number_id);

			ResultSet result = checkStatement.executeQuery();
			result.next();
			int count = result.getInt(1);

			if (count == 0) {
			    PreparedStatement insertStatement = connection.prepareStatement("insert into phonebills_database.bills (month, payment, numbers_idnumbers) values (?, ?, ?);");
			    insertStatement.setString(1, monthName);
			    insertStatement.setFloat(2, total);
			    insertStatement.setInt(3, number_id);
			    insertStatement.executeUpdate();
			} else {
				PreparedStatement updateStatement = connection.prepareStatement("update phonebills_database.bills set month=?, payment=? where numbers_idnumbers = ?;");
				updateStatement.setString(1, monthName);
				updateStatement.setFloat(2, total);
				updateStatement.setInt(3, number_id);
				updateStatement.executeUpdate();
			}

			
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int displayBill(String username) {
		try {
			int number_id = 0;
			PreparedStatement statement = connection.prepareStatement("select idnumbers from phonebills_database.numbers where clients_username = ?;");
			statement.setString(1, username);
			
			try(ResultSet resultSet = statement.executeQuery()){
	    		if (resultSet.next()) {
	    			number_id = resultSet.getInt(1);
	    		}
	    	}
			PreparedStatement statement2 = connection.prepareStatement("select idbills, month, payment from phonebills_database.bills where numbers_idnumbers = ?;");
			statement2.setInt(1, number_id);
			
			bills.clear();
			try(ResultSet resultSet = statement2.executeQuery()){
	    		while(resultSet.next()) {
	    			int id = resultSet.getInt(1);
	    			String month = resultSet.getString(2);
	    			float payment = Float.parseFloat(resultSet.getString(3));
	    			Bill bill = new Bill(id, month, payment);
	    			
	    			bills.add(bill);
	    		}
	    	}
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int callHistory(String username) {
		try {
			int number_id = 0;
			PreparedStatement statement = connection.prepareStatement("select idnumbers from phonebills_database.numbers where clients_username = ?;");
			statement.setString(1, username);
			
			try(ResultSet resultSet = statement.executeQuery()){
	    		if (resultSet.next()) {
	    			number_id = resultSet.getInt(1);
	    		}
	    	}
			
			PreparedStatement statement2 = connection.prepareStatement("select idcalls, duration, time from phonebills_database.calls where numbers_idnumbers = ?;");
			statement2.setInt(1, number_id);
			
			calls.clear();
			try(ResultSet resultSet = statement2.executeQuery()){
	    		while(resultSet.next()) {
	    			int id = resultSet.getInt(1);
	    			int duration = resultSet.getInt(2);
	    			Timestamp time = resultSet.getTimestamp(3);
	    			Call call = new Call(id, duration, time);
	    			
	    			calls.add(call);
	    		}
	    	}

		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int deleteBill(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from phonebills_database.bills where idbills = ?;");
			statement.setInt(1, id);
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
	
	public List<Program> getPrograms(){
		return programs;
	}
	
	public List<Bill> getBills(){
		return bills;
	}
	
	public List<Call> getCalls(){
		return calls;
	}
}
