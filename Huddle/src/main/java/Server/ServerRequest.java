package Server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Honk;
import client.User;

public class ServerRequest implements Runnable {
	
	private Socket huddleSocket;
	private Scanner clientInput;
	private PrintWriter clientOutput;
	static private ArrayList<JSONObject> Honks=new ArrayList<JSONObject>();
    static private ArrayList<JSONObject> Users=new ArrayList<JSONObject>();
	
	public ServerRequest(Socket huddleSocket) {
		this.huddleSocket = huddleSocket;
	}
	public void writeToFile() {
		try {
		FileWriter outHonk=new FileWriter("honks.txt",false);
		FileWriter outUser=new FileWriter("users.txt",false);
		for(int i=0;i<Honks.size();i++) {
			if(i==0)
				outHonk.write(Honks.get(i).toString()+"\n");
			else
				outHonk.append(Honks.get(i).toString()+"\n");
		}
		for(int i=0;i<Users.size();i++) {
			if(i==0)
				outUser.write(Users.get(i).toString()+"\n");
			else
				outUser.append(Users.get(i).toString()+"\n");
			}
		outUser.close();
		outHonk.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			try {
				setUpCommunications();
	            doRequests();           
			}
			finally { huddleSocket.close(); }
		}
		catch (IOException exception) { exception.printStackTrace(); }
	}
	
	public void doRequests() throws IOException {
		while (true) { 
			if (!clientInput.hasNextLine()) return;
			handleServerRequest(getClientJSONResponse());
		}
	}
	
	public void handleServerRequest(JSONObject jsonRequest) {
		
		// Test responses to test requests
		if(jsonRequest.getString("type").equals("testRequest")) {
			sendJSONResponseToClient(getTestJSONResponse());
		}
		else if(jsonRequest.getString("type").equals("userData")) {
			sendJSONResponseToClient(getTestUserDataJSONResponse());
		}
		else if(jsonRequest.getString("type").equals("HonkList")) {
			sendJSONHonkResponseToClient(getHonkList().toString());
		} else if(jsonRequest.getString("type").equals("UserList")) {
			sendJSONUsersResponseToClient(getUsers().toString());
		}else if(jsonRequest.getString("type").equals("Post")) {
			Post(new JSONObject(jsonRequest.getString("Honk")));
		}else if (jsonRequest.getString("type").equals("NewUser")){
			AddNewUser(new JSONObject(jsonRequest.getString("User")));
		}
		return;
	}
	private void AddNewUser(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		Users.add(jsonObject);
		writeToFile();
		//System.out.print(Honks.toString());
	}
	private void Post(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		Honks.add(jsonObject);
		writeToFile();
		//System.out.print(Honks.toString());
	}
	private void sendJSONHonkResponseToClient(String honkList) {
		// TODO Auto-generated method stub
		clientOutput.println(honkList);
		clientOutput.flush();
	}
	
	private void sendJSONUsersResponseToClient(String userList) {
		// TODO Auto-generated method stub
		clientOutput.println(userList);
		clientOutput.flush();
	}

	public JSONObject getClientJSONResponse() {
		String jsonResponse = clientInput.nextLine();
//		System.out.println("Recieved From Client: " + jsonResponse);
		return new JSONObject(jsonResponse);
	}
	
	public void sendJSONResponseToClient(JSONObject jsonClientResponse) {
//	    System.out.println("Sending To Client: " + jsonClientResponse.toString());
		clientOutput.println(jsonClientResponse.toString());
		clientOutput.flush();
	}
	
	public void setUpCommunications() throws IOException {
		InputStream instream = huddleSocket.getInputStream();
		OutputStream outstream = huddleSocket.getOutputStream();
		this.clientInput = new Scanner(instream, StandardCharsets.UTF_8);
        this.clientOutput = new PrintWriter(outstream);
	}
	public JSONArray getHonkList() {
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i <Honks.size(); i++) {
			jsonArray.put(Honks.get(i));
		}
		return jsonArray;
	}
	
	
	public JSONArray getUsers() {
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i <Users.size(); i++) {
				jsonArray.put(Users.get(i));
			}
			
		return jsonArray;
	}
	// ---------- Temporary Testing Methods	------------
	public JSONObject getTestUserDataJSONResponse() {
		JSONObject testJSONResponse = new JSONObject();
		testJSONResponse.put("type", "userDataResponse");
		testJSONResponse.put("isTest", true);
		return testJSONResponse;
	}
	
	public JSONObject getTestJSONResponse() {
		JSONObject testJSONResponse = new JSONObject();
		testJSONResponse.put("type", "testResponse");
		testJSONResponse.put("isTest", true);
		testJSONResponse.put("number", 12345);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < 10; ++i) {
			jsonArray.put(i);
		}
		testJSONResponse.put("array", jsonArray);
		return testJSONResponse;
	}
	
	// ------------ End Of Testing Methods -------------
	
}
