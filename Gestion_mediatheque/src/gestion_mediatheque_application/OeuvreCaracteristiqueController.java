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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OeuvreCaracteristiqueController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Oeuvre> tableview;
    @FXML
    private TableColumn<Oeuvre, String> tabletitre;
    @FXML
    private TableColumn<Oeuvre, String> tableauteur;
    @FXML
    private TableColumn<Oeuvre, Integer> tableexemplaire;
    @FXML
    private Button retour;
    @FXML
    private Button accueil;
    @FXML
    private Label Ltest;

    private ObservableList<Oeuvre> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
  
    public void myFonction(String value){
        // Le label va prendre la valeur du comBo box passé en paramètre
        Ltest.setText(value);
         Connection connexion = null;
try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
data=FXCollections.observableArrayList();
//On excécute et sauvegarde la requete
ResultSet rs = connexion.createStatement().executeQuery("SELECT titre,auteur,exemplaire FROM oeuvre WHERE titre = '"+Ltest.getText()+"'");
while(rs.next()){
 // On obtient des strings et int da'après la valeur de la requete
    data.add(new Oeuvre (rs.getString(1),rs.getString(2),rs.getInt(3)));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(AdherentCoordonneesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    // Définir les valeurs des tables
    //NB.Property Value Factory doit être identique à celui défini dans la classe de modèle
    tabletitre.setCellValueFactory(new PropertyValueFactory<> ("titre"));
    tableauteur.setCellValueFactory(new PropertyValueFactory<> ("auteur"));
 
    
    tableexemplaire.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getExemplaire().intValue()).asObject());      
    tableview.setItems(null);
    tableview.setItems(data);
    
        
        
    }
    
    
    
    
    
    
    
    
    
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        // si l'utilisateur sélectionne le retour
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Consulter_Oeuvre.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter une oeuvre");
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
            // si l'utilisateur sélectionne le boutton d'auccueil
            
              int dialogButton = JOptionPane.YES_NO_OPTION;         // On définit les bouttons de dialogue
            int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);       //valeur du résultat
           if(dialogResult == JOptionPane.YES_OPTION){      // si l'utilisateur choisit l'option oui
               //On affiche un message
        JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil",
                        "Information",

                        JOptionPane.INFORMATION_MESSAGE);
        // On charge la page d'accueil
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Gestion_mediatheque.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Mediatheque");
    }
    }
}
