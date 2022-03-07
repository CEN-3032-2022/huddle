package client;

import java.util.ArrayList;
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
	public User(String json){
		StringBuilder tempBuilder = new StringBuilder();
		StringBuilder RHonks=new StringBuilder();
		RHonks.append(json);
		for(int i =0;i<RHonks.length();i++)
			if(RHonks.charAt(i)=='"')
				RHonks.deleteCharAt(i);
		while(RHonks.charAt(0)=='{'||RHonks.charAt(0)=='['||RHonks.charAt(0)==',')
			RHonks.deleteCharAt(0);
		String[] LHonks=RHonks.toString().split("]");
		String HonksStr=(LHonks[0].split(":")[1]);
		json="{"+LHonks[1];
		tempBuilder.append(json);
		tempBuilder.deleteCharAt(0);
		tempBuilder.deleteCharAt(tempBuilder.length()-1);
		json=tempBuilder.toString();
		String[] jsonVals=json.split(",");
		String[] tempVals=jsonVals[1].split(":");
		password=tempVals[1];
		tempVals=jsonVals[3].split(":");
		id = Integer.parseInt(tempVals[1]);
		tempVals=jsonVals[6].split(":");
		username = tempVals[1];
		tempVals=jsonVals[2].split(":");
		bio = tempVals[1];
		tempVals=jsonVals[4].split(":");
		numHonks = Integer.parseInt(tempVals[1]);
		tempVals=HonksStr.split("}");
		honks = new ArrayList<Honk>();
		for(int i=1;i<numHonks;i++) {
			System.out.println(tempVals[i]+"}");
			honks.add(new Honk(tempVals[i]+"}"));
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
		honks.add(new Honk(id, content, publishDate));
		numHonks++;
	}
	
	public void addHonk(int id, String content, int month, int day, int year) {
		honks.add(new Honk(id, content, month, day, year));
		numHonks++;
	}
	
	public String toString() {
		String output = "id: " + getId() + "\n" +
				"username: " + getUsername() + "\n" +
				"password: " + getPassword() + "\n" +
				"bio: " + getBio() + "\n";
		
		return output;
	}
}
