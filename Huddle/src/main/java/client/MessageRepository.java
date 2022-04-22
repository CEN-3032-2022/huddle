package client;

import java.util.ArrayList;

public interface MessageRepository {
	ArrayList<Message> getSentMessages(String sender);
	ArrayList<Message> getRecievedMessages(String recipient);
	boolean sendMessage(String sender, String recipient, String content);
}