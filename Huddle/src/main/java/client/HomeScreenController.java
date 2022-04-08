package client;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HomeScreenController{
	HonkRetriever honkRtr;
	boolean isMasterWall;
	
	@FXML ScrollPane honkScrollPaneContainer;
	@FXML TextField searchBar;
	@FXML Text UserName;
	@FXML Text bioText;
	@FXML public void initialize(){
		honkRtr = new HonkRetriever();
		searchBar.setPromptText("#hashtag to seach for honks with containing the hashtag");
		UserName.setText(App.currentUser.getString("UserName"));
		bioText.setText(App.currentUser.getString("bio"));
		switchToFollowed();
	}
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToWall() throws IOException {
		JSONArray allHonks = honkRtr.getAllHonks();
		honkScrollPaneContainer.setContent(createHonksGridpane(allHonks));
		isMasterWall = true;
    }
    @FXML
    private void switchToFollowed() {
		JSONArray followedHonks = honkRtr.getAllFollowedUsersHonks(App.currentUser.getString("UserName"));
		honkScrollPaneContainer.setContent(createHonksGridpane(followedHonks));
		isMasterWall = false;
    }
    @FXML
    private void search() {
    	String searchText = searchBar.getText();
    	if(searchText.startsWith("#")) {
    		String hashtag = searchText.stripTrailing();
    		HonkRetriever honksRtr = new HonkRetriever();
    		JSONArray hashtagHonks = honksRtr.getHashtagHonks(hashtag);
    		honkScrollPaneContainer.setContent(createHonksGridpane(hashtagHonks));
    	}
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
    
    private GridPane createHonksGridpane(JSONArray honksData) {
		GridPane honksPane = new GridPane();
		for(int i=0;i<honksData.length();i++) {
			honksPane.add(new Text(honksData.getJSONObject(i).getString("UserName")), 0, (i*4)+0);
			honksPane.add(new Text(honksData.getJSONObject(i).getString("date")), 3, (i*4)+0);
			honksPane.add(new Text(honksData.getJSONObject(i).getString("content")), 1, (i*4)+1);
			honksPane.add(new Text("Likes: " + honksData.getJSONObject(i).getInt("numLikes")), 3, (i*4)+1);
			final String name = honksData.getJSONObject(i).getString("UserName");
			honksPane.add(createViewProfileButton(name), 0,(i*4)+2);
			honksPane.add(createLikeButton(honksData.getJSONObject(i)), 0, (i*4)+2);
		}
    	return honksPane;
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
					if(isMasterWall) switchToWall();
					else switchToFollowed();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    	
    	return likeButton;
    }
    
    private Button createViewProfileButton(String name) {
    	Button viewProfileButton = new Button("View Profile");
    	viewProfileButton.getStyleClass().clear();
    	viewProfileButton.getStyleClass().add("profileButton");
        viewProfileButton.setOnAction(new EventHandler<>(){
			@Override
			public void handle(ActionEvent event) {
				try {
					ProfileController.holder=name;
					App.setRoot("/fxml/Profile");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        });
    	return viewProfileButton;
    }
 }