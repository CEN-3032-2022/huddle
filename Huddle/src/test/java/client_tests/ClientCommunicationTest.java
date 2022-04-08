package client_tests;

import org.json.JSONObject;
import org.junit.Test;

import client.ClientCommunication;
import client.User;

public class ClientCommunicationTest {
	
	// Must start the server before running tests
	
	public JSONObject getTestJSONRequest() {
		JSONObject testJSON = new JSONObject();
		testJSON.put("type", "testRequest");
		testJSON.put("isTest", true);
		return testJSON;
	}
	
	@Test
	public void sendingTestJSONToServer() {
//		JSONObject testJSON = getTestJSONRequest();
//		ClientCommunication.getInstance().sendJSONRequestToServer(testJSON);
//		JSONObject testJSONResponse = ClientCommunication.getInstance().getServerJSONResponse();
//		
//		System.out.println(testJSONResponse);
//		
//		assert(testJSONResponse.getString("type").equals("testResponse"));
//		assert(testJSONResponse.getBoolean("isTest"));
//		assert(testJSONResponse.getInt("number") == 12345);
//		assert(testJSONResponse.getJSONArray("array").getInt(0) == 0);
//		assert(testJSONResponse.getJSONArray("array").getInt(4) == 4);
//		assert(testJSONResponse.getJSONArray("array").getInt(9) == 9);
	}

	@Test
	public void sendingUserDataToServer() {
//		User testUser = new User(1, "user1", "user1pw!", "test user with id 1");
//		
//		JSONObject testUserJSON = new JSONObject(testUser);
//		testUserJSON.put("type", "userDataTest");
//		
//		assert(testUserJSON.get("id").equals(1));
//		assert(testUserJSON.get("numHonks").equals(0));
//		assert(testUserJSON.get("username").equals("user1"));
//		assert(testUserJSON.get("password").equals("user1pw!"));
//		assert(testUserJSON.get("bio").equals("test user with id 1"));
//		
//		ClientCommunication.getInstance().sendJSONRequestToServer(testUserJSON);
//		JSONObject testUserDataJSONResponse = ClientCommunication.getInstance().getServerJSONResponse();
//		
//		assert(testUserDataJSONResponse.getString("type").equals("userDataResponse"));
//		assert(testUserDataJSONResponse.getBoolean("isTest"));
	}
	
}
