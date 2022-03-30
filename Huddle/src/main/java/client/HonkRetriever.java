package client;

import org.json.JSONArray;
import org.json.JSONObject;

public class HonkRetriever {
	
	public JSONArray getAllHonks() {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "HonkList");
		JSON.put("isTest", false);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	String honksJSONArrayString = ClientCommunication.getInstance().getServerUsersJSONResponse();
		JSONArray allHonks = new JSONArray(honksJSONArrayString);
		return allHonks;
	}
	
	public JSONArray getHashtagHonks(String hashtag) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "hashtagSearch");
		JSON.put("value", hashtag);
		JSON.put("isTest", false);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	String hashtagHonksJSONArrayString = ClientCommunication.getInstance().getServerUsersJSONResponse();
		JSONArray hashtagHonks = new JSONArray(hashtagHonksJSONArrayString);
		return hashtagHonks;
	}
}
