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
		JSONObject JSON = new JSONObject();
    	JSON.put("id", 7);
    	JSON.put("UserName", App.currentUser);
    	JSON.put("content", content);
    	JSON.put("date", publishDate.toString());
    	JSONObject JSON2=new JSONObject();
    	JSON2.put("type", "Post");
		JSON2.put("isTest", false);
		JSON2.put("Honk", JSON.toString());
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON2);
		
		switchToHome();
    }
}