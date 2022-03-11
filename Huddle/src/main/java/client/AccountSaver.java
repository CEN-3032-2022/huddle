package client;

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
		
		@SuppressWarnings("unused")
		JSONObject saveUserAccountJSON = getSaveUserAccountJSONRequest();
		
		// TODO: Send save user request and retrieve response
		
		return true;
	}
	
	private JSONObject getSaveUserAccountJSONRequest() {
		JSONObject saveUserAccountJSON = new JSONObject();
		
		saveUserAccountJSON.put("type", "saveUserRequest");
		saveUserAccountJSON.put("isTest", false);
		saveUserAccountJSON.put("username", username);
		saveUserAccountJSON.put("password", password);
		saveUserAccountJSON.put("recoveryAnswer1", recoveryAnswer1);
		saveUserAccountJSON.put("recoveryAnswer2", recoveryAnswer2);
		
		return saveUserAccountJSON;
	}
	
}
