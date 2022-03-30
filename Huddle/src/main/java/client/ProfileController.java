package client;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
public class ProfileController{
	@FXML ScrollPane honkScrollPaneContainer;
	static String holder;
	@FXML
	private Text UserName;
	@FXML
	private Text bioText;
	@FXML public void initialize(){
		UserName.setText(holder);
		String value = "";
		JSONObject JSON = new JSONObject();
		JSON.put("type", "UserList");
		JSON.put("isTest", false);
		ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
		value=ClientCommunication.getInstance().getServerUsersJSONResponse();
		JSONArray Arr=new JSONArray(value);		
		for(int i = 0; i < Arr.length(); i++) {
			if(Arr.getJSONObject(i).getString("UserName").equals(UserName.getText())) {
				bioText.setText(Arr.getJSONObject(i).getString("bio"));
				}
			}
		setField();
	}
	@FXML
	private void setField() {
		String value=""; 
		JSONObject JSON = new JSONObject();
		JSON.put("type", "HonkList");
		JSON.put("isTest", false);
    	ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
    	value=ClientCommunication.getInstance().getServerUsersJSONResponse();
		JSONArray Arr=new JSONArray(value);
		GridPane tPane=new GridPane();
		for(int i=0;i<Arr.length();i++) {
			if(Arr.getJSONObject(i).getString("UserName").equals(UserName.getText())) {
				tPane.add(new Text(Arr.getJSONObject(i).getString("UserName")), 0, (i*4)+0);
				tPane.add(new Text(Arr.getJSONObject(i).getString("date")), 3, (i*4)+0);
				tPane.add(new Text(Arr.getJSONObject(i).getString("content")), 1, (i*4)+1);
			}
		}
		honkScrollPaneContainer.setContent(tPane);
	}
    @FXML
    private void switchToMain() throws IOException {
    	App.setRoot("/fxml/HomeScreenUsr");
    }
 }