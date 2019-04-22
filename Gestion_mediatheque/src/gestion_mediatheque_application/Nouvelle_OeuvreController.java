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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class Nouvelle_OeuvreController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Ltitre;
    @FXML
    private Label Lauteur;
    @FXML
    private Label Lnombre_exemplaire;
    @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtauteur;
    @FXML
    private TextField txtn_exemplaire;
    @FXML
    private Label Lerror;

    Random r = new Random();
    @FXML
    private Label Lerror1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
            // si l'utilisateur sélectionne le bouton 'retour' on revient à la page d'accueil
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
        //si l'utilisateur sélectionne le bouton 'valider'
        if (txttitre.getText().equals("") || txtauteur.getText().equals("") || txtn_exemplaire.getText().equals("")){   //dans le cas ou l'un des champs au moins est sans valeur
            Lerror1.setVisible(false);
            Lerror.setVisible(true);                        // Un message d'erreur sera affiché
        } else {
            
               int dialogButton = JOptionPane.YES_NO_OPTION;                                // On déclare le button
    int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton); // La valeur de résultat
if(dialogResult == JOptionPane.YES_OPTION){ // Si l'utilisateur choisit un l'option oui
  
    
    int valeur = 10000 + r.nextInt(99999 - 10000);          // nombre aléatoire générée entre 10.000 et 99.999

        Connection connexion = null;
        
try {
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
// On s'assure que la connexion ait été établi et ensuite on insère les données dans la table
PreparedStatement posted = connexion.prepareStatement("INSERT INTO oeuvre (id_oeuvre,titre,auteur,exemplaire) VALUES ("+valeur+","+"'"+txttitre.getText()+"'"+","+"'"+txtauteur.getText()+"'"+","+txtn_exemplaire.getText()+")");
posted.execute();

posted.close();
connexion.close();
// On affiche un message de réussite 
 JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil",
                        "Operation reussie",

                        JOptionPane.INFORMATION_MESSAGE);
} catch (Exception e) {
Lerror.setVisible(false);
JOptionPane.showMessageDialog(null, "Echec de l'operation", "ERROR", JOptionPane.ERROR_MESSAGE);

} finally {
           // On excécutera cette partie à la fin soit dans le cas d'une opération réussie, soit dans le cas d'une erreur
           // Redirection vers la page d'accueil
            System.out.println("Insert Complete");
          
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
    } else {
    
}
    }
    }
}
    
    

