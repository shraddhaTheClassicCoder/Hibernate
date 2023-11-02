package com.ineuron.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestApp {

	public static void main(String[] args) {

		String configFile = "src\\com\\ineuron\\properties\\application.properties";
		HikariConfig config = new HikariConfig(configFile);

		try (HikariDataSource dataSource = new HikariDataSource(config)) {

			try {
				Connection connection = dataSource.getConnection();
				java.sql.Statement stmt = connection.createStatement();
				ResultSet resultSet = stmt.executeQuery("select sid,sname,sage,saddress from student");
				System.out.println("SID" + "\t" + "SNAME" + "\t" + "SAGE" + "\t" + "SADDRESS");
				if (resultSet != null) {
				 while(resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t"
								+ resultSet.getInt(3) + "\t" + resultSet.getString(4));
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
