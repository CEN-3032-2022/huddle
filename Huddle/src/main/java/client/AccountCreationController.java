package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreationController {
	@FXML TextArea password;
	@FXML Text req1;
	@FXML Text req2;
	@FXML Text req3;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void handleOnKeyReleasedUsrName() throws IOException{
    	
    }
    @FXML
    private void handleOnKeyReleasedPassword() throws IOException{
    	Pattern pattern1 = Pattern.compile("[A-Z]");
    	Pattern pattern2 = Pattern.compile("[0-9]");
    	Pattern pattern3 = Pattern.compile("^([A-Z]|[0-9]|[a-z]|[/*,/@,/$,/!]){7,15}$");
        Matcher matcher1 = pattern1.matcher(password.getText());
        Matcher matcher2 = pattern2.matcher(password.getText());
        Matcher matcher3 = pattern3.matcher(password.getText());
    	if(matcher1.find())
    		req1.setFill(Color.GREEN);
    	else
    		req1.setFill(Color.DARKGOLDENROD);
    	if(matcher2.find())
    		req2.setFill(Color.GREEN);
    	else
    		req2.setFill(Color.DARKGOLDENROD);
    	if(matcher3.find())
    		req3.setFill(Color.GREEN);
    	else
    		req3.setFill(Color.DARKGOLDENROD);
    	
    }
}