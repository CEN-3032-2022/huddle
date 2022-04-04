package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	static String connectURL = "jdbc:derby:HuddleDB;";
	static String shutdownURL = "jdbc:derby:HuddleDB;shutdown=true";
	static Connection connect = null;
	static Connection shutdown = null;
	
	public static Connection getConnection() {
		
			try {
				connect = DriverManager.getConnection(connectURL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return connect;
	}
			
}
