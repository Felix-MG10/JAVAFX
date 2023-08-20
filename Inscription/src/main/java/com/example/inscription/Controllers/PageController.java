package com.example.inscription.Controllers;

import com.example.inscription.Dao.DBconnexion;
import com.example.inscription.table.User;
import com.example.inscription.tools.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PageController implements Initializable {

    @FXML
    private TableColumn<User, String> C1;

    @FXML
    private TableColumn<User, String> C2;

    @FXML
    private TableColumn<User, Integer> C3;

    @FXML
    private TableView<User> UserTB;

    @FXML
    private TextField numeroTFD;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField nomTFD;

    @FXML
    private TextField prenomTFD;

    @FXML
    void save(ActionEvent event) {
        String sql = "INSERT INTO inscription VALUES (null, ?, ?, ?)";

        try {
            // chargement de la requete
            db.initPrepar(sql);

            //passages des valeurs
            db.getPstm().setString(1, nomTFD.getText());
            db.getPstm().setString(2, prenomTFD.getText());
            db.getPstm().setInt(3, Integer.parseInt(numeroTFD.getText()));

            int ok = db.executeMaj();
            loadTable();
            nomTFD.setText("");
            prenomTFD.setText("");
            numeroTFD.setText("");
            db.closeConnection();

        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    private DBconnexion db = new DBconnexion();

    public ObservableList<User> getUser(){
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SELECT * FROM inscription ORDER BY nom";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                User user = new User();
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setNumero(rs.getInt("numero"));
                users.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return users;
    }

    public void loadTable(){
        ObservableList<User> liste = getUser();
        UserTB.setItems(liste);
        C1.setCellValueFactory(new PropertyValueFactory<User, String >("nom"));
        C2.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        C3.setCellValueFactory(new PropertyValueFactory<User, Integer>("numero"));
    }
}