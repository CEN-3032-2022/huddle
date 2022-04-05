package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseRestarter {
	
	private Connection connect = null;
	
	public void dropAllTables() {
		try {
			PreparedStatement preparedStatement = connect.prepareStatement("DROP TABLE APP.USERS");
			preparedStatement.execute();
		}
		catch(SQLException e) { 
			System.out.println("No Tables To Drop");
		}
	}
	
	public void createAllTables() {
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

	public void restartDatabase() {
		connect = ConnectionFactory.getConnection();
		dropAllTables();
		createAllTables();
	}
	
}
