package com.telusko.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class InsertImageToDatabase {

	public static void main(String[] args) throws IOException, SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		int id=1;

		// Establish Connection
		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null) {
				// Create PreparedStatement object and sent and get precompile query from
				// database
				String SqlInsertQuery = "select id,name,image from person where id=?";
				pstmt = con.prepareStatement(SqlInsertQuery);

			}

			if (pstmt != null) {
				pstmt.setInt(1, id);
				// Execute Query
				 res = pstmt.executeQuery();

			}

			if (res != null) {

				if (res.next()) {
					System.out.println("ID" + "\t" + "Name" + "\t" + "Image");
					int sid = res.getInt(1);
					String sname = res.getString(2);
					InputStream i = res.getBinaryStream(3);

					File f = new File("Shraddha.jpg");
					FileOutputStream fos = new FileOutputStream(f);

					IOUtils.copy(i, fos);

					fos.close();

					System.out.println(sid + "\t" + sname + "\t" + f.getAbsolutePath());

				} else {
					System.out.println("Record is not available for given id");
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
