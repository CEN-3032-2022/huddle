package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LogInController {
	@FXML Text wrongLogin;
	@FXML TextArea password;
	@FXML TextArea userName;
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
    	boolean correct;
    	if(password.getText().equals("Test")&&userName.getText().equals("Test"))
    		correct=true;
    	else
    		correct=false;
    	if(correct) {
    		wrongLogin.setVisible(false);
        	App.setRoot("/Group7/Huddle/UserInterface/HomeScreenUsr");
    	}
    	else {
    		if(App.getUserAgentStylesheet().contains("2"))
    			wrongLogin.setFill(Color.BLACK);
    		else
    			wrongLogin.setFill(Color.DARKRED);
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
