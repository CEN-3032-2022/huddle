package client;

import java.util.ArrayList;

public interface HonkRepository {
	ArrayList<Honk> getUserHonks(String username);
	
	boolean postHonk(int id, String username, String content);
	
	boolean updateHonk(String username, String date, String content, int numLikes);
	
	ArrayList<Honk> getHonkList();
	
	ArrayList<Honk> getHashtagHonkList(String hashtag);
	
	ArrayList<Honk> getFollowedHonks(String username);
}
