package client;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserRepositoryImp implements UserRepository {
	
	@Override
	public ArrayList<User> getAllUsers() {
		
		// TODO
		// Not Used Anywhere Yet
		// Implement When Needed
		
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		JSONObject profileRequest = new JSONObject();
		profileRequest.put("type", "user");
		profileRequest.put("request", "getUsr");
		profileRequest.put("UserName", username);
		ClientCommunication.getInstance().sendJSONRequestToServer(profileRequest);
		JSONObject profileData = ClientCommunication.getInstance().getServerJSONResponse();
		
		return new User(profileData);
	}

	@Override
	public boolean verifiyUser(String username, String password) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "user");
		JSON.put("request", "verify");
		JSON.put("password", password);
		JSON.put("UserName", username);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject verifyUsersJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray Arr = verifyUsersJSON.getJSONArray("verifyUsers");
		
		for(int i = 0; i < Arr.length(); i++) {
			if(Arr.getJSONObject(i).getString("UserName").equals(username)) {
					return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean followUser(String userFollowing, String userToFollow) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "user");
		JSON.put("request", "followUser");
		JSON.put("userFollowing", App.currentUser.getUsername());
		JSON.put("userToFollow", userToFollow);
		ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
		JSONObject followUserJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
		
		return followUserJsonResponse.getBoolean("isSuccess");
	}

	@Override
	public boolean addNewUser(String username, String password, String recA1, String recA2) {
		JSONObject userJSON = new JSONObject();
		
    	userJSON.put("id", 101); // id will be automatically generated in database
		userJSON.put("type", "saveUserRequest");
		userJSON.put("bio", "I am " + username);
		userJSON.put("followerCount", 0);
		userJSON.put("usersFollowing", new JSONArray());
		userJSON.put("UserName", username);
		userJSON.put("password", password);
		userJSON.put("recoveryAnswer1", recA1);
		userJSON.put("recoveryAnswer2", recA2);
		
    	JSONObject requestJSON = new JSONObject();
    	requestJSON.put("type", "user");
    	requestJSON.put("request", "NewUser");
    	requestJSON.put("User", userJSON.toString());
    	
		ClientCommunication.getInstance().sendJSONRequestToServer(requestJSON);
    	JSONObject saveUserJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
		return saveUserJsonResponse.getBoolean("isSuccess");
	}

}
