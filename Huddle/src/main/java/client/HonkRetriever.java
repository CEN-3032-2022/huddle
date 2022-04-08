package client;

import org.json.JSONArray;
import org.json.JSONObject;

public class HonkRetriever {
	
	public JSONArray getAllHonks() {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "honk");
		JSON.put("request", "honkList");
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject honksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray allHonks = honksJSON.getJSONArray("allHonks");
		return allHonks;
	}
	
	public JSONArray getHashtagHonks(String hashtag) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "honk");
		JSON.put("request", "hashtagSearch");
		JSON.put("value", hashtag);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject hashtagHonksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray hashtagHonks = hashtagHonksJSON.getJSONArray("hashtagHonks");
		return hashtagHonks;
	} 
	
	public JSONArray searchByUsername(String username) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "user");
		JSON.put("request", "usernameSearch");
		JSON.put("value", username);
		ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
		JSONObject usernamesJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray usernames = usernamesJSON.getJSONArray("username");
		return usernames;
	}

	public JSONArray getUsrHonks(String username) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "honk");
		JSON.put("request", "usrHonks");
		JSON.put("UserName", username);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject userHonksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray userHonks = userHonksJSON.getJSONArray("userHonks");
		return userHonks;
	}
}
