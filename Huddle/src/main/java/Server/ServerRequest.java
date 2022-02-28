package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONObject;

public class ServerRequest implements Runnable {
	
	private Socket huddleSocket;
	private InputStreamReader clientInput;
//	private Scanner clientInputScanner;
	private BufferedReader streamReader;
	private OutputStreamWriter clientOutput;
	
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
	
	public void setUpCommunications() throws IOException {
		InputStream instream = huddleSocket.getInputStream();
		OutputStream outstream = huddleSocket.getOutputStream();
		this.clientInput = new InputStreamReader(instream, StandardCharsets.UTF_8);
//		this.clientInputScanner = new Scanner(instream, StandardCharsets.UTF_8);
        this.clientOutput = new OutputStreamWriter(outstream, StandardCharsets.UTF_8);
        this.streamReader = new BufferedReader(clientInput);
	}
	
	public void doRequests() throws IOException {
		while (true) { 
			if (!clientInput.ready()) {
				try {
					System.out.println("Sleeping");
					Thread.sleep(1000);
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
			else {
				System.out.println("Hello");
				handleServerRequest(getClientJSONResponse());
			}
		}
	}
	
	public JSONObject getClientJSONResponse() {
		StringBuilder responseStringBuilder = new StringBuilder();
		String inputString;
		try {
			while ((inputString = streamReader.readLine()) != null) {
			    responseStringBuilder.append(inputString);
			    System.out.println("Input String: " + inputString);
			}
			System.out.println(responseStringBuilder.toString());
		} catch (IOException e) { e.printStackTrace(); }
		return new JSONObject(responseStringBuilder.toString());
	}
	
	public void handleServerRequest(JSONObject jsonRequest) {
		
		System.out.println(jsonRequest.toString());
		if(jsonRequest.get("type") == "testJSONObject") {
			JSONObject testJSONResponse = new JSONObject();
			testJSONResponse.put("type", "testJSONObject");
			testJSONResponse.put("helloWho", "HelloWorld");
			testJSONResponse.put("number", 12345);
			testJSONResponse.put("isTest", true);
		    System.out.println("Sending Back: " + testJSONResponse.toString());
			try { clientOutput.write(testJSONResponse.toString()); }
			catch (IOException e) { e.printStackTrace(); }
		}
		
		return;
	}
	
}
