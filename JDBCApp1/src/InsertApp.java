import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertApp {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		try {

			con = JdbcUtil.getJDBCConnection();

			if (con != null) {
				stmt = con.createStatement();
				System.out.println("Statement object is created");
			}

			if (stmt != null) {
				String selectQuery = "select sid,saddress,sage,sname from student";
				res = stmt.executeQuery(selectQuery);
				System.out.println("SID" + "\t" + "SADDRESS" + "SAGE" + "\t" + "SNAME");
				while (res.next()) {
					
					System.out.print(res.getInt(1)+res.getString(2) + "\t" + res.getInt(3) + "\t" + res.getString(4));
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Release the Resources

	}
}
