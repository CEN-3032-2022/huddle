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
	
	public JSONArray getAllFollowedUsersHonks(String username) {
		JSONObject profileRequest = new JSONObject();
		profileRequest.put("type", "user");
		profileRequest.put("request", "getUsr");
		profileRequest.put("UserName", username);
		ClientCommunication.getInstance().sendJSONRequestToServer(profileRequest);
		JSONObject profileData = ClientCommunication.getInstance().getServerJSONResponse();
		
		JSONObject followedHonksRequest = new JSONObject();
		followedHonksRequest.put("type", "honk");
		followedHonksRequest.put("request", "followedHonks");
		followedHonksRequest.put("followedUsers", profileData.get("usersFollowing"));
		ClientCommunication.getInstance().sendJSONRequestToServer(followedHonksRequest);
		JSONObject followedHonksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray followedHonks = followedHonksJSON.getJSONArray("followedHonks");
		
		return followedHonks;
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
