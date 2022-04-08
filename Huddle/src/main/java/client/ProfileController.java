package client;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
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
		if(holder.equalsIgnoreCase(App.currentUser.getUsername())) {
			   followButton.setVisible(false);
		}
		UserName.setText(holder);
		
		User profileData = getUserProfileData(holder);
		if(isAlreadyFollowingUser()) disableFollowButton();		
		bioText.setText(profileData.getBio());
		profileFollowerCount = profileData.getFollowerCount();
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
				tPane.add(new Text("Likes: " + Arr.getJSONObject(i).getInt("numLikes")), 3, (i*4)+2);
				tPane.add(createLikeButton(Arr.getJSONObject(i)), 0, (i*4)+2);
			}
		}
		honkScrollPaneContainer.setContent(tPane);
	}
    @FXML
    private void followButtonOnClick() {		
		UserRepositoryImp userRepo = new UserRepositoryImp();
    	userRepo.followUser(App.currentUser.getUsername(), holder);
    	
		followersText.setText("Followers: " + ++profileFollowerCount);
		disableFollowButton();
    }
    @FXML
    private void switchToMain() throws IOException {
    	App.setRoot("/fxml/HomeScreenUsr");
    }
    
    private boolean isAlreadyFollowingUser() {
		User currUserData = getUserProfileData(App.currentUser.getUsername());
    	ArrayList<String> followedUsers = currUserData.getFollowedUsernames();
    	for(int i = 0; i < followedUsers.size(); ++i) {
    		if(holder.equalsIgnoreCase(followedUsers.get(i))) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private User getUserProfileData(String username) {
		UserRepositoryImp userRepo = new UserRepositoryImp();
    	User currUser = userRepo.getUserByUsername(App.currentUser.getUsername());
    	
		return currUser;
    }
    
    private void disableFollowButton() {
    	followButton.setStyle("-fx-background-color: darkgreen;");
    	followButton.setDisable(true);
    	followButton.setText("Followed");
    }

    private Button createLikeButton(JSONObject honkJSON) {
    	Button likeButton = new Button("Like");
    	likeButton.getStyleClass().clear();
    	likeButton.getStyleClass().add("likeButton");
    	
    	likeButton.setOnAction(new EventHandler<>() {
    		@Override
    		public void handle(ActionEvent event) {    			
    			try {
					LikeHonkController.likeHonk(honkJSON);
					setField();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    	
    	return likeButton;
    }
 }