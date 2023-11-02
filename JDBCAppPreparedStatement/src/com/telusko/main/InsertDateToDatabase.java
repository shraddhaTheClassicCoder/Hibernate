package com.telusko.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.telusko.Utility.JdbcUtil;

public class InsertDateToDatabase {

	public static void main(String[] args) throws IOException, SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		// Establish Connection
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null) {
				// Create PreparedStatement object and sent and get precompile query from
				// database
				String SqlInsertQuery = "insert into user(uname,udob,udom) values(?,?,?)";
				pstmt = con.prepareStatement(SqlInsertQuery);

			}

			if (pstmt != null) {

				
				  Scanner scan = new Scanner(System.in);
				  System.out.println("Enter user name:"); String uname=scan.next();
				 
                
                //Enter the dob
                System.out.println("Enter user dob:");
                String udate=scan.next();
                
                //convert string date to util date
                SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
                Date sdate=sdf.parse(udate);
                
                long l=sdate.getTime();
                
                java.sql.Date sqlDate=new java.sql.Date(l);
                
                //Enter the dom
                System.out.println("Enter user dom:(yyyy-MM-dd)");
                String sqlmDate=scan.next();
                java.sql.Date domDate=java.sql.Date.valueOf(sqlmDate);
                
                
                //set the values
                pstmt.setString(1, uname);
                pstmt.setDate(2, sqlDate);
                pstmt.setDate(3, domDate);
                
                
                //Execute the Query
                int coutRow=pstmt.executeUpdate();
                System.out.println("No of rows affected:"+coutRow);
                
                
                
                
                
                
                
                
                
			
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
