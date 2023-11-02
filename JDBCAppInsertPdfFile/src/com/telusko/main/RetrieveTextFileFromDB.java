package com.telusko.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.telusko.Utility.JdbcUtil;

public class RetrieveTextFileFromDB {

	public static void main(String[] args) throws IOException, SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		int id = 4;
		// Establish Connection
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null) {
				// Create PreparedStatement object and sent and get precompile query from
				// database
				String SqlSelectQuery = "select id,name,history from cities where id = ?";
				pstmt = con.prepareStatement(SqlSelectQuery);

			}

			if (pstmt != null) {

				pstmt.setInt(1, id);
			}

			// execute Query
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {

				if (rs.next()) {
					System.out.println("ID" + "\t" + "Name" + "\t" + "History");
					String sid=rs.getString(1);
					String sname=rs.getString(2);
					Reader reader=rs.getCharacterStream(3);
					
					//check file exist or not 
					File f=new File("History.txt");
					
					//FileWriter is used to write data into the file
					FileWriter fw=new FileWriter(f);
					
					IOUtils.copy(reader,fw);
					
					System.out.println(sid+"\t"+sname+"\t"+f.getAbsolutePath());
					
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
