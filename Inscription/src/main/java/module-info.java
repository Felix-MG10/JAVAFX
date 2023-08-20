module com.example.inscription {
    requires javafx.controls;
    requires javafx.fxml;
    requires TrayNotification;
    requires java.sql;


    opens com.example.inscription to javafx.fxml;
    exports com.example.inscription;
    exports com.example.inscription.Controllers;
    opens com.example.inscription.Controllers to javafx.fxml;
    exports com.example.inscription.table;
    opens com.example.inscription.table to javafx.fxml;
}