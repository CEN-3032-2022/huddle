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
    private void switchToNew() throws IOException {
        App.setRoot("/fxml/CreateAcc");
    }
    @FXML
    private void switchToReset() throws IOException {
        App.setRoot("/fxml/ResetPassword");
    }
    @FXML
    private void check() throws IOException {
    	boolean correct = false;
    	
    	String value = "";
		JSONObject JSON = new JSONObject();
		JSON.put("type", "user");
		JSON.put("request", "verify");
		JSON.put("Password", password.getText());
		JSON.put("UserName", userName.getText());
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	JSONObject verifyUsersJSON = ClientCommunication.getInstance().getServerJSONResponse();
		JSONArray Arr = verifyUsersJSON.getJSONArray("verifyUsers");
		for(int i = 0; i < Arr.length(); i++) {
			if(Arr.getJSONObject(i).getString("UserName").equals(userName.getText())) {
					correct=true;
					App.currentUser = Arr.getJSONObject(i);
					break;
			}
		}
    	if(correct) {
    		wrongLogin.setVisible(false);
        	App.setRoot("/fxml/HomeScreenUsr");
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
