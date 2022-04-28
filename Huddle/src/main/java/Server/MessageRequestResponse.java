package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class MessageRequestResponse implements ServerResponse {

	private ArrayList<JSONObject> Messages = new ArrayList<JSONObject>();
 	private JSONObject messageRequestJSON;
	
 	public MessageRequestResponse(JSONObject messageRequestJSON) {
 		readFile();
 		this.messageRequestJSON = messageRequestJSON;
 	}
 	
	@Override
	public JSONObject getResponse() {
		String requestType = messageRequestJSON.getString("request");
		
 		switch(requestType) {
 			case "getSentMessages":
 				return getSentMessages();
 			case "getReceivedMessages":
 				return getReceivedMessages();
 			case "getSentAndRecievedMessages":
 				return getSentAndRecievedMessages();
 			case "sendMessage":
 				sendMessage();
 				return getSuccessResponse();
 		}

 		return getFailureResponse();
	}
	
	private JSONObject getSentAndRecievedMessages() {
		String user = messageRequestJSON.getString("user");
		String otherAccount = messageRequestJSON.getString("otherAccount");

 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i <Messages.size(); i++) {
 			if(user.equals(Messages.get(i).getString("sender")) && otherAccount.equals(Messages.get(i).getString("recipient")))
 				jsonArray.put(Messages.get(i));
 			else if(user.equals(Messages.get(i).getString("recipient")) && otherAccount.equals(Messages.get(i).getString("sender")))
 				jsonArray.put(Messages.get(i));
 		}
 		
 		JSONObject sentMessagesJSON = new JSONObject();
 		sentMessagesJSON.put("sentAndRecievedMessages", jsonArray);
 		
 		return sentMessagesJSON;
	}
	
	private JSONObject getSentMessages() {
		String sender = messageRequestJSON.getString("sender");
		String recipient = messageRequestJSON.getString("recipient");

 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i <Messages.size(); i++) {
 			if(sender.equals(Messages.get(i).getString("sender")) && recipient.equals(Messages.get(i).getString("recipient")))
 				jsonArray.put(Messages.get(i));
 		}
 		
 		JSONObject sentMessagesJSON = new JSONObject();
 		sentMessagesJSON.put("sentMessages", jsonArray);
 		
 		return sentMessagesJSON;
	}
	
	private JSONObject getReceivedMessages() {
		String recipient = messageRequestJSON.getString("recipient");
		String sender = messageRequestJSON.getString("sender");


 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i < Messages.size(); i++) {
 			if(recipient.equals(Messages.get(i).getString("recipient")) && sender.equals(Messages.get(i).getString("sender")))
 				jsonArray.put(Messages.get(i));
 		}
 		
 		JSONObject receivedMessagesJSON = new JSONObject();
 		receivedMessagesJSON.put("receivedMessages", jsonArray);
 		return receivedMessagesJSON;
	}
	
	private void sendMessage() {
		JSONObject messageJSON = new JSONObject(messageRequestJSON.getString("message"));
 		Messages.add(messageJSON);
 		writeToFile();
	}
	
	private void readFile(){
 		try {
 			Messages.clear();
 			BufferedReader Reader = new BufferedReader(new FileReader("messages.txt"));
 			String val;
 			while((val = Reader.readLine()) != null) {
 				Messages.add(new JSONObject(val));
 			}
 			Reader.close();
 		} catch (IOException e) { e.printStackTrace(); }
 	}
	
	private void writeToFile() {
 		try {
 			FileWriter outHonk = new FileWriter("messages.txt",false);
 			for(int i=0;i < Messages.size(); i++) {
 				if(i == 0)
 					outHonk.write(Messages.get(i).toString()+"\n");
 				else
 					outHonk.append(Messages.get(i).toString()+"\n");
 			}
 			outHonk.close();
 		}
 		catch(Exception e) { e.printStackTrace(); }
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

}
