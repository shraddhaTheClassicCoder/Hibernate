import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteApp {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;

		// Establish connection
		String url = "jdbc:mysql:///telusko";
		String username = "root";
		String password = "root";

		con = DriverManager.getConnection(url, username, password);
		System.out.println("Connection is established....");
		System.out.println("");

		// Create Statement object and execute query
		stmt = con.createStatement();

		System.out.println("Statement object is created....");
		System.out.println("");

		String UpdateQuery = "update student set sname='gita' where sid=22";

		int countrows = stmt.executeUpdate(UpdateQuery);

		System.out.println("No of rows affected:"+"\t"+ countrows);

		// release the resources
        System.out.println("Releasing Resources");
		stmt.close();
		con.close();
	}

}
