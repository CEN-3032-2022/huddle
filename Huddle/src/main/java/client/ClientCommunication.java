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
	
    private static ClientCommunication clientComm;
    
	private Socket clientSocket;
	private Scanner serverInput;
	private PrintWriter serverOutput;
	
	public static ClientCommunication getInstance() {
		if(clientComm == null) {
			clientComm = new ClientCommunication();
		}
		return clientComm;
	}
	
	public static void closeInstance() {
		if(clientComm != null) {
			clientComm.closeCommunications();
			clientComm = null;
		}
	}
	
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
		return new JSONObject(jsonResponse);
	}

	public void sendJSONRequestToServer(JSONObject jsonServerRequest) {
		serverOutput.println(jsonServerRequest.toString());
		serverOutput.flush();
	}
}
