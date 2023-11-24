package a.clientapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        System.out.println();
        stage.setScene(new Scene(root, 700, 550));
        stage.show();
        stage.setTitle("Ethernal Storage");
    }

    public static void main(String[] args) {
        launch(args);
    }
}