package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserImpl {
	public static Connection connect = null;
	public static String insert = "INSERT INTO APP.USERS(username, password, biography) VALUES (?, ?, ?)";
	public static String readAll = "SELECT * FROM APP.USERS";
	
	
	public static void successMessage(User user) {
		String message = "SUCCESS: " + user.toString() + "\nsuccessfully stored.\n";
		System.out.println(message);
	}
	
	public static void createInsert(User user) throws SQLException {
		connect = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(insert);
		preparedStatement.setString(1, user.fetchUsername());
		preparedStatement.setString(2, user.fetchPassword());
		preparedStatement.setString(3, user.fetchBiography());
		preparedStatement.executeUpdate();
	}
	
	public static void createReadAll() throws SQLException {
		connect = ConnectionFactory.getConnection();
		PreparedStatement prepareStatement = connect.prepareStatement(readAll);
		ResultSet resultSet = prepareStatement.executeQuery();
		int increment = 1;
		while (resultSet.next()) 
		{
			int id = resultSet.getInt("id");
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String biography = resultSet.getString("biography");
			User user = new User.UserBuilder(username, password)
					.userBiography(biography)
					.userId(id)
					.build();
			System.out.println("\nuserid: " + id + user.toString());
			increment++;
		}
		connect.close();
		}
	
	public static void format(int id, String username, String password, String biography) {
		System.out.format("%s, %s, %s, %s\n", id, username, password, biography);
	}
	
}
