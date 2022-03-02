
public class Honk {
	private int id;
	private String content;
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
