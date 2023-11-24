package a.clientapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static a.clientapp.Config.socket;

public class DataController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Label labelFilesOnDisk;

    @FXML
    void initialize() throws IOException {

        labelFilesOnDisk.setText(".\n" +
                "BigText.txt\n" +
                "Makefile\n" +
                "SieciServer\n" +
                "Hello.txt\n" +
                "CMakeFiles\n" +
                "cmake_install.cmake\n" +
                "..\n" +
                "SieciServer.cbp\n" +
                "CMakeCache.txt\n" +
                "Testing\n" +
                ".cmake\n" +
                "images.jpeg");

        buttonBack.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Remote Disk");
            buttonBack.getScene().getWindow().hide();
        });
    }

}
