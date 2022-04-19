package client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class HomeScreenController{
	HonkRetriever honkRtr;
	boolean isMasterWall;
	boolean viewReply;
	boolean searchBool;
	JSONObject replyHolder;
	@FXML ScrollPane honkScrollPaneContainer;
	@FXML TextField searchBar;
	@FXML Text UserName;
	@FXML Text bioText;
	@FXML public void initialize(){
		honkRtr = new HonkRetriever();
		searchBar.setPromptText("[\"username\" to search for usernames] [\"#hashtag\" to seach for hashtags] [\"@username\" to search for tags]");
		UserName.setText(App.currentUser.getUsername());
		bioText.setText(App.currentUser.getBio());
		switchToFollowed();
	}
    @FXML
    private void switchToLogin() throws IOException {
    	isMasterWall=false;
    	viewReply=false;
    	searchBool=false;
        App.setRoot("/fxml/Login");
    }
    @FXML
    private void switchToWall() throws IOException {
    	isMasterWall=true;
    	viewReply=false;
    	searchBool=false;
    	HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
		ArrayList<Honk> honks = honksRtr.getHonkList();
		honkScrollPaneContainer.setContent(createHonksGridpane(honks));
		isMasterWall = true;
    }
    @FXML
    private void switchToFollowed() {
    	isMasterWall=false;
    	viewReply=false;
    	searchBool=false;
    	HonkRepositoryImp  honksRtr= new HonkRepositoryImp();
		ArrayList<Honk> honks = honksRtr.getFollowedHonks(App.currentUser.getUsername());
		honkScrollPaneContainer.setContent(createHonksGridpane(honks));
		isMasterWall = false;
    }
    @FXML
    private void search() {
    	isMasterWall=false;
    	viewReply=false;
    	searchBool=true;
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
    private GridPane createHonksGridpane(ArrayList<Honk> honks) {
		GridPane honksPane = createHonksGridpane();

		for(int i=0;i<honks.size();i++) {
			GridPane honk = createHonkGridpane();
			final String name = honks.get(i).getUserName();
		
			honk.add(createViewProfileButton(name), 0,0);
			honk.add(new Text(name), 1, 0);
			honk.add(new Text(honks.get(i).getPublishDate().toString()), 2, 0);
			honk.add(createHonkContent(honks.get(i).getContent()), 1, 1, 2, 1);
			honk.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 0, 2);
			honk.add(new Text("Likes: " + honks.get(i).getNumLikes()), 1, 2);
			honk.add(createReplyHbox(new JSONObject(honks.get(i).toJsonString())), 2, 2);
			honksPane.add(honk, 0, i);
		}
    	return honksPane;
    }
    private GridPane createUsrGridpane(User usr,ArrayList<Honk> honks) {
		GridPane honksPane = createHonksGridpane();
		honksPane.add(createViewProfileButton(usr.getUsername()), 0,0);
		honksPane.add(new Text("User: " + usr.getUsername()+"\nFollowers: "+usr.getFollowerCount()+"\n"), 0, 1);
		
		for(int i=0;i<honks.size();i++) {
			GridPane honk = createHonkGridpane();
			final String name = honks.get(i).getUserName();
			
			honk.add(createViewProfileButton(name), 0,0);
			honk.add(new Text(name), 1, 0);
			honk.add(new Text(honks.get(i).getPublishDate().toString()), 2, 0);
			honk.add(createHonkContent(honks.get(i).getContent()), 1, 1, 2, 1);
			honk.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 0, 2);
			honk.add(new Text("Likes: " + honks.get(i).getNumLikes()), 1, 2);
			honksPane.add(honk, 0, i + 2);
		}
    	return honksPane;
    }
    private GridPane createRepliesGridpane(ArrayList<Honk> honks) {
    	isMasterWall=false;
    	viewReply=true;
    	searchBool=false;
		GridPane honksPane = createReplyHonksGridpane();
		GridPane mainHonk = createHonkGridpane();
		mainHonk.add(createViewProfileButton(honks.get(0).getUserName()), 0, 0);
		mainHonk.add(new Text(honks.get(0).getUserName()), 1, 0);
		mainHonk.add(new Text(honks.get(0).getPublishDate().toString()), 2,0);
		mainHonk.add(createHonkContent(honks.get(0).getContent()), 1, 1, 2, 1);
		mainHonk.add(new Text("Likes: " + honks.get(0).getNumLikes()), 1,2);
		mainHonk.add(createLikeButton(new JSONObject(honks.get(0).toJsonString())), 0, 2);
		mainHonk.add(createReplyHbox(new JSONObject(honks.get(0).toJsonString())), 2, 2);
		honksPane.add(mainHonk, 0, 0, 2, 1);
		for(int i=1;i<honks.size();i++) {
			GridPane honk = createHonkGridpane();
			final String name = honks.get(i).getUserName();
			honk.add(createViewProfileButton(name), 0, 0);
			honk.add(new Text(honks.get(i).getUserName()), 1, 0);
			honk.add(new Text(honks.get(i).getPublishDate().toString()), 2, 0);
			honk.add(createHonkContent(honks.get(i).getContent()), 1, 1, 2, 1);
			honk.add(createLikeButton(new JSONObject(honks.get(i).toJsonString())), 0, 2);
			honk.add(new Text("Likes: " + honks.get(i).getNumLikes()), 1, 2);
			honk.add(createReplyHbox(new JSONObject(honks.get(0).toJsonString())), 2, 2);
			honk.add(createReplyHbox(new JSONObject(honks.get(i).toJsonString())), 2, 2);
			mainHonk.add(createReplyHbox(new JSONObject(honks.get(i).toJsonString())), 2, 2);
			honksPane.add(honk, 1, i+1);
		}
		
    	return honksPane;
    }
    private Label createHonkContent(String content) {
        Label label = new Label(content);
        label.setWrapText(true);
        return label;
    }
    private Button createLikeButton(JSONObject honkJSON) {
    	Button likeButton = new Button("Like");
    	likeButton.getStyleClass().clear();
    	likeButton.getStyleClass().add("likeButton");
    	likeButton.getStyleClass().add("button");
    	likeButton.setOnAction(new EventHandler<>() {
    		@Override
    		public void handle(ActionEvent event) {    			
    			try {
					LikeHonkController.likeHonk(honkJSON);
					if(isMasterWall) switchToWall();
					else if(viewReply) {
						HonkRepositoryImp x = new HonkRepositoryImp();
						ArrayList<Honk> temp =x.getReplyList(replyHolder.getInt("id"));
						honkScrollPaneContainer.setContent(createRepliesGridpane(temp));
						}
					else if(searchBool) {
						search();
					}
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
    	likeButton.getStyleClass().add("replyButton");
    	likeButton.getStyleClass().add("button");
    	likeButton.setOnAction(new EventHandler<>() {
    		@Override
    		public void handle(ActionEvent event) {    			
					try {
						replyHolder = honkJSON;
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
    	viewProfileButton.getStyleClass().add("viewRepliesButton");
    	viewProfileButton.getStyleClass().add("button");
        viewProfileButton.setOnAction(new EventHandler<>(){
			@Override
			public void handle(ActionEvent event) {
				isMasterWall=false;
		    	viewReply=true;
		    	searchBool=false;
				HonkRepositoryImp x = new HonkRepositoryImp();
				ArrayList<Honk> temp =x.getReplyList(object.getInt("id"));
				if(temp.size()>1) {
					replyHolder = object;
					honkScrollPaneContainer.setContent(createRepliesGridpane(temp));
				}
			}});
    	return viewProfileButton;
    }
    private Button createViewProfileButton(String name) {
    	Button viewProfileButton = new Button("Profile");
    	viewProfileButton.getStyleClass().clear();
    	viewProfileButton.getStyleClass().add("profileButton");
    	viewProfileButton.getStyleClass().add("button");
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
    private HBox createReplyHbox(JSONObject honkJSON) {
    	HBox replyHbox = new HBox();
    	replyHbox.setSpacing(5);
    	replyHbox.setAlignment(Pos.CENTER_RIGHT);
    	
    	replyHbox.getChildren().add(createReplyButton(honkJSON));
    	replyHbox.getChildren().add(createViewRepliesButton(honkJSON));
    	
    	return replyHbox;
    }
    private GridPane createHonksGridpane() {
		GridPane honksPane = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		honksPane.getStyleClass().add("honks-pane");
	    col1.setPercentWidth(100);
	    honksPane.getColumnConstraints().add(col1);
		honksPane.setVgap(10);
		
		return honksPane;
    }
    private GridPane createReplyHonksGridpane() {
		GridPane honksPane = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		honksPane.getStyleClass().add("honks-pane");
	    col1.setPercentWidth(10);
	    col2.setPercentWidth(90);
	    honksPane.getColumnConstraints().addAll(col1, col2);
	    
		honksPane.setVgap(10);
		
		return honksPane;
    }
    private GridPane createHonkGridpane() {
		GridPane honk = new GridPane();
		honk.setPadding(new Insets(3));
		honk.getStyleClass().add("honk");
		honk.setHgap(5);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		col2.setHalignment(HPos.RIGHT);
		honk.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints(), col2);
		return honk;
    }
 }