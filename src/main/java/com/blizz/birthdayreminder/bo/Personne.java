package com.blizz.birthdayreminder.bo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Personne {
    String prenom, nom;
    int id;
    LocalDate dateNaissance;

    public Personne(String prenom, String nom, LocalDate dateNaissance) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public Personne(int id, String prenom, String nom, LocalDate dateNaissance) {
        this(prenom, nom, dateNaissance);
        this.id = id;
    }
    public Personne() {
    }

    public boolean isAdult(){
        return dateNaissance.isBefore(LocalDate.now().minusYears(18));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("prenom='").append(prenom).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", id=").append(id);
        sb.append(", dateNaissance=").append(dateNaissance);
        sb.append('}');
        return sb.toString();
    }
}
