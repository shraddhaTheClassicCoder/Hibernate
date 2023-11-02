package com.telusko.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.telusko.Utility.JdbcUtil;

public class InsertTextFileToDatabase {

	public static void main(String[] args) throws IOException, SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		int id = 1;

		// Establish Connection
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null) {
				// Create PreparedStatement object and sent and get precompile query from
				// database
				String SqlInsertQuery = "insert into cities(name,history) values(?,?)";
				pstmt = con.prepareStatement(SqlInsertQuery);

			}

			if (pstmt != null) {

				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter the name of city:");
				String name = scanner.next();

				System.out.print("Enter the history of city:(Enter the imageLoc)");
				String imageLoc = scanner.next();

				pstmt.setString(1, name);
				pstmt.setCharacterStream(2, new FileReader(new File(imageLoc)));

				// Execute Query
				int count = pstmt.executeUpdate();

				System.out.println("Image inserted Successfully");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
