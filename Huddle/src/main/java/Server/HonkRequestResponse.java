package Server;

 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 import org.json.JSONArray;
 import org.json.JSONObject;

 public class HonkRequestResponse implements ServerResponse {

 	private ArrayList<JSONObject> Honks = new ArrayList<JSONObject>();
 	private int PostC=0;
 	private JSONObject honkRequestJSON;

 	public HonkRequestResponse(JSONObject honkRequestJSON) {
 		readFile();
 		this.honkRequestJSON = honkRequestJSON;
 	}

 	@Override
 	public JSONObject getResponse() {
 		String requestType = honkRequestJSON.getString("request");
 		switch(requestType) {
 			case "honkList":
 				return getHonkList();
 			case "Post":
 				if(honkRequestJSON.getInt("replyTo")<0)
 					postHonk();
 				else
 					postReply();
 				return getSuccessResponse();
 			case "hashtagSearch":
 				return getHashTagHonkList();
 			case "taggedSearch":
 				return getTagHonkList();
 			case "usrHonks":
 				return getUserHonks();
			case "followedHonks":
				return getFollowedHonks();
			case "getReplies":
				return getReplies();
 			case "Update":
 				updateHonk();
 				return getSuccessResponse();
 		}

 		return getFailureResponse();
 	}

 	private JSONObject getTagHonkList() {
 		String tag = honkRequestJSON.getString("tag"); 
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i <Honks.size(); i++) {
				if(Honks.get(i).getString("content").contains(tag)&&Honks.get(i).getInt("replyTo")==-1) {
					jsonArray.put(Honks.get(i));
			}
		}
		JSONObject taggedHonksJSON = new JSONObject();
		taggedHonksJSON.put("taggedHonks", jsonArray);
		return taggedHonksJSON;
	}

	// Remove Method When Database Fully Integrated
 	private void writeToFile() {
 		try {
 			FileWriter outHonk = new FileWriter("honks.txt",false);
 			for(int i=0;i < Honks.size(); i++) {
 				if(i == 0)
 					outHonk.write(Honks.get(i).toString()+"\n");
 				else
 					outHonk.append(Honks.get(i).toString()+"\n");
 			}
 			outHonk.close();
 		}
 		catch(Exception e) { e.printStackTrace(); }
 	}

 	// Remove Method When Database Fully Integrated
 	private void readFile(){
 		try {
 			Honks.clear();
 			BufferedReader Reader = new BufferedReader(new FileReader("honks.txt"));
 			String val;
 			while((val = Reader.readLine()) != null) {
 				Honks.add(new JSONObject(val));
 			}
 			Reader.close();
 			PostC=Honks.size();
 		} catch (IOException e) { e.printStackTrace(); }
 	}

 	private JSONObject getUserHonks() {
 		String username = honkRequestJSON.getString("UserName");

 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i <Honks.size(); i++) {
 			if(username.equals(Honks.get(i).getString("UserName"))&&Honks.get(i).getInt("replyTo")==-1)
 				jsonArray.put(Honks.get(i));
 		}
 		JSONObject userHonksJSON = new JSONObject();
 		userHonksJSON.put("userHonks", jsonArray);
 		return userHonksJSON;
 	}

 	private void postHonk() {
 		JSONObject honkJSON = new JSONObject(honkRequestJSON.getString("Honk"));
 		honkJSON.put("id",PostC);
 		PostC++;
 		honkJSON.put("replyTo",-1);
 		Honks.add(honkJSON);
 		writeToFile();
 	}
 	private void postReply() {
 		JSONObject honkJSON = new JSONObject(honkRequestJSON.getString("Honk"));
 		honkJSON.put("id",PostC);
 		PostC++;
 		honkJSON.put("replyTo",honkRequestJSON.getInt("replyTo"));
 		Honks.add(honkJSON);
 		writeToFile();
 	}
 	private void updateHonk() {
 		JSONObject honkJSON = new JSONObject(honkRequestJSON.getString("Honk"));
 		for(int i = 0; i < Honks.size(); i++) {
 			if(Honks.get(i).getString("UserName").equalsIgnoreCase(honkJSON.getString("UserName")) &&
 					Honks.get(i).getString("content").equalsIgnoreCase(honkJSON.getString("content")) &&
 					(Honks.get(i).getInt("numLikes") + 1) == (honkJSON.getInt("numLikes"))
 			){	
 				Honks.set(i, honkJSON);
 			}
 		}
 		writeToFile();
 	}

 	private JSONObject getHonkList() {
 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i <Honks.size(); i++) {
 			if(Honks.get(i).getInt("replyTo")==-1)
 				jsonArray.put(Honks.get(i));
 		}
 		JSONObject allHonksJSON = new JSONObject();
 		allHonksJSON.put("allHonks", jsonArray);
 		return allHonksJSON;
 	}

 	private JSONObject getHashTagHonkList() {
 		String hashtag = honkRequestJSON.getString("value");

 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i < Honks.size(); i++) {
 			String honkText = Honks.get(i).getString("content");
 		    Matcher hashtagMatcher = Pattern.compile("(#\\w+)\\b").matcher(honkText);
 		    while(hashtagMatcher.find()) {
 		        if(hashtag.equalsIgnoreCase(hashtagMatcher.group().strip())&&Honks.get(i).getInt("replyTo")==-1) {
 		        	jsonArray.put(Honks.get(i));
 		        	break;
 		        }
 		    }
 		}
 		JSONObject htHonksJSON = new JSONObject();
 		htHonksJSON.put("hashtagHonks", jsonArray);
 		return htHonksJSON;
 	}
 	
	private JSONObject getFollowedHonks() {
		JSONArray followedUsers = honkRequestJSON.getJSONArray("followedUsers"); 
		
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i <Honks.size(); i++) {
			for(int j = 0; j < followedUsers.length(); ++j) {
				if(Honks.get(i).getString("UserName").equals(followedUsers.getString(j))&&Honks.get(i).getInt("replyTo")==-1) {
					jsonArray.put(Honks.get(i));
				}
			}
		}
		JSONObject userHonksJSON = new JSONObject();
		userHonksJSON.put("followedHonks", jsonArray);
		return userHonksJSON;
	}
	private JSONObject getReplies() {
		int id = honkRequestJSON.getInt("id"); 
		JSONArray jsonArray = new JSONArray();
			for(int j = 0; j < Honks.size(); ++j) {
				if(Honks.get(j).getInt("replyTo")==id) {
					jsonArray.put(Honks.get(j));
				}
		}
		JSONObject userHonksJSON = new JSONObject();
		userHonksJSON.put("Honks", jsonArray);
		return userHonksJSON;
	}
 	private JSONObject getSuccessResponse() {
 		JSONObject successJSON = new JSONObject();
 		successJSON.put("isSuccess", true);
 		return successJSON;
 	}

 	private JSONObject getFailureResponse() {
 		JSONObject successJSON = new JSONObject();
 		successJSON.put("isSuccess", false);
 		return successJSON;
 	}

 }
