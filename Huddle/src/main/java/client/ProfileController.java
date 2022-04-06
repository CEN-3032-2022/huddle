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
	int profileFollowerCount;
	@FXML ScrollPane honkScrollPaneContainer;
	@FXML Button followButton;
	@FXML Text followersText;
	static String holder;
	@FXML
	private Text UserName;
	@FXML
	private Text bioText;
	@FXML public void initialize(){
		if(holder.equalsIgnoreCase(App.currentUser.getString("UserName"))) {
			   followButton.setVisible(false);
		}
		UserName.setText(holder);
		
		JSONObject profileData = getUserProfileData(holder);
		if(isAlreadyFollowingUser()) disableFollowButton();		
		bioText.setText(profileData.getString("bio"));
		profileFollowerCount = profileData.getInt("followerCount");
		followersText.setText("Followers: " + profileFollowerCount);
		setField();
	}
	@FXML
	private void setField() {
		HonkRetriever honksRtr = new HonkRetriever();
		JSONArray Arr = honksRtr.getUsrHonks(holder);
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
    private void followButtonOnClick() {
		JSONObject JSON = new JSONObject();
		JSON.put("type", "user");
		JSON.put("request", "followUser");
		JSON.put("userFollowing", App.currentUser.getString("UserName"));
		JSON.put("userToFollow", holder);
		ClientCommunication.getInstance().sendJSONRequestToServer(JSON);
		ClientCommunication.getInstance().getServerJSONResponse();
		followersText.setText("Followers: " + ++profileFollowerCount);
		disableFollowButton();
    }
    @FXML
    private void switchToMain() throws IOException {
    	App.setRoot("/fxml/HomeScreenUsr");
    }
    
    private boolean isAlreadyFollowingUser() {
		JSONObject currUserData = getUserProfileData(App.currentUser.getString("UserName"));
    	JSONArray followedUsers = currUserData.getJSONArray("usersFollowing");
    	for(int i = 0; i < followedUsers.length(); ++i) {
    		if(holder.equalsIgnoreCase(followedUsers.getString(i))) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private JSONObject getUserProfileData(String username) {
		JSONObject profileRequest = new JSONObject();
		profileRequest.put("type", "user");
		profileRequest.put("request", "getUsr");
		profileRequest.put("UserName", username);
		ClientCommunication.getInstance().sendJSONRequestToServer(profileRequest);
		JSONObject profileData = ClientCommunication.getInstance().getServerJSONResponse();
		return profileData;
    }
    
    private void disableFollowButton() {
    	followButton.setStyle("-fx-background-color: darkgreen;");
    	followButton.setDisable(true);
    	followButton.setText("Followed");
    }
 }