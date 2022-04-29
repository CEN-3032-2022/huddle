package server_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import client.Honk;
import client.HonkRepositoryImp;
import client.LikeHonkController;

class ReplyTest {

	@Test
	void test() {
		HonkRepositoryImp honkRep = new HonkRepositoryImp();
		int honk1 = (honkRep.getReplyList(-100)).size();
		System.out.print(honk1);
		assert(honk1==0);
		int honk2 = (honkRep.getReplyList(0)).size();
		assert(honk2>=0);
	}

}
