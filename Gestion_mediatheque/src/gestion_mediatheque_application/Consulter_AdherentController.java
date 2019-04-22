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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class Consulter_AdherentController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Ladherents;
    @FXML
    private ComboBox<String> Comboname;
    @FXML
    private CheckBox Check_coordonnees;
    @FXML
    private CheckBox Check_pret;
    @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private Label Lerror;

    private ObservableList<Adherent> data;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
               Connection connexion = null;
try{
    // On se connecte à la base de donnée 
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
//On excécute la requete et on sauvegarde le résultat
ResultSet rs = connexion.createStatement().executeQuery("SELECT DISTINCT NOM FROM adherent");
while(rs.next()){
 // On obtient des chaines de caractère du résultat de la requete
    System.out.println(rs.getString(1));

    Comboname.getItems().add(rs.getString(1));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulter_AdherentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        // si l'utilisateur fait un retour sur la page d'accueil
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
         if (Comboname.getValue().isEmpty() || Comboname.getValue().equals("")){        // si aucun adherent n'est sélectionné
             Lerror.setVisible(true);
         } 
          else if (Check_pret.isSelected()){                // si la case de pret est sélectionné
                    if (Check_coordonnees.isSelected()){        // si la case des coordonnées est également sélectionnée (les deux cases sont sélectionnées)
                        FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AdherentPretCoordonnees.fxml"));
                Parent root = loader.load();
                AdherentPretCoordonneesController pret_coordonees = loader.getController();
                pret_coordonees.myFonction(Comboname.getValue());
                Scene sc = new Scene(root);
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(sc);
                stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
                stage.show();
                stage.setTitle("Consulter les adhérents");
                    } else{                                     // si seul la case de pret est sélectionné

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AdherentPret.fxml"));
                Parent root = loader.load();
                AdherentPretController pret = loader.getController();
                pret.myFonction(Comboname.getValue());
                Scene sc = new Scene(root);
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(sc);
                stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
                stage.show();
                stage.setTitle("Consulter les adhérents");
            }
          }
            else if (Check_coordonnees.isSelected()){           //si la case de coordonnée est sélectionnée
                if(Check_pret.isSelected()){            // si la case de pret est également sélectionnée (les deux cases sont sélectionnées)
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AdherentPretCoordonnees.fxml"));
                Parent root = loader.load();
                AdherentPretCoordonneesController pret_coordonees = loader.getController();
                pret_coordonees.myFonction(Comboname.getValue());
                Scene sc = new Scene(root);
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(sc);
                stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
                stage.show();
                stage.setTitle("Consulter les adhérents");
                } else{         // si seul la case des coordonnees est sélectionnée

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AdherentCoordonnees.fxml"));
                Parent root = loader.load();
                AdherentCoordonneesController coordonees = loader.getController();
                coordonees.myFonction(Comboname.getValue());
                Scene sc = new Scene(root);
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(sc);
                stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
                stage.show();
                stage.setTitle("Consulter les adhérents");

            }
            }
          
            else {
                Lerror.setVisible(true);
            }
    } catch (Exception e){
        Lerror.setVisible(true);    // Exception attrapée si aucune valeur n'est sélectionnée
    }
    }
}


