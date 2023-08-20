package com.example.inscription.table;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nom, prenom;
    private int numero;

    public User() {
    }

    public User(String nom, String prenom, int numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
