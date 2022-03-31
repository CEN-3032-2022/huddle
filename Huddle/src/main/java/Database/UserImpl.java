package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl {
	public static Connection connect = null;
	public static String insert = "INSERT INTO APP.USERS(username, password, biography) VALUES (?, ?, ?)";
	public static String readAll = "SELECT * FROM APP.USERS";
	
	
	public static String successMessage(String username) {
		String message = username + " was created successfully.";
		return message;
	}
	
	public static PreparedStatement createInsert() throws SQLException {
		connect = ConnectionFactory.getConnection();
		PreparedStatement prepareStatement = connect.prepareStatement(insert);
		return prepareStatement;
	}
	
	public static ResultSet createReadAll() throws SQLException {
		connect = ConnectionFactory.getConnection();
		PreparedStatement prepareStatement = connect.prepareStatement(readAll);
		ResultSet fetchResults = prepareStatement.executeQuery(readAll);
		return fetchResults;
		}
	
	public static void format(int id, String username, String password, String biography) {
		System.out.format("%s, %s, %s, %s\n", id, username, password, biography);
	}
	
}
