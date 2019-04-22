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
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
public class RemiseActionController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Ladherent;
    @FXML
    private Label Loeuvre;
    @FXML
    private Label Lremise;
    @FXML
    private ComboBox<String> Combo_adherent;
    @FXML
    private ComboBox<String> Combo_oeuvre;
    @FXML
    private DatePicker date_remise;
    @FXML
    private Button retour;
    @FXML
    private Button enregistrer_quitter;
    @FXML
    private Label Lerror1;
    @FXML
    private Label Lid;
    @FXML
    private ComboBox<String> Combo_id;
    @FXML
    private Label Lerror;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           // On fait appel à la méthode addDateRemis() de la classe de notre base de donnée
        DBConnection.addDateRemise();
        
                      Connection connexion = null;
try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
//On excécute les requetes et on sauvegarde les résultats
ResultSet nom = connexion.createStatement().executeQuery("SELECT DISTINCT pret.nom_adherent FROM pret WHERE date_de_remise_reelle IS NULL");
ResultSet oeuvre = connexion.createStatement().executeQuery("SELECT DISTINCT pret.oeuvre FROM pret WHERE date_de_remise_reelle IS NULL");
ResultSet pret_id = connexion.createStatement().executeQuery("SELECT DISTINCT pret.id_pret FROM pret WHERE date_de_remise_reelle IS NULL");
// On obtient des Strings, des valeurs des resultats que l'on insère dans la Combo box correspondante
while(nom.next()){
    
    Combo_adherent.getItems().add(nom.getString(1));
    
}
while(oeuvre.next()){
    Combo_oeuvre.getItems().add(oeuvre.getString(1));
}
while (pret_id.next()){
    Combo_id.getItems().add(pret_id.getString(1));
}
} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(RemiseActionController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void EnregistrerQuitterAction(ActionEvent event) throws IOException  {
        try{
                if (Combo_adherent.getValue().equals("") || Combo_oeuvre.getValue().equals("") || date_remise.getValue().toString().equals("")){   // si l'un des champs est vide 
                Lerror1.setVisible(true);
                }
        
            int dialogButton = JOptionPane.YES_NO_OPTION;       // On déclare le bouttton de dialogue
            int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton); // valeur du résultat
            if(dialogResult == JOptionPane.YES_OPTION){ // si l'utilisateur choisit l'option oui
        
            Connection connexion = null;
        
            try {
                //On se connecte à la base de donnée
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                System.out.println("Le pilote JDBC MySQL a été chargé");
                connexion = DriverManager.getConnection(DBConnection.url);
                // On execute des requetes et on sauvegarde des résultats
                ResultSet statement = connexion.createStatement().executeQuery("SELECT date_de_remise_attendue FROM pret WHERE nom_adherent = '"+Combo_adherent.getValue()+"'"+"AND oeuvre = '" +Combo_oeuvre.getValue()+"' AND id_pret = "+Combo_id.getValue());
                ResultSet state = connexion.createStatement().executeQuery("SELECT date_de_pret FROM pret WHERE nom_adherent = '"+Combo_adherent.getValue()+"'"+"AND oeuvre = '" +Combo_oeuvre.getValue()+"' AND id_pret = "+Combo_id.getValue());
                
                if (statement.next()){  //si le resultat n'est pas vide autrement dit  si le pret existe ou toutes les données du pret sont vraies
                    Date date = statement.getDate(1);   // on convertit le résultat de la requete en Date pour pouvoir faire une comparaison
                    
                    if(date_remise.getValue().isAfter(date.toLocalDate())){         // si la date du rendue est postérieure à la date limite
                    Period intervalleperiod =Period.between(date.toLocalDate(), date_remise.getValue());
                    intervalleperiod.getDays();
                        if(intervalleperiod.getYears()!=0){         //si la différence entre les deux dates n'est pas nulle en terme d'années
                            JOptionPane.showMessageDialog(null, "Le delai de ce pret est dépassé de "+intervalleperiod.getYears()+"an(s) "+intervalleperiod.getMonths()+" mois et "+ intervalleperiod.getDays()+ " jours ", "Attention", JOptionPane.ERROR_MESSAGE);
                                
                            }
                        else{
                            if (intervalleperiod.getMonths()!=0){   // si la différence entre les deux dates en termes de mois n'est pas nulle
                                JOptionPane.showMessageDialog(null, "Le delai de ce pret est dépassé de "+intervalleperiod.getMonths()+" mois et "+ intervalleperiod.getDays()+ " jours ", "Attention", JOptionPane.ERROR_MESSAGE);
                                }
                            else{                                   // sila différence entre les deux dates ne s'expriment qu'en jours
                                JOptionPane.showMessageDialog(null, "Le delai de ce pret est dépassé de "+ intervalleperiod.getDays()+ " jours ", "Attention", JOptionPane.ERROR_MESSAGE);
                            }
                            
                            
                        }
                        // Les statements étant fermés, on fait des mises à jour sur les dates et le nombre d'oeuvres meme si le délai de rendue est dépassé
                        int mise_a_jour_date = connexion.createStatement().executeUpdate("UPDATE pret set date_de_remise_reelle = "+ "'"+date_remise.getValue() +"'"+ "WHERE nom_adherent = "+"'"+Combo_adherent.getValue()+"'"+"AND oeuvre = " + "'"+Combo_oeuvre.getValue()+"'"+"AND id_pret = "+Combo_id.getValue());
                        int mise_a_jour =connexion.createStatement().executeUpdate("UPDATE oeuvre set exemplaire = exemplaire+1 WHERE titre = '"+Combo_oeuvre.getValue()+"'");
                        
                        // On affiche un message
                        JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "Vous serez rediriger vers la page d'accueil",
                        "Operation reussie,",

                        JOptionPane.INFORMATION_MESSAGE);
                
                        //On charge la page d'accueil 
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
    
                } else{             // si les données concernant les prets sont éronnées 
                    JOptionPane.showMessageDialog(null, "Ce pret n'existe pas\n car l'id de ce pret ne correspond pas à cet adherent ou à cet oeuvre"+"\n Vous pouvez consulter les prets en cours pour un adherent dans le sous menu \n"+"\n Consulter un adherent ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    int dialogButton1 = JOptionPane.YES_NO_OPTION;                  // on déclare des valeurs pour les boutons de dialogue
            int dialogResult1 = JOptionPane.showConfirmDialog (null, "Voulez vous etre rediriger vers la page de consultation ? ","Information",dialogButton1);         // valeur du résultat
            if(dialogResult1 == JOptionPane.YES_OPTION){    //si l'utilisateur choisit l'option oui
            
            //On charge la page de consultation    
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
                }
                
                   if (state.next()){   // si la date de pret existe
                    Date date_pret = state.getDate(1);      //On convertit le resultat en Date pour faire la convertit
                    System.out.println(date_pret);
                    if(date_pret.toLocalDate().isAfter(date_remise.getValue())){        // si la date de remise est avant la date d'emprunt chose qui est impossible
                        Lerror.setVisible(true);
                        JOptionPane.showMessageDialog(null, "La date de remise ne peut etre antérieure à celle de l'emprunt  \n Vous serez rediriger vers la page de consultation ", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                }
                   else{    // on excécute les requetes de mises à jour sur les dates et les oeuvres
PreparedStatement posted = connexion.prepareStatement("UPDATE pret set date_de_remise_reelle = "+ "'"+date_remise.getValue() +"'"+ "WHERE nom_adherent = "+"'"+Combo_adherent.getValue()+"'"+"AND oeuvre = " + "'"+Combo_oeuvre.getValue()+"'"+"AND id_pret = "+Combo_id.getValue());
posted.execute();
posted.close();
PreparedStatement update = connexion.prepareStatement("UPDATE oeuvre set exemplaire = exemplaire+1 WHERE titre = '"+Combo_oeuvre.getValue()+"'");
update.execute();
update.close();

    // On affiche une fenetre
    JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil \n",
                        "Operation reussie",

                        JOptionPane.INFORMATION_MESSAGE);
         
        
            connexion.close();
             System.out.println("Insert Complete");
            // on charge la page d'accueil
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

} catch (Exception e){
        Lerror1.setVisible(true);
        
    }
            
            
            
}
        }catch (Exception e) {      // exception attrapée si aucune valeur n'est sélectionnée
JOptionPane.showMessageDialog(null, "Echec de l'operation", "ERROR", JOptionPane.ERROR_MESSAGE);}
    finally {
           
    }   
    }
}
    

