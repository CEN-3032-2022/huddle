package server_tests;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import client.ClientCommunication;
import client.Honk;
import client.User;
import client.UserRepositoryImp;

// Temporary class to ensure JUnit functionality works
// REMOVE LATER
public class ServerJUnitTest {
	
	@Test
	public void testFollow() {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "user");
		JSON.put("request", "followUser");
		JSON.put("userFollowing","Bmw54");
		JSON.put("userToFollow", "benjamin");
		ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
		JSONObject x = ClientCommunication.getInstance().getServerJSONResponse();
		boolean y = x.getBoolean("isSuccess");
		assert(y==true);
	}
	@Test
	public void testSearchUsr() {
		UserRepositoryImp x = new UserRepositoryImp();
		User usr = x.getUserByUsername("Bmw54");
		assert(usr.getUsername().equals("Bmw54"));
	}
	@Test
	public void testLike() {
		Honk honk1 = new Honk();
		
		honk1.setNumLikes(honk1.getNumLikes()+1);
		
		assert(honk1.getNumLikes() == 1);
	}
}
