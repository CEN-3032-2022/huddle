package client;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
public class HomeScreenController {
	@FXML ScrollPane Scroll;
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void switchToWall() throws IOException {
    	String value="";
    	ClientCommunication sut = new ClientCommunication();
		JSONObject JSON = new JSONObject();
		JSON.put("type", "HonkList");
		JSON.put("isTest", false);
		sut.sendJSONRequestToServer(JSON);
		value=sut.getServerHonkListJSONResponse();
		JSONArray Arr=new JSONArray(value);
		GridPane tPane=new GridPane();
		System.out.println(Arr.getJSONObject(0).toString());
		for(int i=0;i<Arr.length()-1;i++) {
			Button Like=new Button("Like");
			Like.getStyleClass().add("likeButton");
			Button Reply=new Button("Reply");
			Reply.getStyleClass().add("replyButton");
			tPane.add(new Text(Arr.getJSONObject(i).getString("UserName")), 0, (i*7)+0);
			tPane.add(new Text(Arr.getJSONObject(i).getString("date")), 3, (i*7)+0);
			tPane.add(new Text(Arr.getJSONObject(i).getString("content")), 1, (i*7)+1,6,5);
			tPane.add(Like,4, (i*7)+6);
			tPane.add(new Label(" "), 2, (i*7)+6);
			tPane.add(Reply, 0, (i*7)+6);
		}
		Scroll.setContent(tPane);
    }
    @FXML
    private void switchToLiked() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void switchToTagged() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void switchToLogOut() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void switchToPost() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/post");
    }
    @FXML
    private void switchToBio() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/bio");
    }
    @FXML
    private void switchTheme() throws IOException {
    	if(App.getUserAgentStylesheet().contains("1"))
    		App.setUserAgentStylesheet("file:src/main/resources/Group7/Huddle/UserInterface/theme2.css");
    	else
    		App.setUserAgentStylesheet("file:src/main/resources/Group7/Huddle/UserInterface/theme1.css");
    }
    @FXML
    private void search() {
    	
    }
 }