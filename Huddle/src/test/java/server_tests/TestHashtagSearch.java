package server_tests;

import client.HonkRepositoryImp;
import client.HonkRepository;

import java.util.ArrayList;

import org.junit.Test;

import client.Honk;

public class TestHashtagSearch {
	
	@Test
	public void testHashtagSearch() {
		HonkRepository honkRepo = new HonkRepositoryImp();
		ArrayList<Honk> honks = honkRepo.getHashtagHonkList("#test");
		assert(honks.get(0).getContent().contains("#test"));
	}
	
}
