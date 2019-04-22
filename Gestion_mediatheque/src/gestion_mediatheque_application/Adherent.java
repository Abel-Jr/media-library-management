/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class Adherent {
    
    
    
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty adresse;

    public Adherent(String nom, String prenom, String adresse) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.adresse = new SimpleStringProperty(adresse);
    }

    public StringProperty getNom() {
        return nom;
    }

    public void setNom(String value) {
        nom.set(value);
    }

    public StringProperty getPrenom() {
        return prenom;
    }

    public void setPrenom(String value) {
        prenom.set(value);
    }

    public StringProperty getAdresse() {
        return adresse;
    }

    public void setAdresse(String value) {
        adresse.set(value);
    }
        public StringProperty nomProperty (){ return nom;}
       public StringProperty prenomProperty (){ return prenom;}
       public StringProperty adresseProperty (){ return adresse;}
    
    
    
}
