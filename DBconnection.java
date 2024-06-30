package com.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	 public static Connection getConnection() throws SQLException {
	 return DriverManager.getConnection(URL, USERNAME, PASSWORD);
   }
}

	

