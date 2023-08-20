module com.example.ii {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayNotification;


    opens com.example.ii to javafx.fxml;
    exports com.example.ii;

    exports com.example.ii.controllers;
    opens com.example.ii.controllers to javafx.fxml;

    exports com.example.ii.dao;
    opens com.example.ii.dao to javafx.fxml;

    exports com.example.ii.entities;
    opens com.example.ii.entities to javafx.fxml;

    exports com.example.ii.tools;
    opens com.example.ii.tools to javafx.fxml;
}