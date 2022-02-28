package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ClientCommunication {

    public static final int HUDDLE_PORT = 8888;
	
	private Socket clientSocket;
	private Scanner serverInput;
	private PrintWriter serverOutput;
	
	public ClientCommunication() {
		try {
			clientSocket = new Socket("localhost", HUDDLE_PORT);
			setUpCommunications();
		}
		catch (IOException e) { System.out.println("Could not connect to the server"); }
	}
	
	public void setUpCommunications() throws IOException {
		InputStream instream = clientSocket.getInputStream();
		OutputStream outstream = clientSocket.getOutputStream();
		this.serverInput = new Scanner(instream, StandardCharsets.UTF_8);
		this.serverOutput = new PrintWriter(outstream);
	}
	
	public void closeCommunications() {
    	try {
    		serverInput.close();
    		serverOutput.close();
    		clientSocket.close();
    	} catch (Exception e) { System.out.println("Could not close connections"); }
	}
	
	public JSONObject getServerJSONResponse() {
		String jsonResponse = serverInput.nextLine(); 
//	    System.out.println("Recieved From Server: " + jsonResponse.toString());
		return new JSONObject(jsonResponse);
	}
	
	public void sendJSONRequestToServer(JSONObject jsonServerRequest) {
//	    System.out.println("Sending To Server: " + jsonServerRequest.toString());
		serverOutput.println(jsonServerRequest.toString());
		serverOutput.flush();
	}
	
	// Testing Methods
    public static void main(String[] args) {
    	ClientCommunication cc = new ClientCommunication();
    	cc.sendTestJSON();
    }
	
	public JSONObject getTestJSON() {
		JSONObject testJSON = new JSONObject();
		testJSON.put("type", "testRequest");
		testJSON.put("isTest", true);
		testJSON.put("number", 12345);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < 10; ++i) {
			jsonArray.put(i);
		}
		testJSON.put("array", jsonArray);
		return testJSON;
	}
	
	public void sendTestJSON() {
		JSONObject testJSON = getTestJSON();
		sendJSONRequestToServer(testJSON);
		JSONObject testResponseJSON = getServerJSONResponse();
	}
	// End Of Testing Methods
	
}
