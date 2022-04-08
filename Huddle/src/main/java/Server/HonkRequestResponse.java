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
 				postHonk();
 				return getSuccessResponse();
 			case "hashtagSearch":
 				return getHashTagHonkList();
 			case "usrHonks":
 				return getUserHonks();
 			case "Update": 
 				updateHonk();
 				return getHonkList();
 		}

 		return getFailureResponse();
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
 		} catch (IOException e) { e.printStackTrace(); }
 	}

 	private JSONObject getUserHonks() {
 		String username = honkRequestJSON.getString("UserName");

 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i <Honks.size(); i++) {
 			if(username.equals(Honks.get(i).getString("UserName")))
 				jsonArray.put(Honks.get(i));
 		}
 		JSONObject userHonksJSON = new JSONObject();
 		userHonksJSON.put("userHonks", jsonArray);
 		return userHonksJSON;
 	}

 	private void postHonk() {
 		JSONObject honkJSON = new JSONObject(honkRequestJSON.getString("Honk"));
 		Honks.add(honkJSON);
 		writeToFile();
 	}

 	private void updateHonk() {
 		JSONObject honkJSON = new JSONObject(honkRequestJSON.getString("Honk"));
 		for(int i = 0; i < Honks.size(); i++) {
 			if(Honks.get(i).getString("UserName").compareTo(honkJSON.getString("UserName")) == 0) {	
 				Honks.set(i, honkJSON);
 			}
 		}
 		writeToFile();
 		System.out.println(honkJSON);
 	}

 	private JSONObject getHonkList() {
 		JSONArray jsonArray = new JSONArray();
 		for(int i = 0; i <Honks.size(); i++) {
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
 		        if(hashtag.equalsIgnoreCase(hashtagMatcher.group().strip())) {
 		        	jsonArray.put(Honks.get(i));
 		        	break;
 		        }
 		    }
 		}
 		JSONObject htHonksJSON = new JSONObject();
 		htHonksJSON.put("hashtagHonks", jsonArray);
 		return htHonksJSON;
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