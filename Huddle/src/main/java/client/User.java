package client;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Honk;
public class User {
	private int id;
	private String username;
	private String bio;
	private ArrayList<String> followedUsernames;
	int followererCount;
	int chosenTheme = 0;
	
	public User(){
		id = -1;
		username = "unnamed user";
		bio = "unwritten bio";
		followedUsernames = new ArrayList<String>();
		followererCount = 0;
		chosenTheme = 1;
	}
	public User(JSONObject json) {
		try {
		id = json.getInt("id");
		followedUsernames = new ArrayList<String>();
		username = json.getString("UserName");;
		bio = json.getString("bio");
		JSONArray array=new JSONArray(json.getJSONArray("usersFollowing").toString());
		for(int i=0;i<array.length();i++) {
			followedUsernames.add(array.getString(i));
		}
		followererCount = json.getInt("followerCount");
		chosenTheme = json.getInt("chosenTheme");
		}
		catch(Exception e) {
			id = -1;
			username = "unnamed user";
			bio = "unwritten bio";
			followedUsernames = new ArrayList<String>();
			followererCount = 0;
		}
	}
	public User(int id, String username, String bio){
		this.id = id;
		this.username = username;
		this.bio = bio;
		chosenTheme = 1;
		followedUsernames = new ArrayList<String>();
		followererCount = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getBio() {
		return bio;
	}
	
	public ArrayList<String> getFollowedUsernames() {
		return followedUsernames;
	}
	
	public String getFollowedUser(int index) {
		return followedUsernames.get(index);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public int getFollowerCount() {
		return this.followererCount;
	}
	
	public void setChosenTheme(int theme) {
		this.chosenTheme = theme;
	}
	
	public int getChosenTheme() {
		return this.chosenTheme;
	}
	
	public String toString() {
		String output = "id: " + getId() + "\n" +
				"username: " + getUsername() + "\n" +
				"bio: " + getBio() + "\n";
		
		return output;
	}
	
//	public String toJsonString() {
//		JSONObject userJson = new JSONObject();
//		userJson.put("id", getId());
//		userJson.put("UserName", getUsername());
//		userJson.put("bio", getBio());
//		
//		JSONArray honksJson = new JSONArray();
//		
//		for(int i = 0; i < getNumHonks(); i++) {
//			honksJson.put(getHonk(i));
//		}
//		
//		userJson.put("honks", honksJson);
//		
//		return userJson.toString();
//	}
}
