package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreationController {
	@FXML TextField password;
	@FXML TextField userName;
	@FXML TextField Q1;
	@FXML TextField Q2;
	@FXML Text req1;
	@FXML Text req2;
	@FXML Text req3;
	@FXML Text taken;
	@FXML int pass;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void handleOnKeyReleasedUsrName() throws IOException{
    	if(userName.getText().length() > 20) {
    		userName.setText(userName.getText().substring(0, 20));
    		userName.positionCaret(20);
    	}
    }
    @FXML
    private void limit() {
    	if(Q2.getText().length() > 30) {
    		Q2.setText(Q2.getText().substring(0, 30));
    		Q2.positionCaret(30);
    	}
    	if(Q1.getText().length() > 30) {
    		Q1.setText(Q1.getText().substring(0, 30));
    		Q1.positionCaret(30);
    	}
    }
    @FXML
    private void handleOnKeyReleasedPassword() throws IOException{
    	if(password.getText().length()>20) {
    		password.setText(password.getText().substring(0, 20));
    		password.positionCaret(20);
    	}
    	Pattern pattern1 = Pattern.compile("[A-Z]");
    	Pattern pattern2 = Pattern.compile("[0-9]");
    	Pattern pattern3 = Pattern.compile("^([A-Z]|[0-9]|[a-z]|[/*,/@,/$,/!]){7,20}$");
        Matcher matcher1 = pattern1.matcher(password.getText());
        Matcher matcher2 = pattern2.matcher(password.getText());
        Matcher matcher3 = pattern3.matcher(password.getText());
    	if(matcher1.find()) {
    		if(App.getUserAgentStylesheet().contains("2"))
    			req1.setVisible(false);
    		else
    			req1.setFill(Color.DARKGREEN);
    	} else {
    		if(App.getUserAgentStylesheet().contains("2"))
    			req1.setVisible(true);
    		else
    			req1.setFill(Color.BLACK);
    	}
    	
    	if(matcher2.find()) {
    		if(App.getUserAgentStylesheet().contains("2"))
    			req2.setVisible(false);
    		else
    			req2.setFill(Color.DARKGREEN);
    	} else {
    		if(App.getUserAgentStylesheet().contains("2"))
    			req2.setVisible(true);
    		else
    			req2.setFill(Color.BLACK);
    	}
    	
    	if(matcher3.find()) {
    		if(App.getUserAgentStylesheet().contains("2"))
    			req3.setVisible(false);
    		else
    			req3.setFill(Color.DARKGREEN);
    	} else {
    		if(App.getUserAgentStylesheet().contains("2"))
    			req3.setVisible(true);
    		else
    			req3.setFill(Color.BLACK);
    	}
    	
    }
    @FXML
    private void Confirm() throws IOException{
    	if((userName.getText().length()>=5&&userName.getText().length()<=20)) {
    		if(App.getUserAgentStylesheet().contains("1"))
    			taken.setFill(Color.RED);
    		taken.setVisible(true);
    	}
    	else
    		switchToPrimary();
    }
}