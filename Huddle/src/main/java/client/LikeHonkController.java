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

public class LikeHonkController {
	@FXML TextArea writeHonkTextArea;
	
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("/fxml/HomeScreenUsr");
    }
    @FXML
    static public void likeHonk(JSONObject honkJSON) throws IOException{
    	
    	JSONObject JSON1 = new JSONObject();
    	JSON1.put("UserName", honkJSON.getString("UserName"));
    	JSON1.put("date", honkJSON.getString("date"));
    	JSON1.put("content", honkJSON.getString("content"));
    	JSON1.put("numLikes", honkJSON.getInt("numLikes") + 1);
    	
    	JSONObject JSON2 = new JSONObject();
    	JSON2.put("type", "honk");
    	JSON2.put("request", "Update");
		JSON2.put("Honk", JSON1.toString());
//		System.out.println(JSON2);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON2);
    	ClientCommunication.getInstance().getServerJSONResponse();
    	
    	
//    	JSONObject postJsonResponse = ClientCommunication.getInstance().getServerJSONResponse();
//		if(postJsonResponse.getBoolean("isSuccess")) switchToHome();
    }
}