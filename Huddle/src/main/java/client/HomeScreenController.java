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
    	else if(searchText.startsWith("@")) {
    		String tag = searchText.stripTrailing();
    		HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
    		ArrayList<Honk> honks = honksRtr.getTagHonkList(tag);
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
			honksPane.add(new Text("Likes: " + honks.get(i).getNumLikes()), 4, (i*4)+2);
			final String name = honks.get(i).getUserName();
			honksPane.add(createViewProfileButton(name), 0,(i*4)+2);
			honksPane.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 1, (i*4)+2);
			honksPane.add(createReplyButton(new JSONObject(honks.get(i).toJsonString())), 2, (i*4)+2);
			honksPane.add(createViewRepliesButton(new JSONObject(honks.get(i).toJsonString())), 3, (i*4)+2);
		}
    	return honksPane;
    }
    private GridPane createRepliesGridpane(ArrayList<Honk> honks) {
		GridPane honksPane = new GridPane();
		honksPane.add(new Text(honks.get(0).getUserName()), 0,0);
		honksPane.add(new Text(honks.get(0).getPublishDate().toString()), 3,0);
		honksPane.add(new Text(honks.get(0).getContent()), 0,1);
		honksPane.add(new Text("Likes: " + honks.get(0).getNumLikes()), 4,2);
		honksPane.add(createViewProfileButton(honks.get(0).getUserName()), 0,2);
		honksPane.add(createLikeButton(new JSONObject(honks.get(0).toJsonString())), 1, 2);
		honksPane.add(createReplyButton(new JSONObject(honks.get(0).toJsonString())), 2, 2);
		honksPane.add(createViewRepliesButton(new JSONObject(honks.get(0).toJsonString())), 3, 2);
		for(int i=1;i<honks.size();i++) {
			honksPane.add(new Text(honks.get(i).getUserName()), 1, (i*4)+0);
			honksPane.add(new Text(honks.get(i).getPublishDate().toString()), 4, (i*4)+0);
			honksPane.add(new Text(honks.get(i).getContent()), 1, (i*4)+1);
			honksPane.add(new Text("Likes: " + honks.get(i).getNumLikes()), 5, (i*4)+2);
			final String name = honks.get(i).getUserName();
			honksPane.add(createViewProfileButton(name), 1,(i*4)+2);
			honksPane.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 2, (i*4)+2);
			honksPane.add(createReplyButton(new JSONObject(honks.get(i).toJsonString())), 3, (i*4)+2);
			honksPane.add(createViewRepliesButton(new JSONObject(honks.get(i).toJsonString())), 4, (i*4)+2);
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
    private Button createReplyButton(JSONObject honkJSON) {
    	Button likeButton = new Button("Reply");
    	likeButton.getStyleClass().clear();
    	likeButton.getStyleClass().add("likeButton");
    	likeButton.setOnAction(new EventHandler<>() {
    		@Override
    		public void handle(ActionEvent event) {    			
					try {
						PostController.replyTo=honkJSON.getInt("id");
						switchToPost();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	}});
    	
    	return likeButton;
    }
    private Button createViewRepliesButton(JSONObject object) {
    	Button viewProfileButton = new Button("View Replies");
    	viewProfileButton.getStyleClass().clear();
    	viewProfileButton.getStyleClass().add("profileButton");
        viewProfileButton.setOnAction(new EventHandler<>(){
			@Override
			public void handle(ActionEvent event) {
				HonkRepositoryImp x = new HonkRepositoryImp();
				ArrayList<Honk> temp =x.getReplyList(object,object.getInt("id"));
				if(temp.size()>1)
					honkScrollPaneContainer.setContent(createRepliesGridpane(temp));
			
			}});
    	return viewProfileButton;
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