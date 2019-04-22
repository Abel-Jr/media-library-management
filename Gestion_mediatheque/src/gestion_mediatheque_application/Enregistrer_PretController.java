/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class Enregistrer_PretController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Lnum;
    @FXML
    private Label Ladherent;
    @FXML
    private Label Loeuvre;
    @FXML
    private Label Ldate;
    @FXML
    private Button retour;
    @FXML
    private Button enregistrer_quitter;
    @FXML
    private Label Lnumberpret;
    @FXML
    private ComboBox<String> Combo_adherent;
    @FXML
    private ComboBox<String> Combo_oeuvre;
    @FXML
    private DatePicker date_pret;
    @FXML
    private Label Lerror;
    Random r = new Random();
    int  valeur = 10000 + r.nextInt(99999 - 10000);
    @FXML
    private Label Lerror1;
    
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
//On exécute la requete et on sauvegarde le résultat 
ResultSet rs = connexion.createStatement().executeQuery("SELECT DISTINCT NOM FROM adherent");
while(rs.next()){
 //  On obtient des String de la requete
    System.out.println(rs.getString(1));

    Combo_adherent.getItems().add(rs.getString(1));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(Enregistrer_PretController.class.getName()).log(Level.SEVERE, null, ex);
        }
Lnumberpret.setText(String.valueOf(valeur));    // Le label prendra la valeur du nombre généré aléatoirement



try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
//  On exécute la requete et on sauvegarde le résultat
ResultSet rs = connexion.createStatement().executeQuery("SELECT DISTINCT titre FROM oeuvre");
while(rs.next()){
 // On obtient des Strings de la requete
    System.out.println(rs.getString(1));

    Combo_oeuvre.getItems().add(rs.getString(1));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(Enregistrer_PretController.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
            // si l'utilisateur sélectionne le boutton retour
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
    private void EnregistrerQuitterAction(ActionEvent event) throws IOException {
        
        if(Combo_oeuvre.getValue().equals("") || Combo_oeuvre.getValue().equals("") || date_pret.getValue().equals("")){
                Lerror.setVisible(true);
        } else {
            
              int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);
if(dialogResult == JOptionPane.YES_OPTION){
        Connection connexion = null;
        LocalDate date = LocalDate.now();
        if(date.isAfter(date_pret.getValue())){
            Lerror1.setVisible(true);
        } else{
try {
    // on se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
       // Une fois connecté à la base de donnée, on insère cette valeure dans la table de pret et on fait une misère à jour sur le nombre d'exemplaire
       
PreparedStatement posted = connexion.prepareStatement("INSERT INTO pret (id_pret,nom_adherent,oeuvre,date_de_pret,date_de_remise_attendue) VALUES ( "+valeur+","+"'"+Combo_adherent.getValue()+"'"+","+"'"+Combo_oeuvre.getValue()+"'"+","+"'"+date_pret.getValue()+"'"+","+"'"+date_pret.getValue().plusDays(30)+"')");
posted.execute();
PreparedStatement update = connexion.prepareStatement("UPDATE oeuvre set exemplaire = exemplaire-1 WHERE titre = '"+Combo_oeuvre.getValue()+"'");
posted.close();
update.execute();
update.close();
connexion.close();

// On affiche un message d'information sur la politique de pret

JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Cette mediatheque offre un delai de pret de 30 jours à partir du jour d'emprunt\n"+
                        "Ce pret est valable jusqu'à la date suivante " + date_pret.getValue().plusDays(30)+"\n"+
                        "\n Vous serez rediriger vers la page d'accueil",
                        "Operation reussie",

                        JOptionPane.INFORMATION_MESSAGE);
} catch (Exception e) {
JOptionPane.showMessageDialog(null, "Echec de l'operation, veuillez recommencer", "ERROR", JOptionPane.ERROR_MESSAGE);

} finally {
        // A la fin on charge la page d'accueil
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
        System.out.println(date_pret.getValue());
    }
    }
}
    }
}




