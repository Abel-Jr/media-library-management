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
import java.sql.PreparedStatement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class Supprimer_OeuvreController implements Initializable {

    @FXML
    private ComboBox<String> Combotitre;
    @FXML
    private Label Lerror;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Lliste;
    @FXML
    private Button retour;
    @FXML
    private Button valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
                Connection connexion = null;
try{
    // On se connecte avec la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
// On excécute la requete et on sauvegarde le résultat de la requete
ResultSet rs = connexion.createStatement().executeQuery("SELECT DISTINCT titre FROM oeuvre");
while(rs.next()){
 // Obtenir des chaines de caractères (String) de la base de donnée
    System.out.println(rs.getString(1));

    Combotitre.getItems().add(rs.getString(1));         // On insère les valeurs de la requete dans la combo box
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(Supprimer_OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
            // si l'utilisateur choisit de faire un retour à la page d'accueil
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
        if (Combotitre.getValue().equals("")){      // Si aucune valeur n'est sélectionner
            Lerror.setVisible(true);
        } else{
     
               int dialogButton = JOptionPane.YES_NO_OPTION;                 // On déclare le button
    int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);       // La valeur de résultat
if(dialogResult == JOptionPane.YES_OPTION){         // Si l'utilisateur choisit un l'option oui
  
            Connection connexion = null;
            
            try {
                // On se connecte à la base de donnée 
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                System.out.println("Le pilote JDBC MySQL a été chargé");
                connexion = DriverManager.getConnection(DBConnection.url);
                // Une fois connectée on excécute la requete de suppression
                PreparedStatement posted = connexion.prepareStatement("DELETE from oeuvre where oeuvre.titre = "+ "'"+Combotitre.getValue()+"' " );
                posted.execute();
                
                posted.close();
                connexion.close();
                //On affiche un message en cas d'operation reussie
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez redirigé vers la page d'accueil",
                        "Operation reussie",

                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                // En cas d'erreur on affiche un essage d'erreur
                JOptionPane.showMessageDialog(null, "Echec de l'operation", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                // A la fin on revient à la page d'accueil
                System.out.println("Removal performed");
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
      } catch(Exception e){
          Lerror.setVisible(true);
      }
    }

    @FXML
    private void initialize(ActionEvent event) {
    }
    }
    

