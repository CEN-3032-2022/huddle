package server_tests;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import client.ClientCommunication;
import client.Honk;

// Temporary class to ensure JUnit functionality works
// REMOVE LATER
public class ServerJUnitTest {
	
	@Test
	public void testTrue() {
		assert(true);
	}
	@Test
	public void HonkTest() {
//		String value = null;
//		ClientCommunication sut = new ClientCommunication();
//		JSONObject testJSON = new JSONObject();
//		testJSON.put("type", "HonkList");
//		testJSON.put("isTest", false);
//		sut.sendJSONRequestToServer(testJSON);
//		assert((value=sut.getServerHonkListJSONResponse())!=null);
//		System.out.println(value);
	}
	@Test
	public void testNotFalse() {
		assert(!false);
	}
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
}
