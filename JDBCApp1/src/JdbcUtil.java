
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	private JdbcUtil() {
		// Making constructor private to avoid the object creation
	}

	static {
		// Loading and Register the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static Connection getJDBCConnection() throws SQLException, IOException {

		FileInputStream fis = new FileInputStream("D:\\JDBC\\JDBCApp1\\src\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		// Establish connection with database
//		String url = "jdbc:mysql://localhost:3306/telusko";
//		String username = "root";
//		String password = "root";

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("username"), properties.getProperty("password"));
		System.out.println("Connection Established...");
		System.out.println("Connection class:" + connection.getClass().getName());

		return connection;
	}

	public static void cleanUp(Connection connection, ResultSet resultset, Statement statement) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultset != null) {
			try {
				resultset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
