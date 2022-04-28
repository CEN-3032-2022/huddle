package client;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class MessageRepositoryImp implements MessageRepository{
	public ArrayList<Message> getSentMessages(String sender, String recipient){
		
		JSONObject JSON = new JSONObject();
		JSON.put("type", "message");
		JSON.put("request", "getSentMessages");
		JSON.put("sender", sender);
		JSON.put("recipient", recipient);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject messagesJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray sentMessagesJSON = messagesJSON.getJSONArray("sentMessages");
		
		ArrayList<Message> sentMessages = new ArrayList<Message>(); 
		
		for(int i = 0; i < sentMessagesJSON.length(); i++) {
			JSONObject currentMessage = (JSONObject) sentMessagesJSON.get(i);
			sentMessages.add(new Message(currentMessage));
		}
		
		return sentMessages;
	}
	
	public ArrayList<Message> getReceivedMessages(String sender, String recipient){
		JSONObject JSON = new JSONObject();
		JSON.put("type", "message");
		JSON.put("request", "getReceivedMessages");
		JSON.put("recipient", recipient);
		JSON.put("sender", sender);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject messagesJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray receivedMessagesJSON = messagesJSON.getJSONArray("receivedMessages");
		
		ArrayList<Message> receivedMessages = new ArrayList<Message>(); 
		
		for(int i = 0; i < receivedMessagesJSON.length(); i++) {
			JSONObject currentMessage = (JSONObject) receivedMessagesJSON.get(i);
			receivedMessages.add(new Message(currentMessage));
		}
		
		return receivedMessages;
	}
	
	public boolean sendMessage(String sender, String recipient, String content) {
		JSONObject JSON = new JSONObject();
		JSON.put("sender", sender);
		JSON.put("recipient", recipient);
		JSON.put("content", content);
		
		JSONObject JSON2 = new JSONObject();
		JSON2.put("type", "message");
		JSON2.put("request", "sendMessage");
		JSON2.put("message", JSON.toString());
		
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON2);
    	
    	JSONObject postJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
		return postJsonResponse.getBoolean("isSuccess");
		
	}

	@Override
	public ArrayList<Message> getSentAndReceivedMessages(String user, String otherAccount) {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "message");
		JSON.put("request", "getSentAndRecievedMessages");
		JSON.put("user", user);
		JSON.put("otherAccount", otherAccount);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject messagesJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray receivedMessagesJSON = messagesJSON.getJSONArray("sentAndRecievedMessages");
		ArrayList<Message> receivedMessages = new ArrayList<Message>(); 
		
		for(int i = 0; i < receivedMessagesJSON.length(); i++) {
			JSONObject currentMessage = (JSONObject) receivedMessagesJSON.get(i);
			receivedMessages.add(new Message(currentMessage));
		}
		
		return receivedMessages;
	}
}
