/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdherentPretCoordonneesController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Adherent> tableview_coordonnees;
    @FXML
    private TableColumn<Adherent, String> tablenom;
    @FXML
    private TableColumn<Adherent, String> tableprenom;
    @FXML
    private TableColumn<Adherent, String> tableadresse;
    @FXML
    private TableView<Pret> tableview_pret;
    @FXML
    private TableColumn<Pret, String> tableoeuvre;
    @FXML
    private TableColumn<Pret, Object> tabledate;
    @FXML
    private Button retour;
    @FXML
    private Button accueil;

    private ObservableList<Adherent> adherent;
    
    private ObservableList<Pret> pret;
    @FXML
    private Label Ltest;
    @FXML
    private TableColumn<Pret, Integer> tableid_pret;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }    
    
    public void myFonction(String value){
        //Le label va prendre la valeur du ComBo box passé en paramètre dans la page de Consultation
        Ltest.setText(value);
        
           Connection connexion = null;
try{
    // On se connecte à a base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
adherent=FXCollections.observableArrayList();
pret = FXCollections.observableArrayList();
//Excécute les requetes et on sauvegarde les requetes
ResultSet adhe = connexion.createStatement().executeQuery("SELECT DISTINCT adherent.nom,adherent.prenom,adherent.adresse FROM adherent INNER JOIN pret ON adherent.nom=pret.nom_adherent WHERE nom_adherent = '"+Ltest.getText()+"'"+" AND date_de_remise_reelle IS NULL");
ResultSet pre = connexion.createStatement().executeQuery("SELECT DISTINCT id_pret,oeuvre,date_de_pret FROM pret WHERE nom_adherent = '"+Ltest.getText()+"'"+" AND date_de_remise_reelle IS NULL");

while(adhe.next()){
 // On obtient des chaines de caractère de la requete
    adherent.add(new Adherent (adhe.getString(1),adhe.getString(2),adhe.getString(3)));
}
while (pre.next()){
    // On obtient des Objets et on crée un nouveau pret
    pret.add(new Pret (pre.getInt(1),pre.getString(2),pre.getObject(3)));
}

} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(AdherentPretController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
    // Définir les valeurs des tables
    //NB.Property Value Factory doit être identique à celui défini dans la classe de modèle
    tablenom.setCellValueFactory(new PropertyValueFactory<> ("nom"));
    tableprenom.setCellValueFactory(new PropertyValueFactory<> ("prenom"));
    tableadresse.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
    
            
    tableview_coordonnees.setItems(null);
    tableview_coordonnees.setItems(adherent);
    
    
    
    tableid_pret.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getId().intValue()).asObject());
    tableoeuvre.setCellValueFactory(new PropertyValueFactory<> ("oeuvre"));
    tabledate.setCellValueFactory(data -> data.getValue().datepretProperty());
    
    
    tableview_pret.setItems(null);
    tableview_pret.setItems(pret);
    
    
     }
        
        
        
        
    
    
    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        //si l'utilisateur sélectionne le bouton retour
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Consulter_Adherent.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter un adherent");
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
        // si l'utilisateur sélectionne le bouton accueil
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Gestion_mediatheque.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Accueil");
    }
    
}
