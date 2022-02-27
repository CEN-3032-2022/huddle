package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;

public class ServerRequest implements Runnable {
	
	private Socket huddleSocket;
	private InputStreamReader clientInput;
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
		this.clientInput = new InputStreamReader(instream);
        this.clientOutput = new OutputStreamWriter(outstream);
        this.streamReader = new BufferedReader(clientInput);
	}
	
	public void doRequests() throws IOException {
		while (true) {  
			if (!clientInput.ready()) {
				return;
			}
			handleServerRequest(getClientJSONResponse());
		}
	}
	
	public JSONObject getClientJSONResponse() {
		StringBuilder responseStringBuilder = new StringBuilder();
		String inputString;
		try {
			while ((inputString = streamReader.readLine()) != null) {
			    responseStringBuilder.append(inputString);
			}
		} catch (IOException e) { e.printStackTrace(); }
		return new JSONObject(responseStringBuilder.toString());
	}
	
	public void handleServerRequest(JSONObject jsonRequest) {
		
		System.out.println(jsonRequest.toString());
		
		return;
	}
	
}
