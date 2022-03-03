package client_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		User user2 = new User(1, "epicninjapenguin", "123456", "I like ninjas and penguins");
		
		String correctParameterizedConstructorToString = "id: 1\n" +
				"username: epicninjapenguin\n" +
				"password: 123456\n" +
				"bio: I like ninjas and penguins\n";
		
		System.out.println(user2.toString());
		assertEquals(user2.toString(), correctParameterizedConstructorToString);
	}
	
	@Test
	public void testGetters() {
		//test getters
		User user1 = new User();

		
		System.out.println(user1.toString());
		assertEquals(user1.getId(), -1);
		assertEquals(user1.getUsername(), "unnamed user");
		assertEquals(user1.getPassword(), "password");
		assertEquals(user1.getBio(), "unwritten bio");
	}
	
	@Test
	public void testSetters() {
		//test setters
		User user1 = new User();
		
		user1.setId(2);
		user1.setUsername("hungry hungry hippo");
		user1.setPassword("abcdefg");
		user1.setBio("follow your hippo dreams");
		
		System.out.println(user1.toString());
		assertEquals(user1.getId(), 2);
		assertEquals(user1.getUsername(), "hungry hungry hippo");
		assertEquals(user1.getPassword(), "abcdefg");
		assertEquals(user1.getBio(), "follow your hippo dreams");
	}
	
	@Test
	public void testHonkRelatedFunctions() {
		//test honk related functions
		User user1 = new User();
		
		//test addHonk
		user1.addHonk(new Honk(1, "first honk", new Date(1, 1, 2001)));
		user1.addHonk(2, "second honk", new Date(2, 2, 2002));
		user1.addHonk(3, "third honk", 3, 3, 2003);
		
		Honk honk1 = new Honk(1, "first honk", 1, 1, 2001);
		Honk honk2 = new Honk(2, "second honk", 2, 2, 2002);
		Honk honk3 = new Honk(3, "third honk", 3, 3, 2003);
		
		//test getHonk
		System.out.println(user1.getHonk(0).toString());
		System.out.println(user1.getHonk(1).toString());
		System.out.println(user1.getHonk(2).toString());

		
		//test getHonks
		ArrayList<Honk> honks = user1.getHonks();
		int numHonks = user1.getNumHonks();
		
		for(int i = 0; i < numHonks; i++) {
			System.out.println(honks.get(i).toString());
		}
		
		assertEquals(honks.get(0).toString(), honk1.toString());
		assertEquals(honks.get(1).toString(), honk2.toString());
		assertEquals(honks.get(2).toString(), honk3.toString());
	}
}