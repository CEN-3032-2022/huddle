package client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class PostController {
	static int replyTo;
	@FXML TextArea writeHonkTextArea;
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("/fxml/HomeScreenUsr");
    }
    @FXML
    private void post() throws IOException{
    	HonkRepositoryImp honkRep = new HonkRepositoryImp();
    	System.out.print(replyTo);
    	String content = writeHonkTextArea.getText();
    	
    	if (honkRep.postHonk(App.currentUser.getUsername(), content,replyTo)) {
    		replyTo=-1;
    		switchToHome();
    	}
    }
}