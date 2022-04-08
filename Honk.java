package client;

import org.json.JSONObject;

public class Honk {
	private int id;
	private String content;
	private String UserName;
	private Date publishDate;
	private int numLikes;
	
	public Honk(){
		id = -1;
		content = "unwritten honk";
		publishDate = new Date();
		UserName="";
		numLikes = 0;
	}
	
	public Honk(int id, String content, Date publishDate,String username){
		this.id = id;
		this.content = content;
		this.publishDate = publishDate;
		this.UserName=username;
	}
	@SuppressWarnings("exports")
	public Honk(JSONObject json){
		UserName=json.getString("UserName");
		content=json.getString("content");
		id=json.getInt("id");
		publishDate=new Date(Integer.parseInt(json.getString("date").split("/")[0]),Integer.parseInt(json.getString("date").split("/")[1]),Integer.parseInt(json.getString("date").split("/")[2]));
		numLikes = json.getInt("numLikes");
	}
	public Honk(int id, String content, int month, int day, int year,String username) {
		this.id = id;
		this.content = content;
		this.publishDate = new Date(month, day, year);
		this.UserName=username;
		this.numLikes = 0; 
	}
	public int getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public int getNumLikes() {
		return numLikes;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public void setPublishDate(int month, int day, int year) {
		this.publishDate = new Date(month, day, year);
	}
	
	public String toString() {
		String output = "id: " + getId() + "\n" +
					"content: " + getContent() + "\n" +
					"publish date: " + getPublishDate().toString() + "\n";
		
		return output;
	}
	
	public String toJsonString() {
		JSONObject honkJson = new JSONObject();
		honkJson.put("id", getId());
		honkJson.put("UserName", getUserName());
		honkJson.put("Content", getContent());
		honkJson.put("PublishDate", getPublishDate());
		honkJson.put("numLikes", getNumLikes());
				
		return honkJson.toString();
	}
}
