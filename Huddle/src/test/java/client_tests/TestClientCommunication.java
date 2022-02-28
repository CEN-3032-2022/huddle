package client_tests;

import org.json.JSONObject;
import org.junit.Test;

import client.ClientCommunication;

public class TestClientCommunication {
	
	// Must start the server before running tests
	
	@Test
	public void testJSON() {
		ClientCommunication sut = new ClientCommunication();
		
		sut.sendTestJSON();
		JSONObject testJSONResponse = sut.getServerJSONResponse();
		
		assert(testJSONResponse.getString("type").equals("testResponse"));
		assert(testJSONResponse.getBoolean("isTest"));
		assert(testJSONResponse.getInt("number") == 12345);
		assert(testJSONResponse.getJSONArray("array").getInt(0) == 0);
		assert(testJSONResponse.getJSONArray("array").getInt(4) == 4);
		assert(testJSONResponse.getJSONArray("array").getInt(9) == 9);
		
		sut.closeCommunications();
	}
}
