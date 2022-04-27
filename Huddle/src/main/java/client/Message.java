package client;

import org.json.JSONObject;

public class Message {
	private String sender;
	private String recipient;
	private String content;
	
	public Message(){
		sender = "unnamed sender";
		recipient = "unnamed recipient";
		content = "unspecified message";
	}
	
	public Message(String sender, String recipient, String content){
		this.sender = sender;
		this.recipient = recipient;
		this.content = content;
	}
	
	public Message(JSONObject json) {
		sender = json.getString("sender");
		recipient = json.getString("recipient");
		content = json.getString("content");
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		String out = "sender: " + sender + "\n";
		out += "recipient: " + recipient + "\n";
		out += "content: " + content + "\n"; 
		
		return out;
	}
}
