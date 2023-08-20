module com.example.etudiant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayNotification;


    opens com.example.etudiant to javafx.fxml;
    exports com.example.etudiant;
    exports com.example.etudiant.dao;
    opens com.example.etudiant.dao to javafx.fxml;
    exports com.example.etudiant.entities;
    opens com.example.etudiant.entities to javafx.fxml;
    exports com.example.etudiant.controllers;
    opens com.example.etudiant.controllers to javafx.fxml;
}