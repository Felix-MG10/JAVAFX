package com.example.etudiant.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnexion {
    // Pour la connexion a la base
    private Connection cnx;

    // Pour les requetes preparees
    private PreparedStatement pstm;

    // Pour les requetes de type SELECT
    private ResultSet rs;

    //Pour les requetes de type mise a jour
    private int ok;


    public Connection getConnection(){
        String host = "localhost";
        String password = "";
        String dbname = "liste_etudiant_java";
        String user = "root";
        String url = "jdbc:mysql://"+ host +":3306/"+dbname;
        try {

            // Chargement du pilote de MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Ouverture de la connexion a la base
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion reussie");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return cnx;
    }

    public void initPrepar(String sql){
        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);
            System.out.println();
        }catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public ResultSet executeSelect(){
        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public int executeMaj(){
        try {
            ok = pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    public void closeConnection(){
        try {
            if (cnx != null){
                cnx.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {
        return pstm;
    }
}
