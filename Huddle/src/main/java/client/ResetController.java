package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ResetController {
	@FXML TextArea password;
	@FXML TextArea q1;
	@FXML TextArea q2;
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void switchToErr() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/ResetFail");
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
 }