package client;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HashtagHonkRetriever {
	
	private String hashtag;

	public HashtagHonkRetriever(String hashtag) {
		this.hashtag = hashtag;
	}
	
	public GridPane getHashtagHonkPane() {
    	String value=""; 
		JSONObject JSON = new JSONObject();
		JSON.put("type", "hashtagSearch");
		JSON.put("value", hashtag);
		JSON.put("isTest", false);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
		JSONArray hashtagHonks = new JSONArray(ClientCommunication.getInstance().getServerUsersJSONResponse());
		
		GridPane htHonkPane = new GridPane();
		for(int i = 0;i < hashtagHonks.length(); i++) {
			htHonkPane.add(new Text(hashtagHonks.getJSONObject(i).getString("UserName")), 0, (i*4)+0);
			htHonkPane.add(new Text(hashtagHonks.getJSONObject(i).getString("date")), 3, (i*4)+0);
			htHonkPane.add(new Text(hashtagHonks.getJSONObject(i).getString("content")), 1, (i*4)+1);
		}
		
		return htHonkPane;
	}
	
}
