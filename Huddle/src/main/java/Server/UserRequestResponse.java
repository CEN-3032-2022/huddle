package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import client.App;

public class UserRequestResponse implements ServerResponse {

    static private ArrayList<JSONObject> Users = new ArrayList<JSONObject>();
    private JSONObject userRequestJSON;
    
	@Override
	public JSONObject getResponse() {	
		String requestType = userRequestJSON.getString("request");
		
		switch(requestType) {
			case "UserList":
				return getUsers();
			case "NewUser":
				AddNewUser();
				return getSuccessResponse();
			case "verify":
				return verify();
			case "getUsr":
				return getUser();
			case "userDataTest":
				return getTestUserDataJSONResponse();
			case "followUser":
				followUser();
				return getSuccessResponse();
			case "unfollowUser":
				unfollowUser();
				return getSuccessResponse();
			case "updatePassword":
				return updatePassword();
			case "updateBio":
				return updateBio();
			case "updateTheme":
				return updateTheme();
		}
		
		return getFailureResponse();
	}
	
	public UserRequestResponse(JSONObject userRequestJSON) {
		readFile();
		this.userRequestJSON = userRequestJSON;
	}
    
	private void writeToFile() {
		try {
			FileWriter outUser = new FileWriter("users.txt",false);
			for(int i = 0; i < Users.size(); i++) {
				if(i == 0)
					outUser.write(Users.get(i).toString()+"\n");
				else
					outUser.append(Users.get(i).toString()+"\n");
				}
			outUser.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	private void readFile(){
		try {
			Users.clear();
			BufferedReader Reader = new BufferedReader(new FileReader("users.txt"));
			String val2;
			while((val2 = Reader.readLine()) != null) {
				Users.add(new JSONObject(val2));
			}
			Reader.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private JSONObject getUser() {
		String username = userRequestJSON.getString("UserName");
		
		JSONObject json = new JSONObject();
		for(int i = 0; i < Users.size(); i++) {
			if(username.equals(Users.get(i).getString("UserName"))) {
				json=new JSONObject(Users.get(i).toString());
				json.remove("password");
				break;
			}
		}
		return json;
	}
	
	private void AddNewUser() {
		JSONObject userJSON = new JSONObject(userRequestJSON.getString("User"));
		Users.add(userJSON);
		writeToFile();
	}
	
	private JSONObject getUsers() {
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i <Users.size(); i++) {
			JSONObject x = new JSONObject(Users.get(i).toString());
			x.remove("password");
			x.remove("recoveryAnswer1");
			x.remove("recoveryAnswer2");
			jsonArray.put(x);
		}
		JSONObject usersJSON = new JSONObject();
		usersJSON.put("users", jsonArray);
		return usersJSON;
	}
	
	public JSONObject verify() {
		String userName = userRequestJSON.getString("UserName");
		String password = userRequestJSON.getString("password");
		
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i <Users.size(); i++) {
			if(userName.equals(Users.get(i).getString("UserName")) && password.equals(Users.get(i).getString("password"))) {
				JSONObject x = new JSONObject(Users.get(i).toString());
				x.remove("password");
				x.remove("recoveryAnswer1");
				x.remove("recoveryAnswer2");
				jsonArray.put(x);
				break;
			}
		}
		JSONObject possibleUsersJSON = new JSONObject();
		possibleUsersJSON.put("verifyUsers", jsonArray);
		return possibleUsersJSON;
	}
	
	private JSONObject getSuccessResponse() {
		JSONObject successJSON = new JSONObject();
		successJSON.put("isSuccess", true);
		return successJSON;
	}
	
	private JSONObject getFailureResponse() {
		JSONObject successJSON = new JSONObject();
		successJSON.put("isSuccess", false);
		return successJSON;
	}
	
	private void followUser() {
		String userFollowing = userRequestJSON.getString("userFollowing");
		String userToFollow = userRequestJSON.getString("userToFollow");
		
		for(int i = 0; i <Users.size(); i++) {
			if(userFollowing.equals(Users.get(i).getString("UserName"))) {
				JSONArray usersFollowing = Users.get(i).getJSONArray("usersFollowing");
				usersFollowing.put(userToFollow);
				Users.get(i).put("usersFollowing", usersFollowing);
			}
			else if(userToFollow.equals(Users.get(i).getString("UserName"))) {
				int newFollowerCount = Users.get(i).getInt("followerCount") + 1;
				Users.get(i).put("followerCount", newFollowerCount);
			}
		}
		
		writeToFile();
	}
	
	private void unfollowUser() {
		String userUnfollowing = userRequestJSON.getString("userUnfollowing");
		String userToUnfollow = userRequestJSON.getString("userToUnfollow");
		
		for(int i = 0; i <Users.size(); i++) {
			if(userUnfollowing.equals(Users.get(i).getString("UserName"))) {
				JSONArray usersFollowing = Users.get(i).getJSONArray("usersFollowing");
				for(int j = 0; j < usersFollowing.length(); j++) {
					if(usersFollowing.get(j).equals(userToUnfollow)) usersFollowing.remove(j);
				}
				Users.get(i).put("usersFollowing", usersFollowing);
			}
			else if(userToUnfollow.equals(Users.get(i).getString("UserName"))) {
				int newFollowerCount = Users.get(i).getInt("followerCount") - 1;
				Users.get(i).put("followerCount", newFollowerCount);
			}
		}
		
		writeToFile();
	}
	
	private JSONObject updatePassword() {
		String username = userRequestJSON.getString("UserName");
		String newPassword = userRequestJSON.getString("newPassword");
		String recAnswr1 = userRequestJSON.getString("recoveryAnswer1");
		String recAnswr2 = userRequestJSON.getString("recoveryAnswer2");
		
		
		for(int i = 0; i < Users.size(); i++) {
			if(username.equals(Users.get(i).getString("UserName"))) {
				JSONObject userJSON = Users.get(i);
				if(userJSON.get("recoveryAnswer1").equals(recAnswr1)
						&& userJSON.get("recoveryAnswer2").equals(recAnswr2)) {
					Users.get(i).put("password", newPassword);
	 				writeToFile();
	 				return getSuccessResponse();
				}
			}
		}
		return getFailureResponse();
	}
	
	private JSONObject updateBio() {
		String username = userRequestJSON.getString("UserName");
		String newBio = userRequestJSON.getString("newBio");
		
		for(int i = 0; i < Users.size(); i++) {
			if(username.equals(Users.get(i).getString("UserName"))) {
				Users.get(i).put("bio", newBio);
	 			writeToFile();
	 			return getSuccessResponse();
			}
		}
		return getFailureResponse();
	}
	
	private JSONObject updateTheme() {
		String username = userRequestJSON.getString("UserName");
		int chosenTheme = userRequestJSON.getInt("chosenTheme");
		
		for(int i = 0; i < Users.size(); i++) {
			if(username.equals(Users.get(i).getString("UserName"))) {
				Users.get(i).put("chosenTheme", chosenTheme);
	 			writeToFile();
	 			return getSuccessResponse();
			}
		}
		return getFailureResponse();
	}
	
	// ---------- Temporary Testing Methods	------------
	private JSONObject getTestUserDataJSONResponse() {
		JSONObject testJSONResponse = new JSONObject();
		testJSONResponse.put("type", "userDataResponse");
		testJSONResponse.put("isTest", true);
		return testJSONResponse;
	}
	// ------------ End Of Testing Methods -------------

}
