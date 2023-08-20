package com.example.etudiant.controllers;

import com.example.etudiant.dao.DBconnexion;
import com.example.etudiant.entities.Etudiant;
import com.example.etudiant.tools.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EtudiantController implements Initializable {

    @FXML
    private TableColumn<Etudiant, String> emailCol;

    @FXML
    private TextField emailTFD;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableColumn<Etudiant, Integer> idCol;

    @FXML
    private TableColumn<Etudiant, String> nomCol;

    @FXML
    private TextField nomTFD;

    @FXML
    private TableColumn<Etudiant, String> numeroCol;

    @FXML
    private TextField numeroTFD;

    @FXML
    private TableColumn<Etudiant, String> prenomCol;

    @FXML
    private TextField prenomTFD;

    @FXML
    private TableView<Etudiant> tableauTB;
    @FXML
    private TableColumn<Etudiant, String> classeCol;

    @FXML
    private ComboBox<String> classeTFD;

    private String classe;


    ObservableList<String> listeClass = FXCollections.observableArrayList(
      "CS","II","RT","RI"
    );
    @FXML
    void choice(ActionEvent event) {
        String choix = classeTFD.getValue(); //classeTFD contient le choix fais par l utilisateur
        System.out.println(choix);

    }
    @FXML
    void save(ActionEvent event) {
        String sql = "INSERT INTO etudiant VALUES (null, ?, ?, ? , ?, ?)";
        try {
            //Preparation de la requete
            db.initPrepar(sql);

            if (nomTFD.getText().isEmpty() || prenomTFD.getText().isEmpty() || emailTFD.getText().isEmpty() || numeroTFD.getText().isEmpty() || classeTFD.getValue() == null){
                Notification.NotifError("Error", "Tous les champs sont obligatoires ");
            } else if (!isValidEmail(emailTFD.getText())) {
                Notification.NotifError("Error", "Adresse e-mail invalide");
            } else if (!isValidPhoneNumber(numeroTFD.getText())) {
                Notification.NotifError("Error", "Numéro de téléphone invalide");
            } else {
                //Passage des valeurs
                db.getPstm().setString(1, nomTFD.getText());
                db.getPstm().setString(2, prenomTFD.getText());
                db.getPstm().setString(3, emailTFD.getText());
                db.getPstm().setString(4, numeroTFD.getText());
                db.getPstm().setString(5, classeTFD.getValue());
                // ou
                // String choix = classeTFD.getValue();
                // db.getPstm().setString(5, choix);
                int ok = db.executeMaj();
                loadTable();
                clearField();
                db.closeConnection();
                Notification.NotifSucces("Success", "Insertion reussie dans la base😎");
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        //comboBox.setItems(FXCollections.observableArrayList("Felix", "Panda", "King"));
    }

    DBconnexion db = new DBconnexion();

    public ObservableList<Etudiant> getEtudiant(){
        ObservableList<Etudiant> Liste_Etudiant = FXCollections.observableArrayList();
        String sql = "SELECT * FROM etudiant ORDER BY nom";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setEmail(rs.getString("email"));
                etudiant.setNumero(rs.getString("numero"));
                etudiant.setClasse(rs.getString("classe"));
                Liste_Etudiant.add(etudiant);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Liste_Etudiant;
    }

    public void loadTable(){
        ObservableList<Etudiant> liste = getEtudiant();
        tableauTB.setItems(liste);
        idCol.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("email"));
        numeroCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("numero"));
        classeCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("classe"));
        classeTFD.setItems(listeClass); //Permet de recuperer au niveau de la liste des classe la valeurs choisit
    }
    public void clearField(){
        nomTFD.setText("");
        prenomTFD.setText("");
        emailTFD.setText("");
        numeroTFD.setText("");
        classeTFD.setValue("");
    }



    // Pour la gestion de l email et du numero
    private boolean isValidEmail(String email) {
        String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "\\d{9}"; // On verifier que le numero contient 9 chiffres
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }
}
