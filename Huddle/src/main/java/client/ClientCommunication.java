package client;

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
	
	private InputStreamReader serverInput;
	private OutputStreamWriter serverOutput;
	private Socket clientSocket;
	
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
	}
	
	public void closeCommunications() {
    	try {
    		serverInput.close();
    		serverOutput.close();
    		clientSocket.close();
    	} catch (Exception e) { System.out.println("Could not close connections"); }
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
		try { serverOutput.write(testJSON.toString()); }
		catch (IOException e) { e.printStackTrace(); }
	}
	// End Of Testing Methods
	
}
