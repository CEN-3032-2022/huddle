package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ResetController {
	@FXML TextField password;
	@FXML TextField q1;
	@FXML TextField q2;
	
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToErr() throws IOException {
        App.setRoot("/fxml/ResetFail");
    }
    @FXML
    private void check() throws IOException {
    	boolean correct;
    	if(q2.getText().equals("Test")&&q1.getText().equals("Test"))
    		correct=true;
    	else
    		correct=false;
        if(correct)
        	switchToLogin();
        else
        	switchToErr();
    }
    
    @FXML
    private void returnToLoginPage() throws IOException{
    	switchToLogin();
    }
    
    
 }