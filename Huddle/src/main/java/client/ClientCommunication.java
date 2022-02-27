package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientCommunication {

    public static final int HUDDLE_PORT = 8888;
	
	private InputStreamReader input;
	private OutputStreamWriter output;
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
		this.input = new InputStreamReader(instream);
		this.output = new OutputStreamWriter(outstream);
	}
	
	public void closeCommunications() {
    	try {
        	input.close();
        	output.close();
    		clientSocket.close();
    	} catch (Exception e) { System.out.println("Could not close connections"); }
	}
	
}
