package client;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Honk;
public class User {
	private int id;
	private String username;
	private String password;
	private String bio;
	private ArrayList<Honk> honks;
	private int numHonks;
	
	public User(){
		id = -1;
		username = "unnamed user";
		password = "password";
		bio = "unwritten bio";
		honks = new ArrayList<Honk>();
		numHonks = 0;
	}
	public User(JSONObject json){
		id = json.getInt("id");
		honks=new ArrayList<Honk>();
		username = json.getString("username");;
		password = json.getString("password");
		bio = json.getString("bio");
		numHonks = json.getInt("numHonks");
		JSONArray array=new JSONArray(json.getJSONArray("honks").toString());
		for(int i=0;i<array.length();i++) {
			honks.add(new Honk(array.getJSONObject(i)));
		}
	}
	public User(int id, String username, String password, String bio){
		this.id = id;
		this.username = username;
		this.password = password;
		this.bio = bio;
		honks = new ArrayList<Honk>();
		numHonks = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getBio() {
		return bio;
	}
	
	public ArrayList<Honk> getHonks() {
		return honks;
	}
	
	public Honk getHonk(int index) {
		return honks.get(index);
	}
	
	public int getNumHonks() {
		return numHonks;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public void addHonk(Honk honk) {
		honks.add(honk);
		numHonks++;
	}
	
	public void addHonk(int id, String content, Date publishDate) {
		honks.add(new Honk(id, content, publishDate, username));
		numHonks++;
	}
	
	public void addHonk(int id, String content, int month, int day, int year) {
		honks.add(new Honk(id, content, month, day, year,username));
		numHonks++;
	}
	
	public String toString() {
		String output = "id: " + getId() + "\n" +
				"username: " + getUsername() + "\n" +
				"password: " + getPassword() + "\n" +
				"bio: " + getBio() + "\n";
		
		return output;
	}
	
	public String toJsonString() {
		JSONObject userJson = new JSONObject();
		userJson.put("id", getId());
		userJson.put("UserName", getUsername());
		userJson.put("Password", getPassword());
		userJson.put("bio", getBio());
		
		JSONArray honksJson = new JSONArray();
		
		for(int i = 0; i < getNumHonks(); i++) {
			honksJson.put(getHonk(i));
		}
		
		userJson.put("honks", honksJson);
		
		return userJson.toString();
	}
}
