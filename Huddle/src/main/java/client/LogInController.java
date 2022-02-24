package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
		App.setUserAgentStylesheet("file:src/main/resources/Group7/Huddle/UserInterface/theme1.css");
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
    		wrongLogin.setFill(Color.RED);
    		wrongLogin.setVisible(true);
    	}
    }
}
