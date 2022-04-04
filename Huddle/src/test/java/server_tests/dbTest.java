package server_tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import Database.ConnectionFactory;
import Database.User;
import Database.UserAccess;
public class dbTest {
	@Test
	public void test1(){
	try {
	Connection connect = ConnectionFactory.getConnection();
		
	PreparedStatement preparedStatement = connect.prepareStatement("DROP TABLE APP.USERS");
	preparedStatement.execute();
		
		PreparedStatement preparedStatement2 = connect.prepareStatement("CREATE TABLE APP.USERS(ID INTEGER NOT NULL GENERATED "
				+ "ALWAYS AS IDENTITY,"
				+ " USERNAME VARCHAR(255), PASSWORD VARCHAR(255), BIOGRAPHY VARCHAR(255), PRIMARY KEY (ID))");
		preparedStatement2.execute();

	UserAccess userAccess = new UserAccess();
    User user = new User.UserBuilder("user2", "Password2")
           .userBiography("Bio2")
            .build();
    userAccess.createUser(user);
    userAccess.readAllUsers();
	}catch(SQLException e) { 
		System.out.print(e.getErrorCode()+"\n"+e.getCause());
	}
	}
}
