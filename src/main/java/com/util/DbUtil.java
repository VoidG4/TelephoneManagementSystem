package com.util;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbUtil {
	static Connection connection = null;
	static DataSource datasrc = null;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				InitialContext ctx = new InitialContext();
				datasrc = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
				connection = datasrc.getConnection();
			} catch (Exception sqle) {
				sqle.printStackTrace();
			}
		}
		return connection;
	}
}
