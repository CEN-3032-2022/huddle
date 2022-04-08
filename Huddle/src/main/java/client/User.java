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
	
	public User(){
		id = -1;
		username = "unnamed user";
		bio = "unwritten bio";
		followedUsernames = new ArrayList<String>();
	}
	public User(JSONObject json){
		id = json.getInt("id");
		followedUsernames = new ArrayList<String>();
		username = json.getString("UserName");;
		bio = json.getString("bio");
		JSONArray array=new JSONArray(json.getJSONArray("usersFollowing").toString());
		for(int i=0;i<array.length();i++) {
			followedUsernames.add(array.getString(i));
		}
	}
	public User(int id, String username, String bio){
		this.id = id;
		this.username = username;
		this.bio = bio;
		followedUsernames = new ArrayList<String>();
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
	
//	public void addHonk(Honk honk) {
//		honks.add(honk);
//		numHonks++;
//	}
//	
//	public void addHonk(int id, String content, Date publishDate) {
//		honks.add(new Honk(id, content, publishDate, username));
//		numHonks++;
//	}
//	
//	public void addHonk(int id, String content, int month, int day, int year) {
//		honks.add(new Honk(id, content, month, day, year,username));
//		numHonks++;
//	}
	
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
