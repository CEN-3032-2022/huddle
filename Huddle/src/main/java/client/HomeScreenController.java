package client;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
public class HomeScreenController {
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
 }