package Server;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServerResponse {

	FileDataRetriever dataRetriever;
	
	public ServerResponse() {
		dataRetriever = new FileDataRetriever();
	}
	
	public JSONObject getUserDataLookupJSONResponse(String username) {
		try {
			JSONObject foundUserJSON = dataRetriever.getUserByUsername(username);
			if(foundUserJSON == null) {
				foundUserJSON = new JSONObject();
				foundUserJSON.put("userExists", false);
			} else {
				foundUserJSON.put("userExists", true);
			}
			foundUserJSON.put("type", "userLookupResponse");
			return foundUserJSON;
		} catch (IOException e) { e.printStackTrace(); }

		return null;
	}
	
	public JSONObject getHonkListJSONResponse() {
		try {
			JSONObject honkListJSONResponse = new JSONObject();
			JSONArray allHonksJSONArray = dataRetriever.getAllHonks();
			if(allHonksJSONArray == null) {
				honkListJSONResponse.put("honksExists", false);
			} else {
				honkListJSONResponse.put("allHonks", allHonksJSONArray);
				honkListJSONResponse.put("honksExist", true);
			}
			honkListJSONResponse.put("type", "allHonksResponse");
			return honkListJSONResponse;
		} catch (IOException e) { e.printStackTrace(); }
		
		return null;
	}
	
	

	
	// ---------- Temporary Testing Responses	------------
	public JSONObject getTestUserDataJSONResponse() {
		JSONObject testJSONResponse = new JSONObject();
		testJSONResponse.put("type", "userDataResponse");
		testJSONResponse.put("isTest", true);
		return testJSONResponse;
	}
	
	public JSONObject getTestJSONResponse() {
		JSONObject testJSONResponse = new JSONObject();
		testJSONResponse.put("type", "testResponse");
		testJSONResponse.put("isTest", true);
		testJSONResponse.put("number", 12345);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < 10; ++i) {
			jsonArray.put(i);
		}
		testJSONResponse.put("array", jsonArray);
		return testJSONResponse;
	}
	
	public JSONArray getHonkList() {
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < 10; ++i) {
			JSONObject Response = new JSONObject();
			Response.put("id", 1);
			Response.put("content","Hi");
			Response.put("date", "1/1/11");
			Response.put("UserName", "Test"+i);
			jsonArray.put(Response);
		}
		return jsonArray;
	}
	
	
	public JSONArray getUsers() {
			JSONArray jsonArray = new JSONArray();
			JSONObject Response = new JSONObject();
			
			Response.put("id", 1);
			Response.put("UserName", "daniel");
			Response.put("Password", "password");
			jsonArray.put(Response);
	
		return jsonArray;
	}
	// ------------ End Of Testing Methods -------------
	
}
