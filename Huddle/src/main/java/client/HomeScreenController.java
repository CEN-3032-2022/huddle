package client;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
public class HomeScreenController {
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
    @FXML
    private void switchToWall() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
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