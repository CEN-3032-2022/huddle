package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class FileDataRetriever {

	public String FILE_DIRECTORY_PATH = "/Group7/Huddle/DataFiles/";
	
	BufferedReader reader;
	
	public FileDataRetriever() {
		reader = null;
	}
	
	private void setReaderFile(String fileName) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(FILE_DIRECTORY_PATH + fileName));
	}
	
	private JSONObject searchJSONFileForMatch(String JSONKey, String uniqueValue) throws IOException {
		
		String JSONDataString = reader.readLine();
		while (JSONDataString != null) {
			JSONObject JSONData = new JSONObject(JSONDataString);
			if(JSONData.getString(JSONKey).equals(uniqueValue)) {
				return JSONData;
			}
			JSONDataString = reader.readLine();
		}
		
		return null;
	}
	
	private JSONArray searchJSONFileForAllMatches(String JSONKey, String value) throws IOException {
		JSONArray allMatches = new JSONArray();
		
		String JSONDataString = reader.readLine();
		while (JSONDataString != null) {
			JSONObject JSONData = new JSONObject(JSONDataString);
			if(JSONData.getString(JSONKey).equals(value)) {
				allMatches.put(JSONData);
			}
			JSONDataString = reader.readLine();
		}
		
		if(allMatches.length() == 0) return null;
		else return allMatches;
	}
	
	private JSONArray getAllJSONDataFromFile() throws IOException {
		JSONArray allData = new JSONArray();
		
		String JSONDataString = reader.readLine();
		while (JSONDataString != null) {
			JSONObject JSONData = new JSONObject(JSONDataString);
			allData.put(JSONData);
			JSONDataString = reader.readLine();
		}
		
		if(allData.length() == 0) return null;
		else return allData;
	}
	
	JSONObject getUserByUsername(String username) throws IOException {
		setReaderFile("users.txt");
		JSONObject foundUserJSON = searchJSONFileForMatch("username", username);
		return foundUserJSON;
	}
	
	JSONArray getAllUsers() throws IOException {
		setReaderFile("users.txt");
		JSONArray allUserJSON = getAllJSONDataFromFile();
		return allUserJSON;
	}
	
	public JSONArray getAllHonks() throws IOException {
		setReaderFile("honks.txt");
		JSONArray allHonksJSON = getAllJSONDataFromFile();
		return allHonksJSON;
	}
	
	public JSONArray getAllHonksFromUser(String username) throws IOException {
		setReaderFile("honks.txt");
		JSONArray allUsersHonksJSON = searchJSONFileForAllMatches("userName", username);
		return allUsersHonksJSON;
	}
}
