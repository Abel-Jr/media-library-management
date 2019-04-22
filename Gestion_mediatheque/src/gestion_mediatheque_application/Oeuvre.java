/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class Oeuvre {
    
    private StringProperty nom_adherent;
    private  StringProperty titre;
    private  StringProperty auteur;
    private  IntegerProperty exemplaire;

    public Oeuvre(String titre, String auteur, Integer exemplaire) {
        this.titre = new SimpleStringProperty(titre);
        this.auteur = new SimpleStringProperty(auteur);
        this.exemplaire = new SimpleIntegerProperty(exemplaire);
    }
    
    
    public Oeuvre(String nom_adherent,String titre, String auteur, Integer exemplaire) {
        this.nom_adherent= new SimpleStringProperty(nom_adherent);
        this.titre = new SimpleStringProperty(titre);
        this.auteur = new SimpleStringProperty(auteur);
        this.exemplaire = new SimpleIntegerProperty(exemplaire);
    }

    public StringProperty getNom_adherent() {
        return nom_adherent;
    }

    public void setNom_adherent(String value) {
        nom_adherent.set(value);
    }

    public StringProperty getTitre() {
        return titre;
    }

    public void setTitre(String value) {
        titre.set(value);
    }

    public StringProperty getAuteur() {
        return auteur;
    }

    public void setAuteur(String value) {
        auteur.set(value);
    }

    public IntegerProperty getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Integer value) {
        exemplaire.set(value);
    }
       public StringProperty nom_adherentProperty (){ return nom_adherent;}
       public StringProperty titreProperty (){ return titre;}
       public StringProperty auteurProperty (){ return auteur;}
       public IntegerProperty exemplaireProperty (){ return exemplaire;}
    
}
    
    

