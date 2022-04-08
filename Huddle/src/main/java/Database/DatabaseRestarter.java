package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseRestarter {
	
	private static Connection connect = null;
	
	public static void dropAllTables() {
		try {
			PreparedStatement preparedStatement = connect.prepareStatement("DROP TABLE APP.USERS");
			preparedStatement.execute();
		}
		catch(SQLException e) { 
			System.out.println("No Tables To Drop");
		}
	}
	
	public static void createAllTables() {
		try {
			PreparedStatement preparedStatement2 = connect.prepareStatement("CREATE TABLE APP.USERS"
					+ "(ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, "
					+ "USERNAME VARCHAR(255), PASSWORD VARCHAR(255), BIOGRAPHY VARCHAR(255), PRIMARY KEY (ID))");
			preparedStatement2.execute();
		}
		catch(SQLException e) {
			System.out.println(e.getErrorCode()+"\n"+e.getCause());
		}
	}

	public static void restartDatabase() {
		connect = ConnectionFactory.getConnection();
		DatabaseRestarter.dropAllTables();
		DatabaseRestarter.createAllTables();
	}
	
}
