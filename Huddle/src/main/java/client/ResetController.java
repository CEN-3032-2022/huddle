package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ResetController {
	@FXML Text pwReqText;

	@FXML TextField usernameTextField;
	@FXML TextField passwordTextField;
	@FXML TextField recAns1TextField;
	@FXML TextField recAns2TextField;
	
	private int MAX_PASSWORD_CHAR_LENGTH = 20;
	
	private String LIGHT_RED = "#f24b4e";
	private String LIGHT_GREEN = "#6eff72";
	
    @FXML
    private void switchToReset() throws IOException {
        App.setRoot("/fxml/ResetPassword");
    }
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToSuccess() throws IOException {
        App.setRoot("/fxml/ResetSuccess");
    }
    @FXML
    private void switchToErr() throws IOException {
        App.setRoot("/fxml/ResetFail");
    }
    @FXML
    private void check() throws IOException {
    	String username = usernameTextField.getText();
    	String newPassword = passwordTextField.getText();
    	String recAnswer1 = recAns1TextField.getText();
    	String recAnswer2 = recAns2TextField.getText();
    	
        AccountCreationValidator accValidator = new AccountCreationValidator();
    	if(!accValidator.isValidPassword(newPassword)) {
    		switchToErr();
    		return;
    	}
    	
		UserRepositoryImp userRepo = new UserRepositoryImp();
    	if(userRepo.updatePassword(username, newPassword, recAnswer1, recAnswer2))
    		switchToSuccess();
    	else
    		switchToErr();
        	
    }
    
    @FXML
    private void returnToLoginPage() throws IOException{
    	switchToLogin();
    }
    
    @FXML
    private void handleOnKeyReleasedPassword() throws IOException{
    	clearExtraCharsFromTextField(passwordTextField, MAX_PASSWORD_CHAR_LENGTH);
    	
        String passwordStr = passwordTextField.getText();
        AccountCreationValidator accValidator = new AccountCreationValidator();
        
    	if(accValidator.isValidPassword(passwordStr))
    		pwReqText.setFill(Color.web(LIGHT_GREEN));
    	else
    		pwReqText.setFill(Color.web(LIGHT_RED));
    }
    
    private void clearExtraCharsFromTextField(TextField textField, int maxCharLength) {
    	if(textField.getText().length() > maxCharLength) {
    		textField.setText(textField.getText().substring(0, maxCharLength));
    		textField.positionCaret(maxCharLength);
    	}
    }
 }