package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class ClientCommunication {

    public static final int HUDDLE_PORT = 8888;
	
	private Socket clientSocket;
	private InputStreamReader serverInput;
	private BufferedReader streamReader;
	private OutputStreamWriter serverOutput;
	
	// TESTING
    public static void main(String[] args) {
        // testing main
    	ClientCommunication cc = new ClientCommunication();
    	cc.sendTestJSON();
//    	cc.closeCommunications();
    }
    // TESTING
	
	public ClientCommunication() {
		try {
			clientSocket = new Socket("localhost", HUDDLE_PORT);
			setUpCommunications();
		}
		catch (IOException e) { System.out.println("Could not connect to the server"); }
	}
	
	public void setUpCommunications() throws IOException{
		InputStream instream = clientSocket.getInputStream();
		OutputStream outstream = clientSocket.getOutputStream();
		this.serverInput = new InputStreamReader(instream, StandardCharsets.UTF_8);
		this.serverOutput = new OutputStreamWriter(outstream, StandardCharsets.UTF_8);
        this.streamReader = new BufferedReader(serverInput);
        System.out.println("Communications set up");
	}
	
	public void closeCommunications() {
    	try {
    		serverInput.close();
    		serverOutput.close();
    		clientSocket.close();
    	} catch (Exception e) { System.out.println("Could not close connections"); }
	}
	
	public JSONObject getServerJSONResponse() {
		StringBuilder responseStringBuilder = new StringBuilder();
		String inputString;
		try {
			while ((inputString = streamReader.readLine()) != null) {
			    responseStringBuilder.append(inputString);
			}
			System.out.println("Response String Builder: " + responseStringBuilder.toString());
		} catch (IOException e) { e.printStackTrace(); }
		return new JSONObject(responseStringBuilder.toString());
	}
	
	// Testing Methods
	public JSONObject getTestJSON() {
		JSONObject testJSON = new JSONObject();
		testJSON.put("type", "testJSONObject");
		testJSON.put("helloWho", "HelloWorld");
		testJSON.put("number", 12345);
		testJSON.put("isTest", true);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonArrayContents = new JSONObject();
		for(int i = 0; i < 10; ++i) {
			jsonArrayContents.put("array["+i+"]", i);
		}
		jsonArray.put(jsonArrayContents);
		
		testJSON.put("array", jsonArray);
		
		return testJSON;
	}
	
	public void sendTestJSON() {
		JSONObject testJSON = getTestJSON();
		System.out.println("Sending: " + testJSON.toString());
		try { serverOutput.write(testJSON.toString()); }
		catch (IOException e) { e.printStackTrace(); }
		
		try { Thread.sleep(25000); }
		catch (InterruptedException e) { e.printStackTrace(); }
//		JSONObject jsonResponse = getServerJSONResponse();
//		System.out.println(jsonResponse.toString());
	}
	// End Of Testing Methods
	
}
