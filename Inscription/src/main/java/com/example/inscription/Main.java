package com.example.inscription;

import com.example.inscription.Controllers.PageController;
import com.example.inscription.Dao.DBconnexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/pages/pages.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
       // DBconnexion db = new DBconnexion();
        PageController pg = new PageController();
        pg.loadTable();
    }
}
