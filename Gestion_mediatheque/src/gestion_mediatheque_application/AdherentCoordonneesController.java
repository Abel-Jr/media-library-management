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
public class AdherentCoordonneesController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Adherent> tableview;
    @FXML
    private TableColumn<Adherent, String> tablenom;
    @FXML
    private TableColumn<Adherent, String> tableprenom;
    @FXML
    private TableColumn<Adherent, String> tableadresse;
    @FXML
    private Button retour;
    @FXML
    private Button accueil;
    @FXML
    private Label Ltest;
    
    private ObservableList<Adherent> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void myFonction(String value){
       // Le label va prendre la valeur du ComBo box passé en paramètre dans la page de Consultation
        Ltest.setText(value);
         Connection connexion = null;
try{
    //  On se connecte à la base de donnéee
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
data=FXCollections.observableArrayList();
//On excécute la requete et on enregistre le résultat
ResultSet rs = connexion.createStatement().executeQuery("SELECT nom,prenom,adresse FROM adherent WHERE nom = '"+Ltest.getText()+"'");
while(rs.next()){
 // On obtient des chaines de caractères de la base de donnée
    data.add(new Adherent (rs.getString(1),rs.getString(2),rs.getString(3)));       // On crée un objet de type Adherent
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(AdherentCoordonneesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    // Définir les valeurs des tables
    //NB.Property Value Factory doit être identique à celui défini dans la classe de modèle
    tablenom.setCellValueFactory(new PropertyValueFactory<> ("nom"));
    tableprenom.setCellValueFactory(new PropertyValueFactory<> ("prenom"));
 
    tableadresse.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
            
    tableview.setItems(null);
    tableview.setItems(data);
    
    
    }
    
    
    
    
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
            // si l'utilisateur fait un retour à la page précedente c'est à dire celle de consultation des adherents
             FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Consulter_adherent.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Consulter les adhérents");
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
                // si l'utilisateur sélectionne le bouton de retour
              int dialogButton = JOptionPane.YES_NO_OPTION;         // On déclare des bouttons de dialogue
            int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);   // La valeur du resultat
           if(dialogResult == JOptionPane.YES_OPTION){      // si l'utilisateur choisit l'option oui
               
               // On affiche une message de validation
        JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil",
                        "Information",

                        JOptionPane.INFORMATION_MESSAGE);
        
                // On revient à la page d'accueil
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
