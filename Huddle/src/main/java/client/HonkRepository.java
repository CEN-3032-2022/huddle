package client;

import java.util.ArrayList;

import org.json.JSONObject;

public interface HonkRepository {
	ArrayList<Honk> getUserHonks(String username);
	
	boolean postHonk(String username, String content,int replyTo);
	
	boolean updateHonk(String username, String date, String content, int numLikes,int id);
	
	ArrayList<Honk> getHonkList();
	
	ArrayList<Honk> getHashtagHonkList(String hashtag);
	ArrayList<Honk> getTagHonkList(String tag);
	ArrayList<Honk> getFollowedHonks(String username);

	ArrayList<Honk> getReplyList(int i);
}
