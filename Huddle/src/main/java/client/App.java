package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.User;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
    	App.setUserAgentStylesheet("file:src/main/resources/Group7/Huddle/UserInterface/theme1.css");
        scene = new Scene(loadFXML("/Group7/Huddle/UserInterface/Login"), 640, 480);
        stage.setTitle("Huddle");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();
    }

    static void setRoot(String string) throws IOException {
        scene.setRoot(loadFXML(string));
    }

    private static Parent loadFXML(String string) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(string + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}