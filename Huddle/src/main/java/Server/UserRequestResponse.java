package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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
		}
		
		return getFailureResponse();
	}
    
	// Remove Method When Database Fully Integrated
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
	
	// Remove Method When Database Fully Integrated
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
				json.remove("Password");
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
			x.remove("Password");
			jsonArray.put(x);
		}
		JSONObject usersJSON = new JSONObject();
		usersJSON.put("users", jsonArray);
		return usersJSON;
	}
	
	public JSONObject verify() {
		String userName = userRequestJSON.getString("UserName");
		String password = userRequestJSON.getString("Password");
		
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i <Users.size(); i++) {
			if(userName.equals(Users.get(i).getString("UserName")) && password.equals(Users.get(i).getString("Password"))) {
				JSONObject x = new JSONObject(Users.get(i).toString());
				x.remove("Password");
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
    
	public UserRequestResponse(JSONObject userRequestJSON) {
		readFile();
		this.userRequestJSON = userRequestJSON;
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
