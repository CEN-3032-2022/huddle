package server_tests;

import org.junit.Test;

import Database.DatabaseRestarter;
import Database.User;
import Database.UserAccess;

public class dbTest {
	@Test
	public void testRestartingDatabase(){

		DatabaseRestarter dbRestarter = new DatabaseRestarter();
		dbRestarter.restartDatabase();
		
		UserAccess userAccess = new UserAccess();
		User user = new User.UserBuilder("testUser", "TestPassword1")
				.userBiography("I am a user created from a test")
				.build();
		userAccess.createUser(user);
		userAccess.readAllUsers();
	}
}
