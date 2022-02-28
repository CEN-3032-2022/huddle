package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
		catch (IOException e) {
			System.out.println("Could not connect to the server");
			e.printStackTrace();
		}
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
    	} catch (Exception e) {
    		System.out.println("Could not close connections");
    		e.printStackTrace();
    	}
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
	
	// ---------------- Testing Methods	----------------
	public JSONObject getTestJSONRequest() {
		JSONObject testJSON = new JSONObject();
		testJSON.put("type", "testRequest");
		testJSON.put("isTest", true);
		return testJSON;
	}
	
	public void sendTestJSON() {
		JSONObject testJSON = getTestJSONRequest();
		sendJSONRequestToServer(testJSON);
	}
	// ------------ End Of Testing Methods -------------
}
