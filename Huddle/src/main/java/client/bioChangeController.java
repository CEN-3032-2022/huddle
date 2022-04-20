package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bioChangeController {
	private int MAX_BIO_CHAR_LENTH = 250;
	@FXML TextArea writeBioTextArea;

	@FXML
    private void switchToHome() throws IOException {
        App.setRoot("/fxml/HomeScreenUsr");
    }
    @FXML
    private void changeBio() throws IOException{
    	UserRepositoryImp userRep = new UserRepositoryImp();
    	String newBio = writeBioTextArea.getText();
    	
    	if (userRep.updateBio(App.currentUser.getUsername(), newBio)) {
    		App.currentUser = userRep.getUserByUsername(App.currentUser.getUsername());
    		switchToHome();
    	}
    }
    @FXML
    private void handleOnKeyReleasedBio() throws IOException{
    	clearExtraCharsFromTextArea(writeBioTextArea, MAX_BIO_CHAR_LENTH);
    }
    
    private void clearExtraCharsFromTextArea(TextArea textArea, int maxCharLength) {
    	if(textArea.getText().length() > maxCharLength) {
    		textArea.setText(textArea.getText().substring(0, maxCharLength));
    		textArea.positionCaret(maxCharLength);
    	}
    }
    
    
}