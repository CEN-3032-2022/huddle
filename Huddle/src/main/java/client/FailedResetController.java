package client;

import java.io.IOException;
import javafx.fxml.FXML;

public class FailedResetController {
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/Group7/Huddle/UserInterface/Login");
    }
 }