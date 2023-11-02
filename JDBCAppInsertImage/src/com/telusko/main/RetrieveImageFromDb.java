package com.telusko.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.telusko.Utility.JdbcUtil;

public class RetrieveImageFromDb {

	public static void main(String[] args) throws IOException, SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		// Establish Connection
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null) {
				// Create PreparedStatement object and sent and get precompile query from
				// database
				String SqlInsertQuery = "insert into person(name,image) values(?,?)";
				pstmt = con.prepareStatement(SqlInsertQuery);

			}

			if (pstmt != null) {

				Scanner scan = new Scanner(System.in);
				System.out.println("Enter user name:");
				String name = scan.next();

				// Enter the image
				System.out.println("Enter user image:");
				String imageLoc = scan.next();

				// set the values
				pstmt.setString(1, name);
				pstmt.setBinaryStream(2, new FileInputStream(imageLoc));

				// Execute the Query
				int rowAffected = pstmt.executeUpdate();

				System.out.println("No of row inserted " + rowAffected);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
