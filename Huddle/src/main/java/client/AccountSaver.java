package client;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccountSaver {

	String username;
	String password;
	String recoveryAnswer1;
	String recoveryAnswer2;
	
	public AccountSaver(String username, String password, String recoveryAnswer1, String recoveryAnswer2) {
		this.username = username;
		this.password = password;
		this.recoveryAnswer1 = recoveryAnswer1;
		this.recoveryAnswer2 = recoveryAnswer2;
	}
	
	public boolean saveAccount() {
		JSONObject saveUserRequestJSON = getSaveUserAccountJSONRequest();
		ClientCommunication.getInstance().sendJSONRequestToServer(saveUserRequestJSON);
		
    	JSONObject saveUserJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
		return saveUserJsonResponse.getBoolean("isSuccess");
	}
	
	private JSONObject getSaveUserAccountJSONRequest() {
		JSONObject userJSON = new JSONObject();
		
    	userJSON.put("id", 101); // id will be automatically generated in database
		userJSON.put("type", "saveUserRequest");
		userJSON.put("bio", "I am " + username);
		userJSON.put("followerCount", 0);
		userJSON.put("usersFollowing", new JSONArray());
		userJSON.put("UserName", username);
		userJSON.put("password", password);
		userJSON.put("recoveryAnswer1", recoveryAnswer1);
		userJSON.put("recoveryAnswer2", recoveryAnswer2);
		
    	JSONObject requestJSON = new JSONObject();
    	requestJSON.put("type", "user");
    	requestJSON.put("request", "NewUser");
    	requestJSON.put("User", userJSON.toString());
		
		return requestJSON;
	}
	
}
