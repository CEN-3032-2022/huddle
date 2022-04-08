package client;

import java.io.IOException;
import java.util.ArrayList;

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
		UserName.setText(App.currentUser.getUsername());
		bioText.setText(App.currentUser.getBio());
		switchToFollowed();
	}
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToWall() throws IOException {
    	HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
		ArrayList<Honk> honks = honksRtr.getHonkList();
		honkScrollPaneContainer.setContent(createHonksGridpane(honks));
		isMasterWall = true;
    }
    @FXML
    private void switchToFollowed() {
    	HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
		ArrayList<Honk> honks = honksRtr.getFollowedHonks(App.currentUser.getUsername());
		honkScrollPaneContainer.setContent(createHonksGridpane(honks));
		isMasterWall = false;
    }
    @FXML
    private void search() {
    	String searchText = searchBar.getText();
    	if(searchText.startsWith("#")) {
    		String hashtag = searchText.stripTrailing();
    		HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
    		ArrayList<Honk> honks = honksRtr.getHashtagHonkList(hashtag);
    		honkScrollPaneContainer.setContent(createHonksGridpane(honks));
    	}
    	else{
    		String name = searchText.stripTrailing();
    		HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
    		ArrayList<Honk> honks = honksRtr.getUserHonks(name);
    		UserRepositoryImp x = new UserRepositoryImp();
    		User usr = x.getUserByUsername(searchText);
    		if(honks.size()>0&&usr.getUsername().equals(name))
    			honkScrollPaneContainer.setContent(createUsrGridpane(usr,honks));
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
    private GridPane createUsrGridpane(User usr,ArrayList<Honk> honks) {
		GridPane honksPane = new GridPane();
		honksPane.add(new Text(usr.getUsername()+" Followers: "+usr.getFollowerCount()+"\n"), 0, 0);
		honksPane.add(createViewProfileButton(usr.getUsername()), 0,2);
		for(int i=0;i<honks.size();i++) {
			honksPane.add(new Text(honks.get(i).getUserName()), 0, (i*4)+3);
			honksPane.add(new Text(honks.get(i).getPublishDate().toString()), 3, (i*4)+3);
			honksPane.add(new Text(honks.get(i).getContent()), 0, (i*4)+4);
			honksPane.add(new Text("Likes: " + honks.get(i).getNumLikes()), 3, (i*4)+4);
			final String name = honks.get(i).getUserName();
			honksPane.add(createViewProfileButton(name), 0,(i*4)+5);
			honksPane.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 1, (i*4)+5);
		}
    	return honksPane;
    }
    private GridPane createHonksGridpane(ArrayList<Honk> honks) {
		GridPane honksPane = new GridPane();
		for(int i=0;i<honks.size();i++) {
			honksPane.add(new Text(honks.get(i).getUserName()), 0, (i*4)+0);
			honksPane.add(new Text(honks.get(i).getPublishDate().toString()), 3, (i*4)+0);
			honksPane.add(new Text(honks.get(i).getContent()), 0, (i*4)+1);
			honksPane.add(new Text("Likes: " + honks.get(i).getNumLikes()), 3, (i*4)+2);
			final String name = honks.get(i).getUserName();
			honksPane.add(createViewProfileButton(name), 0,(i*4)+2);
			honksPane.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 1, (i*4)+2);
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
    	Button viewProfileButton = new Button("Profile");
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