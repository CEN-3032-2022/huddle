package client;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserRepositoryImp implements UserRepository {

	
//case "UserList":
//	return getUsers();
//case "NewUser":
//	AddNewUser();
//	return getSuccessResponse();
//case "verify":
//	return verify();
//case "getUsr":
//	return getUser();
//case "userDataTest":
//	return getTestUserDataJSONResponse();
//case "followUser":
//	followUser();
//	return getSuccessResponse();
	
	@Override
	public ArrayList<User> getAllUsers() {
		// Not Used Anywhere
		
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public User getVerifiedUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean followUser(String userFollowing, String userToFollow) {
		// TODO Auto-generated method stub
		return false;
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
