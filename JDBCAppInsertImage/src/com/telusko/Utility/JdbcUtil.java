package com.telusko.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	private JdbcUtil() {
		// To avoid object creation made the constructor private
	}

	static {
		// Load and Register Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Suceessfully");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws IOException, SQLException {

		// Load the Data from property file
		FileInputStream fis = new FileInputStream("src\\com\\telusko\\property\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("username"), properties.getProperty("password"));

		return connection;

	}

	public static void cleanUp(Connection connection, PreparedStatement pstmt) {

		try {
			if (connection != null) {
				connection.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
