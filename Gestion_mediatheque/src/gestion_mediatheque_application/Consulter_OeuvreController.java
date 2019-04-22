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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Consulter_OeuvreController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Lliste;
    @FXML
    private ComboBox<String> Comboname;
    @FXML
    private CheckBox Check_oeuvres;
    @FXML
    private CheckBox Check_pret;
    @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private Label Lerror;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Connection connexion = null;
try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
//On excécute et sauvegarde la requete
ResultSet rs = connexion.createStatement().executeQuery("SELECT titre FROM oeuvre");
while(rs.next()){
 // Obtenir des Strings de la requete 
    System.out.println(rs.getString(1));

    Comboname.getItems().add(rs.getString(1));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulter_OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
            // si l'utilisateur sélectionne le bouton de retour
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

    @FXML
    private void ValiderAction(ActionEvent event) throws IOException {
      try{
          if (Comboname.getValue().equals("")){     //si aucune valeur (aucune oeuvre ) n'est sélectionnée
              
          }
          if (Check_oeuvres.isSelected()){      //si la case de cractéristique est sélectionnée
              if (Check_pret.isSelected()){         // si la case de prets en cours également est sélectionnée (les deux cases sont sélectionnées)
                   FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("OeuvrePretCaracteristique.fxml"));
            Parent root = loader.load();
            OeuvrePretCaracteristiqueController pret_caracteristique = loader.getController();
            pret_caracteristique.myFonction(Comboname.getValue());
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter une oeuvre");
            } else{                                     // si seule la case de caractéristique est sélectionnée
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("OeuvreCaracteristique.fxml"));
            Parent root = loader.load();
            OeuvreCaracteristiqueController caracteristique = loader.getController();
            caracteristique.myFonction(Comboname.getValue());
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter une oeuvre");}
          }
        if (Check_pret.isSelected()){       //si la case de pret est sélectionnée
            
            if (Check_oeuvres.isSelected()){    // si la case des oeuvres est sélectionnée (les deux cases sont sélectionnées)
                
                FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("OeuvrePretCaracteristique.fxml"));
            Parent root = loader.load();
            OeuvrePretCaracteristiqueController pret_caracteristique = loader.getController();
            pret_caracteristique.myFonction(Comboname.getValue());
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter une oeuvre");
                
            } else{         // si seule la case de pret est sélectionnée
            
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("OeuvrePret.fxml"));
            Parent root = loader.load();
            OeuvrePretController pret = loader.getController();
            pret.myFonction(Comboname.getValue());
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter une oeuvre");}
            
        } 
    } catch(Exception e){
        Lerror.setVisible(true);            // Exception attrapée également si aucune valeur n'est sélectionnée 
    }
    } 
}
