package client;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LogInController {
	@FXML Text wrongLogin;
	@FXML PasswordField password;
	@FXML TextField userName;
	
	@FXML
	public void initialize() {
	}
    @FXML
    private void switchToNew() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/CreateAcc");
    }
    @FXML
    private void switchToReset() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/ResetPassword");
    }
    @FXML
    private void check() throws IOException {
    	boolean correct = false;
    	
    	String value = "";
		JSONObject JSON = new JSONObject();
    	ClientCommunication sut = new ClientCommunication();
		JSON.put("type", "UserList");
		JSON.put("isTest", false);
		sut.sendJSONRequestToServer(JSON);
		value=sut.getServerUsersJSONResponse();
		JSONArray Arr=new JSONArray(value);
		System.out.println(Arr.getJSONObject(0).toString());
		
		for(int i = 0; i < Arr.length(); i++) {
			if(Arr.getJSONObject(i).getString("UserName").equals(userName.getText())) {
				if(Arr.getJSONObject(i).getString("Password").equals(password.getText())) {
					correct=true;
					App.currentUser = userName.getText();
					break;
				}
			}
		}
				
//    	if(password.getText().equals("")&&userName.getText().equals(""))
//    		correct=true;
//    	else
//    		correct=false;
    	if(correct) {
    		wrongLogin.setVisible(false);
        	App.setRoot("/Group7/Huddle/UserInterface/HomeScreenUsr");
    	}
    	else {
    		wrongLogin.setVisible(true);
    	}
    }
    @FXML
    private void limit() throws IOException{
    	if(password.getText().length()>20) {
    		password.setText(password.getText().substring(0, 20));
    		password.positionCaret(20);
    	}
    	if(userName.getText().length()>20) {
    		userName.setText(userName.getText().substring(0, 20));
    		userName.positionCaret(20);
    	}
    }
}
