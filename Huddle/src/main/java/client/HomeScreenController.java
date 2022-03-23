package client;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
public class HomeScreenController{
	@FXML ScrollPane honkScrollPaneContainer;
	@FXML
	public static Text UserName= new Text();
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToWall() throws IOException {
    	String value=""; 
		JSONObject JSON = new JSONObject();
		JSON.put("type", "HonkList");
		JSON.put("isTest", false);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	value=ClientCommunication.getInstance().getServerUsersJSONResponse();
		JSONArray Arr=new JSONArray(value);
		GridPane tPane=new GridPane();
		for(int i=0;i<Arr.length();i++) {
			tPane.add(new Text(Arr.getJSONObject(i).getString("UserName")), 0, (i*4)+0);
			tPane.add(new Text(Arr.getJSONObject(i).getString("date")), 3, (i*4)+0);
			tPane.add(new Text(Arr.getJSONObject(i).getString("content")), 1, (i*4)+1);
		}
		honkScrollPaneContainer.setContent(tPane);
    }
    @FXML
    private void switchToLiked() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToTagged() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToLogOut() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToPost() throws IOException {
        App.setRoot("/fxml/post");
    }
    @FXML
    private void switchToBio() throws IOException {
        App.setRoot("/fxml/bio");
    }
    @FXML
    private void switchTheme() throws IOException {
    	if(App.getUserAgentStylesheet().contains("1"))
    		App.setUserAgentStylesheet("file:src/main/resources/css/theme2.css");
    	else
    		App.setUserAgentStylesheet("file:src/main/resources/css/theme1.css");
    }
    @FXML
    private void search() {
    	
    }
 }