package a.clientapp;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.zip.CheckedInputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static a.clientapp.Config.socket;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonForDownload;

    @FXML
    private Button buttonShowFilesOnDisk;

    @FXML
    private TextField inputFileForDownload;

    @FXML
    private Label indicatorOfDownload;

    @FXML
    private Label indicatorOfFailed;

    @FXML
    void initialize() throws IOException {

        buttonForDownload.setOnAction(event -> {

            String fileName = inputFileForDownload.getText();
            if (Objects.equals(fileName, "")){
                fileName = "0";
            };
            //String fileName = "Hello.txt";

            DataInputStream dis = null;
            DataOutputStream dos = null;
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*short textSize = 0;
            try {
                textSize = dis.readShort();
                String text = new String(dis.readNBytes(textSize), StandardCharsets.US_ASCII);
                System.out.println(textSize);
                System.out.println(text);
            } catch (IOException e) {
                e.printStackTrace();
            }*/


            short length = (short) fileName.length();
            byte[] data = ByteBuffer.allocate(2 + length).putShort(length).put(fileName.getBytes(StandardCharsets.UTF_8)).array();
            System.out.println("Check 1 2");
            try {
                dos.write(data);
                System.out.println("Check 3");
            } catch (IOException e) {
                e.printStackTrace();
            }
            long check = 0;

            try {
                check = dis.readLong();
                System.out.println("Check 4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                check = dis.readLong();
                System.out.println("Check 5");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (check == 1)
            {
                ByteArrayOutputStream outputByte = new ByteArrayOutputStream();

                byte[] fileData = null;

                byte[] file1024 = new byte[1024];
                try {
                    long size = dis.readLong();
                    System.out.println("Check 6");
                    fileData = new byte[(int) size];
                    System.out.println(size);

                    while(size > 0) {
                        dis.read(file1024);

                        System.out.println("Bytes left : " + size);

                        outputByte.write(file1024);
                        fileData = outputByte.toByteArray();

                        size = size - 1024;
                    }
                    System.out.println("Check 7");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                File outputFile = new File("D:\\DownloadsFromRemoteDisk\\" + fileName);
                try (FileOutputStream outputStream = new FileOutputStream(outputFile);) {

                    outputStream.write(fileData);  // Write the bytes and you're done.
                    indicatorOfFailed.setText("");
                    indicatorOfDownload.setText("Success!");

                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    dis.close();
                    dos.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Config.socket = new Socket(Config.getHostName(), Config.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                indicatorOfDownload.setText("");
                indicatorOfFailed.setText("Failed!");
                try {
                    dis.close();
                    dos.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Config.socket = new Socket(Config.getHostName(), Config.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonShowFilesOnDisk.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("data-view.fxml"));
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
            buttonShowFilesOnDisk.getScene().getWindow().hide();
        });
    }

}

