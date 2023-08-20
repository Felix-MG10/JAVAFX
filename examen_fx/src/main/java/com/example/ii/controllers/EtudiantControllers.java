package com.example.ii.controllers;

import com.example.ii.dao.DBconnexion;
import com.example.ii.entities.Etudiant;
import com.example.ii.tools.Notification;
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

public class EtudiantControllers implements Initializable {
    private String mat;
    @FXML
    void getMat(ActionEvent event) {

        if (prenomTFD.getText().isEmpty() || nomTFD.getText().isEmpty()){
            Notification.NotifWarning("Warning", "Tous les champs sont obligatoires!!!");
        }else{
            mat = prenomTFD.getText().substring(0,1)+"-"+nomTFD.getText()+"L2RTII-ISI";
            //System.out.println(mat);
            matriculeTFD.setText(mat);
        }
    }
    @FXML
    private Button generer;

    DBconnexion db = new DBconnexion();
    @FXML
    private TableView<Etudiant> EtudiantTB;

    @FXML
    private TableColumn<Etudiant, Integer> idCol;

    @FXML
    private TableColumn<Etudiant, String> matriculeCol;

    @FXML
    private TableColumn<Etudiant, String> nomCol;

    @FXML
    private TextField nomTFD;
    @FXML
    private TextField matriculeTFD;
    @FXML
    private TableColumn<Etudiant, String> prenomCol;

    @FXML
    private TextField prenomTFD;

    @FXML
    private Button saveBtn;

    @FXML
    void save(ActionEvent event) {

        String sql = "INSERT INTO etudiant VALUES (null, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            if(prenomTFD.getText().isEmpty() || nomTFD.getText().isEmpty() || mat.isEmpty()){
                Notification.NotifWarning("Warning", "Tous les champs sont obligatoires!!!");
            }else {
                mat = prenomTFD.getText().substring(0,1)+"-"+nomTFD.getText()+"L2RTII-ISI";
                matriculeTFD.setText(mat);
                db.getPstm().setString(1, mat);
                db.getPstm().setString(2,nomTFD.getText());
                db.getPstm().setString(3,prenomTFD.getText());
                db.executeMaj();
                db.closeConnection();
                clearField();
                loadTable();
                Notification.NotifSucces("Succes", "Insertion reussie!!!");
            }
        }catch (SQLException e){
            Notification.NotifWarning("Error", "Erreur lors de l insertion");
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    ObservableList<Etudiant> getEtudiant(){
        ObservableList<Etudiant> Etudiants = FXCollections.observableArrayList();
        String sql = "SELECT * FROM etudiant ORDER BY nomE";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                Etudiant et = new Etudiant();
                et.setId(rs.getInt("idE"));
                et.setMatricule(rs.getString("matriculeE"));
                et.setNom(rs.getString("nomE"));
                et.setPrenom(rs.getString("prenomE"));
                Etudiants.add(et);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return Etudiants;
    }


    public void loadTable(){
        ObservableList<Etudiant> liste_etudiant = getEtudiant();
        EtudiantTB.setItems(liste_etudiant);

        idCol.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("id"));
        matriculeCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("matricule"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String >("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String >("prenom"));

    }

    public void clearField(){
        nomTFD.setText("");
        prenomTFD.setText("");
        mat = "";
    }

}
