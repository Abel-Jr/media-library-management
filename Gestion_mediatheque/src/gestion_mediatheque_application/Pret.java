/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;


import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



/**
 *
 * @author user
 */
public class Pret {
    private IntegerProperty id_pret;
    private  StringProperty nom;
    private final StringProperty oeuvre;
    private final ObjectProperty date_pret;
    private ObjectProperty date_de_remise_attendue;
   
    

    public Pret(String nom, String oeuvre, Object date_pret) {
        this.nom = new SimpleStringProperty(nom);
        this.oeuvre =new SimpleStringProperty(oeuvre);
        this.date_pret = new SimpleObjectProperty<>(date_pret);
        
    }

    public Pret(String oeuvre, Object date_pret) {
        this.oeuvre = new SimpleStringProperty(oeuvre);
        this.date_pret = new SimpleObjectProperty<>(date_pret);
    }
    
    public Pret(int id_pret,String nom,String oeuvre,Object date_pret,Object date_de_remise_attendue){
        this.id_pret = new SimpleIntegerProperty(id_pret);
        this.nom = new SimpleStringProperty(nom);
        this.oeuvre =new SimpleStringProperty(oeuvre);
        this.date_pret = new SimpleObjectProperty<>(date_pret);
        this.date_de_remise_attendue = new SimpleObjectProperty<>(date_de_remise_attendue);
    } 
    public Pret (int id_pret, String oeuvre, Object date_pret){
        this.id_pret = new SimpleIntegerProperty(id_pret);
        this.oeuvre =new SimpleStringProperty(oeuvre);
        this.date_pret = new SimpleObjectProperty<>(date_pret);
    }
    
    public IntegerProperty getId(){
        return id_pret;
    }
    public void setId(int value){
        id_pret.set(value);
    }
    public StringProperty getNom() {
        return nom;
    }

    public void setNom(String value) {
        nom.set(value);
    }

    public StringProperty getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(String value) {
        oeuvre.set(value);
    }

    public ObjectProperty getDate_pret() {
        return date_pret;
    }

    public void setDate_pret(Date value) {
        date_pret.set(value);
    }

     public ObjectProperty getDateAttendue_pret() {
        return date_de_remise_attendue;
    }

    public void setDatAttendue_pret(Date value) {
        date_de_remise_attendue.set(value);
    }
       public IntegerProperty id_pret(){ return id_pret;}
       public StringProperty nomProperty (){ return nom;}
       public StringProperty oeuvreProperty (){ return oeuvre;}
       public ObjectProperty datepretProperty (){ return date_pret;}
       public ObjectProperty dateremiseProperty(){ return date_de_remise_attendue;}
       
    
}
