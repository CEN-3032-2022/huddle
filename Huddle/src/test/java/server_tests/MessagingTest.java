package server_tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;

import client.Date;
import client.Honk;
import client.HonkRepositoryImp;
import client.LikeHonkController;
import client.MessageRepository;
import client.MessageRepositoryImp;

public class MessagingTest {

	@Test
	public void testGettingMessage() {
		MessageRepository messageRep = new MessageRepositoryImp();
		assert(!messageRep.getSentMessages("testSender", "testRecipient").isEmpty());
	}
	
	@Test
	public void testSendingMessage() {
		MessageRepository messageRep = new MessageRepositoryImp();
		assert(messageRep.sendMessage("testSender", "testRecipient", "testContent"));
	}
}
