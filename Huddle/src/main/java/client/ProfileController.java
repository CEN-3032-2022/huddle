package client;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
		if(isAlreadyFollowingUser()) {
			setAlreadyFollowingButton();
		}
		
		UserName.setText(holder);
		User profileData = getUserProfileData(holder);
		bioText.setText(profileData.getBio());
		profileFollowerCount = profileData.getFollowerCount();
		followersText.setText("Followers: " + profileFollowerCount);
		setField();
	}
	@FXML
	private void setField() {
		HonkRetriever honksRtr = new HonkRetriever();
		JSONArray Arr = honksRtr.getUsrHonks(holder);
		GridPane tPane = createHonksGridpane();
		for(int i=0;i<Arr.length();i++) {
			if(Arr.getJSONObject(i).getString("UserName").equals(UserName.getText())) {
				GridPane honk = createHonkGridpane();
				honk.add(new Text(Arr.getJSONObject(i).getString("UserName")), 0, 0);
				honk.add(new Text(Arr.getJSONObject(i).getString("date")), 2, 0);
				honk.add(createHonkContent(Arr.getJSONObject(i).getString("content")), 1, 1, 2, 1);
				honk.add(createLikeHbox(Arr.getJSONObject(i)), 0, 2, 3, 1);
				tPane.add(honk, 0, i);
			}
		}
		honkScrollPaneContainer.setContent(tPane);
	}
    @FXML
    private void followButtonOnClick() {		
		UserRepositoryImp userRepo = new UserRepositoryImp();

    	if(!isAlreadyFollowingUser()) {
        	userRepo.followUser(App.currentUser.getUsername(), holder);
    		followersText.setText("Followers: " + ++profileFollowerCount);
    		setAlreadyFollowingButton();
    	}
    	else {
        	userRepo.unfollowUser(App.currentUser.getUsername(), holder);
    		followersText.setText("Followers: " + --profileFollowerCount);
    		setNotFollowingButton();
    	}
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
    
    @FXML
    private void sendMessageButtonOnClick() {		
		MessageRepositoryImp messageRepo = new MessageRepositoryImp();
		
		ArrayList<Message> messages = messageRepo.getRecievedMessages(App.currentUser.getUsername());
	
		honkScrollPaneContainer.setContent(createMessagesGridpane(messages));
    }

    private User getUserProfileData(String username) {
		UserRepositoryImp userRepo = new UserRepositoryImp();
    	User profileUser = userRepo.getUserByUsername(username);
    	
		return profileUser;
    }
    
    private void setAlreadyFollowingButton() {
    	followButton.setStyle("-fx-background-color: darkgreen;");
    	followButton.setText("Unfollow");
    }
    
    private void setNotFollowingButton() {
    	followButton.setStyle("-fx-background-color: green;");
    	followButton.setText("Follow");
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
					setField();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    	
    	return likeButton;
    }
    private HBox createLikeHbox(JSONObject honkJSON) {
    	HBox replyHbox = new HBox();
    	replyHbox.setSpacing(5);
    	replyHbox.setAlignment(Pos.CENTER_LEFT);
    	
    	replyHbox.getChildren().add(createLikeButton(honkJSON));
    	replyHbox.getChildren().add(new Text("Likes: " + honkJSON.getInt("numLikes")));
    	
    	return replyHbox;
    }
    private GridPane createHonksGridpane() {
		GridPane honksPane = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
		honksPane.getStyleClass().add("honks-pane");
	    column1.setPercentWidth(100);
	    honksPane.getColumnConstraints().add(column1);
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
    
    private Label createHonkContent(String content) {
        Label label = new Label(content);
        label.setWrapText(true);
        return label;
    }
    
    private GridPane createMessagesGridpane(ArrayList<Message> messages) {
        GridPane messagesPane = createMessageGridpane();
        for(int i=0; i < messages.size(); i++) {
            if(App.currentUser.getUsername().equals(messages.get(i).getSender())) {
                GridPane message = createMessageGridpane();
                message.add(new Text(messages.get(i).getSender()), 1, 0);
                message.add(new Text(messages.get(i).getRecipient().toString()), 2, 0);
                message.add(createMessageContent(messages.get(i).getContent()), 1, 1, 2, 1);
                messagesPane.add(message, 2, i, 2, 1);
            }
            else {
                GridPane message = createMessageGridpane();
                message.add(new Text(messages.get(i).getRecipient()), 1, 0);
                message.add(new Text(messages.get(i).getSender().toString()), 2, 0);
                message.add(createMessageContent(messages.get(i).getContent()), 1, 1, 2, 1);
                messagesPane.add(message, 0, i);
            }
        }
        Button button = new Button("Reply");
        button.getStyleClass().clear();
        button.getStyleClass().add("replyButton");
        button.getStyleClass().add("button");
        messagesPane.add(button, 1, messages.size()+1,1,1);
        
//        TextField messageTextField = new TextField();
//        messagesPane.add(messageTextField, 0, messages.size()+1);

//        button.setOnAction(new EventHandler<>() {
//    		@Override
//    		public void handle(ActionEvent event) {    			
//    			MessageRepositoryImp messageRep = new MessageRepositoryImp();
//				
//				String sender = App.currentUser.getUsername();
//				String recipient = UserName.getText();
//				String content = messageTextField.getText();
//				
//				messageRep.sendMessage(sender, recipient, content);
//				sendMessageButtonOnClick();
//	    	}
//    	});
        
        return messagesPane;
    }
    
    private Label createMessageContent(String content) {
        Label label = new Label(content);
        label.setWrapText(true);
        return label;
    }
    
    private GridPane createMessageGridpane() {
        GridPane honksPane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        honksPane.getStyleClass().add("messages-pane");
        col1.setPercentWidth(100);
        honksPane.getColumnConstraints().add(col1);
        honksPane.setVgap(10);

        return honksPane;
    }
 }