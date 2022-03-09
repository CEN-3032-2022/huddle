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
		ClientCommunication sut = new ClientCommunication();
		
		JSONObject testJSON = getTestJSONRequest();
		sut.sendJSONRequestToServer(testJSON);
		JSONObject testJSONResponse = sut.getServerJSONResponse();
		
		assert(testJSONResponse.getString("type").equals("testResponse"));
		assert(testJSONResponse.getBoolean("isTest"));
		assert(testJSONResponse.getInt("number") == 12345);
		assert(testJSONResponse.getJSONArray("array").getInt(0) == 0);
		assert(testJSONResponse.getJSONArray("array").getInt(4) == 4);
		assert(testJSONResponse.getJSONArray("array").getInt(9) == 9);
		
		sut.closeCommunications();
	}

	@Test
	public void sendingUserDataToServer() {
		ClientCommunication sut = new ClientCommunication();
		User testUser = new User(1, "user1", "user1pw!", "test user with id 1");
		
		JSONObject testUserJSON = new JSONObject(testUser);
		testUserJSON.put("type", "userData");
		
		System.out.println(testUserJSON.toString());
		
		assert(testUserJSON.get("id").equals(1));
		assert(testUserJSON.get("numHonks").equals(0));
		assert(testUserJSON.get("username").equals("user1"));
		assert(testUserJSON.get("password").equals("user1pw!"));
		assert(testUserJSON.get("bio").equals("test user with id 1"));
		
		sut.sendJSONRequestToServer(testUserJSON);
		JSONObject testUserDataJSONResponse = sut.getServerJSONResponse();
				
		assert(testUserDataJSONResponse.getString("type").equals("userDataResponse"));
		assert(testUserDataJSONResponse.getBoolean("isTest"));
		sut.closeCommunications();
	}
	
}
