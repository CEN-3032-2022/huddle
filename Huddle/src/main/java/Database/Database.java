package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public static void main(String[] args) {
	String jdbcURL = "jdbc:derby:HuddleDB;";
	
	try {
		Connection connection = DriverManager.getConnection(jdbcURL);
		String createTable = "";
		
		Statement statement = connection.createStatement();
		statement.executeUpdate(createTable);
		
		System.out.println("Table created.");
	} catch (SQLException e) {
		e.printStackTrace();
		  }
	
	}
	
}

