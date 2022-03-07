package client;
public class Honk {
	private int id;
	private String content;
	private String UserName;
	private Date publishDate;
	
	public Honk(){
		id = -1;
		content = "unwritten honk";
		publishDate = new Date();
	}
	
	public Honk(int id, String content, Date publishDate){
		this.id = id;
		this.content = content;
		this.publishDate = publishDate;
	}
	public Honk(String json){
		try {
		StringBuilder tempBuilder = new StringBuilder();
		tempBuilder.append(json);
		while(tempBuilder.charAt(0)=='{'||tempBuilder.charAt(0)=='['||tempBuilder.charAt(0)==',')
			tempBuilder.deleteCharAt(0);
		while(tempBuilder.charAt(tempBuilder.length()-1)=='}'||tempBuilder.charAt(tempBuilder.length()-1)==']')
			tempBuilder.deleteCharAt(tempBuilder.length()-1);
		for(int i =0;i<tempBuilder.length();i++)
			if(tempBuilder.charAt(i)=='"')
				tempBuilder.deleteCharAt(i);
		json=tempBuilder.toString();
		String[] jsonVals=json.split(",");
		String[] tempVals=jsonVals[2].split(":");
		id=Integer.parseInt(tempVals[1]);
		tempVals=jsonVals[3].split(":");
		content=tempVals[1];
		tempVals=jsonVals[0].split(":");
		String[] tempVals2=tempVals[1].split("/");
		publishDate=new Date(Integer.parseInt(tempVals2[0].replaceAll("\"","")),Integer.parseInt(tempVals2[1].replaceAll("\"","")),Integer.parseInt(tempVals2[2].replaceAll("\"","")));
		tempVals=jsonVals[1].split(":");
		UserName=tempVals[1];
		}
		catch(Exception E) {
			E.printStackTrace();
		}
	}
	public Honk(int id, String content, int month, int day, int year) {
		this.id = id;
		this.content = content;
		this.publishDate = new Date(month, day, year);
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

	public void setUserName(String userName) {
		UserName = userName;
	}

	public void setId(int id) {
		this.id = id;
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
}
