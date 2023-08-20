package com.example.ii.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnexion {
    // Pour la connexion
    private Connection cnx;
    // Pour les requetes preparees
    private PreparedStatement pstm;
    // Pour les requetes de types select
    private ResultSet rs;
    // Pour les requetes de mise a jour
    private int ok;

    public Connection getConnection(){
        // Parametre de connexion
        String host = "localhost";
        String database = "exam_java_db";
        String url = "jdbc:mysql://"+host+":3306/"+database;
        String user = "root";
        String password = "";
        try {
            // Chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            // Ouverture de la connexion a la base
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion reussie !!!");
        }catch (Exception ex){
            System.out.println("Erreur de connexion !");
            ex.printStackTrace();
        }
        return cnx;
    }

    public void initPrepar(String sql){
        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);
            System.out.println();
        }catch (Exception e){
            System.out.println("Erreur");
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


    public PreparedStatement getPstm(){
        return pstm;
    }
}
