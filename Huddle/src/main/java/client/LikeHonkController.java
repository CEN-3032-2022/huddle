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
    	
    	HonkRepositoryImp honkRep = new HonkRepositoryImp();
    	
    	honkRep.updateHonk(honkJSON.getString("UserName"), honkJSON.getString("date"), honkJSON.getString("content"),  honkJSON.getInt("numLikes") + 1);
    }
}