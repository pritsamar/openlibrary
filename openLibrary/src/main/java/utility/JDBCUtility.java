package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utility.ConfigLoader.getConfig;

public class JDBCUtility {
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName(ConfigLoader.getConfig().getProperty("database.driver"));
			 con=DriverManager.getConnection(getConfig().getProperty("database.url"),
					getConfig().getProperty("database.username"),
					getConfig().getProperty("database.password")
					);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	
	 public static void closeConnection(Connection connection) {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (Exception e) {
	            }
	        }
	    }
	
//	public static void main(String[] args) {
//		getConnection();
//	}
}
