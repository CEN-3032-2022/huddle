package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
				clientInput = new Scanner(huddleSocket.getInputStream());
	            clientOutput = new PrintWriter(huddleSocket.getOutputStream());
	            doRequest();           
			}
			finally {
				huddleSocket.close();
			}
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void doRequest() throws IOException {
		
//		while (true) {  
//			if (!clientInput.hasNextLine()) {
//				return;
//			}
//			
//			String command = clientInput.nextLine();  
//			handleServerRequest(command);
//		}
		
	}
	
	public void handleServerRequest(String command) {
		
//		dbOperations.getDbRequestLock().lock();
//		
//		System.out.println("**Command sent by client is [" + command +"]");
//		
//		if (command.equals("print")) {
//			musicTables.printAllTableData();
//			clientOutput.println("print command recieved");
//			clientOutput.flush();
//		}
//		else if (command.contains("query")) {
//			String queryParameters = command.substring(6);
//			
//			String queryResults = "";
//			
//			try {
//				queryResults = dbOperations.getMusicSelectionQueryResultsString(musicTables, queryParameters);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			System.out.println("**Sending Back To Client:[\n" + queryResults.replace("<newline>", "\n") + "]");
//			
//			clientOutput.println(queryResults);
//			clientOutput.flush();
//			return;
//			
//		}
//		else {
//			clientOutput.println("Invalid command recieved");
//			clientOutput.flush();
//			return;
//		}
//		
//		dbOperations.getDbRequestLock().unlock();
		
	}
	
}
