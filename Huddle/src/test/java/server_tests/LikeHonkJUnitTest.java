package server_tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

import client.Date;
import client.Honk;
import client.HonkRepositoryImp;
import client.LikeHonkController;

public class LikeHonkJUnitTest {

	@Test
	public void testLikeHonk() {
		HonkRepositoryImp honkRep = new HonkRepositoryImp();
		
		Honk honk1 = honkRep.getHonkList().get(0);
		System.out.println(honk1.toJsonString());
		
		int initialNumLikes = honk1.getNumLikes();
		
		JSONObject honkJSON = new JSONObject();
		honkJSON.put("UserName", honk1.getUserName());
		honkJSON.put("date", honk1.getPublishDate().toString());
		honkJSON.put("id", honk1.getId());
		honkJSON.put("content", honk1.getContent());
		honkJSON.put("numLikes", honk1.getNumLikes());
		
		try {
			LikeHonkController.likeHonk(honkJSON);
			Honk honk2 = honkRep.getHonkList().get(0);
			
			int finalNumLikes = honk2.getNumLikes();
			assert(initialNumLikes + 1 == finalNumLikes);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
