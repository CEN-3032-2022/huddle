package client;

import java.util.ArrayList;

public interface MessageRepository {
	ArrayList<Message> getSentMessages(String sender, String recipient);
	ArrayList<Message> getReceivedMessages(String sender, String recipient);
	boolean sendMessage(String sender, String recipient, String content);
}