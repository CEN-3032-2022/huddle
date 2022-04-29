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

public class TaggedHonksTest {

	@Test
	public void testTaggingHonk() {
		HonkRepositoryImp honkRep = new HonkRepositoryImp();
		honkRep.postHonk("Test", "@test testing", -1);
		ArrayList<Honk> taggedHonks = honkRep.getTagHonkList("@test");
		assert(taggedHonks.size() > 0);
	}
	
	@Test
	public void testGettingTaggingHonk() {
		HonkRepositoryImp honkRep = new HonkRepositoryImp();
		ArrayList<Honk> taggedHonks = honkRep.getTagHonkList("@guest");
		assert(taggedHonks.size() == 2);
	}
}
