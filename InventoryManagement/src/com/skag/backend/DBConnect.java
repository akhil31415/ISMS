package com.skag.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	public static void intialize()
	{
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or " + "registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
	}
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String msAccDB = "db/ISMS.accdb";
	String dbURL = "jdbc:ucanaccess://" + msAccDB;


	public int updtable(String query) {
		int result=0;
		
		try {
			connection=DriverManager.getConnection(dbURL);
			statement=connection.createStatement();
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result ;		
	}

	public ResultSet select(String query) {
		
		try {
			connection=DriverManager.getConnection(dbURL);
			statement=connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet ;
		
	}
}
