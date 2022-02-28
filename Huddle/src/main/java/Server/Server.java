package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int HUDDLE_PORT = 8888;

	public static void main(String[] args) {

		try {
			// Initialize Database connection here probably
			
			ServerSocket huddleServer = new ServerSocket(HUDDLE_PORT);
			System.out.println("Waiting for clients to connect...");
			
		    while (true) {
		    	Socket huddleSocket = huddleServer.accept();
		    	System.out.println("A client has connected");
		    	
		    	ServerRequest serverRequest = new ServerRequest(huddleSocket);
		    	Thread requestBusinessLogic = new Thread(serverRequest);
		    	requestBusinessLogic.start();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// Any closing statements
		}
		
	}

}