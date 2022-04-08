package client_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;

import client.Date;
import client.Honk;
import client.User;

public class UserTest {

	@Test
	public void testConstructors() {
		//test default constructor
		User user1 = new User();
		
		String correctDefaultConstructorToString = "id: -1\n" +
				"username: unnamed user\n" +
				"password: password\n" +
				"bio: unwritten bio\n";
		
		System.out.println(user1.toString());
		assertEquals(user1.toString(), correctDefaultConstructorToString);
			
		//test parameterized constructor
		User user2 = new User(1, "epicninjapenguin", "I like ninjas and penguins");
		
		String correctParameterizedConstructorToString = "id: 1\n" +
				"username: epicninjapenguin\n" +
				"bio: I like ninjas and penguins\n";
		
		System.out.println(user2.toString());
		assertEquals(user2.toString(), correctParameterizedConstructorToString);
		String jsonT="{\"honks\":[{\"date\":\"1/1/11\",\"UserName\":\"Test1\",\"id\":1,\"content\":\"Hi\"}],\"password\":\"user1pw!\",\"bio\":\"test user with id 1\",\"id\":1,\"numHonks\":1,\"type\":\"userData\",\"username\":\"user1\"}";
		JSONObject x=new JSONObject(jsonT);
		User user3=new User(x);
	}
	
	@Test
	public void testGetters() {
		//test getters
		User user1 = new User();

		
		System.out.println(user1.toString());
		assertEquals(user1.getId(), -1);
		assertEquals(user1.getUsername(), "unnamed user");
		assertEquals(user1.getBio(), "unwritten bio");
	}
	
	@Test
	public void testSetters() {
		//test setters
		User user1 = new User();
		
		user1.setId(2);
		user1.setUsername("hungry hungry hippo");
		user1.setBio("follow your hippo dreams");
		
		System.out.println(user1.toString());
		assertEquals(user1.getId(), 2);
		assertEquals(user1.getUsername(), "hungry hungry hippo");
		assertEquals(user1.getBio(), "follow your hippo dreams");
	}
}