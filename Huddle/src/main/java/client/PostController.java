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
	@FXML TextArea writeHonkTextArea;
	
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/HomeScreenUsr");
    }
    @FXML
    private void post() throws IOException{
    	String content = writeHonkTextArea.getText();
    	Date publishDate = new Date();
    	publishDate.setToCurrentDate();
    	String value = "";
    	
		JSONObject JSON = new JSONObject();
    	ClientCommunication sut = new ClientCommunication();
    	JSON.put("id", 7);
    	JSON.put("UserName", App.currentUser);
    	JSON.put("text", content);
    	JSON.put("publish_date", publishDate.toString());
    	
		sut.sendJSONRequestToServer(JSON);
		System.out.println(JSON);
		
		switchToHome();
    }
}