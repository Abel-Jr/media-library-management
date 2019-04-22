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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Nouveau_AdherentController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private Button sauvegarder_quitter;
    @FXML
    private Button retour;
    @FXML
    private ImageView image;
    @FXML
    private Label Lnom;
    @FXML
    private Label Lprenom;
    @FXML
    private Label Ladresse;
    @FXML
    private Label Lerror;
    
    Random r = new Random();
    private DBConnection db;
    @FXML
    private Label Lexception;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            db = new DBConnection();
    }    

    @FXML
    private void Sauvegarder_QuitterAction(ActionEvent event) throws IOException {
        if (txtnom.getText().equals("") || txtprenom.getText().equals("") || txtadresse.getText().equals("")){      // si l'un des champs est nul, on affiche un message d'indication 
            Lexception.setVisible(false);
            Lerror.setVisible(true);
        } else{
            
                 int dialogButton = JOptionPane.YES_NO_OPTION;                              /// On veut avoir la valeurd du bouton d'un JOptionPane de type YES_NO_QUESTION
    int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);      
if(dialogResult == JOptionPane.YES_OPTION){                                                 // Si l'utilisateur choisit l'option 'oui'

    int valeur = 10000 + r.nextInt(99999 - 10000);      // Nombre aléatoire entre 10.000 et 99.999
            

        Connection connexion = null;            
        
try {
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
// On s'assure que la connexion a été établi et insère les valeurs dans la table adherent
PreparedStatement posted = connexion.prepareStatement("INSERT INTO adherent (id_adherent,nom,prenom,adresse) VALUES ("+valeur+","+"'"+txtnom.getText()+"'"+","+"'"+txtprenom.getText()+"'"+","+"'"+txtadresse.getText()+"'"+")");
posted.execute();

posted.close();
connexion.close();
  // On affiche un message d'operation réussie
    JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil",
                        "Operation reussie",

                        JOptionPane.INFORMATION_MESSAGE);
}
 catch (Exception e) {
// En cas d'erreur on affiche un message d'erreur
JOptionPane.showMessageDialog(null, "Echec de l'operation", "ERROR", JOptionPane.ERROR_MESSAGE);
} finally {
            // Cette partie sera excécuter en cas d'erreur ou de réussite, l'utilisateur sera redirigé vers la page d'accueil
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
    
}   else{
    //  dans le cas ou l'utilisateur ne choisit pas l'option oui
    //rien ne se passe on reste sur la page
}      
    
}
    }
    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        // dans le cas ou on sélection 'retour', on revitn à la page d'accueil
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
