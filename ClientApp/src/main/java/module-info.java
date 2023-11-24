module a.clientapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens a.clientapp to javafx.fxml;
    exports a.clientapp;
}