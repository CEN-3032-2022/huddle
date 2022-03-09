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
	@FXML TextField username;
	@FXML TextField Q1;
	@FXML TextField Q2;
	
	@FXML Text usernameReq1Text;
	@FXML Text pwReq1Text;
	@FXML Text pwReq2Text;
	@FXML Text pwReq3Text;
	@FXML Text takenUsernameWarningText;
	@FXML Text invalidAccountInfoText;
	@FXML Text blankRecoveryAnswersWarningText;
	
	private int MAX_USERNAME_CHAR_LENGTH = 20;
	private int MAX_PASSWORD_CHAR_LENGTH = 20;
	private int MAX_REC_ANSWER_CHAR_LENTH = 30;
	
	private String LIGHT_RED = "#f24b4e";
	private String LIGHT_GREEN = "#6eff72";
	
	private AccountCreationValidator accValidator;
	
	public AccountCreationController() {
		accValidator = new AccountCreationValidator();
	}
	
    @FXML
    private void createAccountButtonOnClick() throws IOException{
    	
    	if(isRecoveryAnswersBlank()) {
    		blankRecoveryAnswersWarningText.setFill(Color.web(LIGHT_RED));
    		blankRecoveryAnswersWarningText.setVisible(true);
    		return;
    	}
    	else blankRecoveryAnswersWarningText.setVisible(false);

    	if(accValidator.isValidAccount(username.getText(), password.getText())) {
    		invalidAccountInfoText.setVisible(false);
    		takenUsernameWarningText.setVisible(false);
    		// save user info
    	}
    	else {
    		if(!accValidator.isUsernameAvailable(username.getText())) {
    			takenUsernameWarningText.setFill(Color.web(LIGHT_RED));
    			takenUsernameWarningText.setVisible(true);
    		}
    		else {
    			invalidAccountInfoText.setFill(Color.web(LIGHT_RED));
    			invalidAccountInfoText.setVisible(true);
    		}
    	}
    }
    
    @FXML
    private void returnToLoginButtonOnClick() throws IOException {
    	switchToLoginPage();
    }
    
    @FXML
    private void handleOnKeyReleasedUsername() throws IOException{
    	clearExtraCharsFromTextField(username, MAX_USERNAME_CHAR_LENGTH);
    	
    	if(accValidator.isUsernameMeetingCharLengthReq(username.getText()))
    		usernameReq1Text.setFill(Color.web(LIGHT_GREEN));
    	else
    		usernameReq1Text.setFill(Color.web(LIGHT_RED));
    }
    
    @FXML
    private void handleOnKeyReleasedPassword() throws IOException{
    	clearExtraCharsFromTextField(password, MAX_PASSWORD_CHAR_LENGTH);
    	
        String passwordStr = password.getText();
        
    	if(accValidator.isPwMeetingCapLetterReq(passwordStr))
    		pwReq1Text.setFill(Color.web(LIGHT_GREEN));
    	else
    		pwReq1Text.setFill(Color.web(LIGHT_RED));
    	
    	if(accValidator.isPwMeetingDigitReq(passwordStr))
    		pwReq2Text.setFill(Color.web(LIGHT_GREEN));
    	else
    		pwReq2Text.setFill(Color.web(LIGHT_RED));
    	
    	if(accValidator.isPwMeetingCharLengthReq(passwordStr))
    		pwReq3Text.setFill(Color.web(LIGHT_GREEN));
    	else
    		pwReq3Text.setFill(Color.web(LIGHT_RED));
    }
    
    @FXML
    private void handleOnKeyReleasedRecoveryAnswer() {
    	clearExtraCharsFromTextField(Q1, MAX_REC_ANSWER_CHAR_LENTH);
    	clearExtraCharsFromTextField(Q2, MAX_REC_ANSWER_CHAR_LENTH);
    }
    
    private void switchToLoginPage() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    
    private void clearExtraCharsFromTextField(TextField textField, int maxCharLength) {
    	if(textField.getText().length() > maxCharLength) {
    		textField.setText(textField.getText().substring(0, maxCharLength));
    		textField.positionCaret(maxCharLength);
    	}
    }
    
    private boolean isRecoveryAnswersBlank() {
    	return (Q1.getText().isEmpty() || Q2.getText().isEmpty());
    }
}