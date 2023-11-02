import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

	public static void main(String[] args) {

		Connection connection = null;

		try {

			// Load and register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully....");

			// Establish connection with database
			String url = "jdbc:mysql://localhost:3306/telusko";
			String username = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established...");
			System.out.println("Connection class:" + connection.getClass().getName());

			// Creating the statement and execute the query

			String sqlQuery = "select sid,saddress,sage,sname from student";

			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(sqlQuery);
			System.out.println("Statement obj implementation class" + stmt.getClass().getName());
             System.out.println("");
			// process the Resultset
             System.out.println("SID"+"\t"+"Saddress"+"\t"+"Sage"+"\t"+"Sname");
			while (res.next()) {
				Integer Sid = res.getInt(1);
				String Saddress = res.getString(2);
				Integer Sage = res.getInt(3);
				String Sname = res.getString(4);

				
				System.out.println(Sid + "\t" + Saddress + "\t\t" +Sage + "\t" + Sname);
			}

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing connection
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
