package client;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class HonkRepositoryImp implements HonkRepository {

	@Override
	public ArrayList<Honk> getUserHonks(String username) {
		
		JSONObject JSON = new JSONObject();
		JSON.put("type", "honk");
		JSON.put("request", "honkList");
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject honksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray allHonks = honksJSON.getJSONArray("allHonks");
		
		ArrayList<Honk> honks = new ArrayList<Honk>(); 
		
		for(int i = 0; i < allHonks.length(); i++) {
			
			JSONObject x = (JSONObject) allHonks.get(i);
			
			if(username.compareTo((String)x.get("Username")) == 0) {
				honks.add(new Honk(x));
			}
			
		}
		
		return honks;
	}

	@Override
	public boolean postHonk(int id, String username, String content) {
    	Date publishDate = new Date();
    	publishDate.setToCurrentDate();
		JSONObject JSON = new JSONObject();
    	JSON.put("id", id);
    	JSON.put("UserName", username);
    	JSON.put("content", content);
    	JSON.put("numLikes", 0);
    	JSON.put("date", publishDate.toString());
    	JSONObject JSON2=new JSONObject();
    	JSON2.put("type", "honk");
    	JSON2.put("request", "Post");
		JSON2.put("Honk", JSON.toString());
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON2);
    	
    	JSONObject postJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
		return postJsonResponse.getBoolean("isSuccess");
		
	}

	@Override
	public boolean updateHonk(String username, String date, String content, int numLikes) {
		JSONObject JSON1 = new JSONObject();
    	JSON1.put("UserName", username);
    	JSON1.put("date", date);
    	JSON1.put("content", content);
    	JSON1.put("numLikes", numLikes);
    	
    	JSONObject JSON2 = new JSONObject();
    	JSON2.put("type", "honk");
    	JSON2.put("request", "Update");
		JSON2.put("Honk", JSON1.toString());
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON2);
    	ClientCommunication.getInstance().getServerJSONResponse();
    	
    	JSONObject postJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
		return postJsonResponse.getBoolean("isSuccess");
	}

	@Override
	public ArrayList<Honk> getHonkList() {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "honk");
		JSON.put("request", "honkList");
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject honksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray allHonks = honksJSON.getJSONArray("allHonks");
		
		ArrayList<Honk> honks = new ArrayList<Honk>(); 
		
		for(int i = 0; i < allHonks.length(); i++) {
			JSONObject x = (JSONObject) allHonks.get(i);
			
			honks.add(new Honk(x));
		}
		
		return honks;
	}

	@Override
	public ArrayList<Honk> getHashtagHonkList(String hashtag) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "honk");
		JSON.put("request", "hashtagSearch");
		JSON.put("value", hashtag);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject hashtagHonksJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray hashtagHonks = hashtagHonksJSON.getJSONArray("hashtagHonks");
		
		ArrayList<Honk> honks = new ArrayList<Honk>(); 
		
		for(int i = 0; i < hashtagHonks.length(); i++) {
			JSONObject x = (JSONObject) hashtagHonks.get(i);
			
			honks.add(new Honk(x));
		}
		
		return honks;
	}

	@Override
	public 	ArrayList<Honk> getFollowedHonks(String username) {
		
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
		

		ArrayList<Honk> honks = new ArrayList<Honk>(); 
		
		for(int i = 0; i < followedHonks.length(); i++) {
			JSONObject x = (JSONObject) followedHonks.get(i);
			
			honks.add(new Honk(x));
		}
		
		return honks;
		
	}

}
