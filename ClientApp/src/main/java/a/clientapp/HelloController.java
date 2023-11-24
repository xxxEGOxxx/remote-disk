package a.clientapp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    Config config = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label indicatorAuth;

    @FXML
    private Button buttonContinue;

    @FXML
    private TextField hostNameInput;

    @FXML
    private TextField portInput;

    @FXML
    void initialize() throws IOException {

        buttonContinue.setOnAction(event -> {
                try {
                    Config.setSocket(hostNameInput.getText(), Integer.parseInt(portInput.getText()));

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
                    buttonContinue.getScene().getWindow().hide();
                } catch (IOException e) {
                    indicatorAuth.setText("Wrong Host...");
                    e.printStackTrace();
                }


            Config.setPort(Integer.parseInt(portInput.getText()));
            //Config.setPort(32228);
            Config.setHostName(hostNameInput.getText());
            //Config.setHostName("ubuntu");
            /*try {
                Config.socket = new Socket(Config.getHostName(), Config.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }*/



        });
    }

}
