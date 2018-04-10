package ist.barline.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConfig {
	private static Connection con;
	
	public Connection getConnection() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "root", "root");
			System.out.println("Connection successful");
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("connection failed");
		}
		return con;
	}
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
			System.out.println("connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
