package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServerRequest implements Runnable {
	
	private Socket huddleSocket;
	private Scanner clientInput;
	private PrintWriter clientOutput;
	
	public ServerRequest(Socket huddleSocket) {
		this.huddleSocket = huddleSocket;
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
		
		// Test response to test request
		if(jsonRequest.getString("type").equals("testRequest")) {
			sendJSONResponseToClient(getTestJSONResponse());
		}
		
		return;
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
	
	// ---------- Temporary Testing Method	------------
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
