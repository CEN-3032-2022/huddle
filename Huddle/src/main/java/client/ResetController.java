package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ResetController {
	@FXML TextField usernameTextField;
	@FXML TextField passwordTextField;
	@FXML TextField recAns1TextField;
	@FXML TextField recAns2TextField;
	
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
 }