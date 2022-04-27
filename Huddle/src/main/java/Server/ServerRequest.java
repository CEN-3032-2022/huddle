package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
		ServerResponse serverResponse = null;
		
		if(jsonRequest.getString("type").equalsIgnoreCase("user")) {
			serverResponse = new UserRequestResponse(jsonRequest);
		} else if(jsonRequest.getString("type").equalsIgnoreCase("honk")) {
			serverResponse = new HonkRequestResponse(jsonRequest);
		} else if(jsonRequest.getString("type").equalsIgnoreCase("message")) {
			serverResponse = new MessageRequestResponse(jsonRequest);
		}
		
		sendJSONResponseToClient(serverResponse.getResponse());
		
		return;
	}

	public JSONObject getClientJSONResponse() {
		String jsonResponse = clientInput.nextLine();
		return new JSONObject(jsonResponse);
	}
	
	public void sendJSONResponseToClient(JSONObject jsonClientResponse) {
		clientOutput.println(jsonClientResponse.toString());
		clientOutput.flush();
	}
	
	public void setUpCommunications() throws IOException {
		InputStream instream = huddleSocket.getInputStream();
		OutputStream outstream = huddleSocket.getOutputStream();
		this.clientInput = new Scanner(instream, StandardCharsets.UTF_8);
        this.clientOutput = new PrintWriter(outstream);
	}
}
