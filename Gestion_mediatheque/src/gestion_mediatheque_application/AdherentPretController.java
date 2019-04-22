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
public class AdherentPretController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Pret> tableview;
    @FXML
    private TableColumn<Pret, String> tablename;
    @FXML
    private TableColumn<Pret, String> tableoeuvre;
    @FXML
    private TableColumn<Pret, Object> date_de_pret;
    @FXML
    private TableColumn<Pret, Object> date_de_remise;
    @FXML
    private Button retour;
    @FXML
    private Button accueil;
    @FXML
    private Label Ltest;
    
    private ObservableList<Pret> data;
    @FXML
    private TableColumn<Pret, Integer> table_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.addDateRemise();
    }    
    
    public void myFonction(String value){
             //Le label va prendre la valeur du ComBo box passé en paramètre dans la page de Consultation
        Ltest.setText(value);
         Connection connexion = null;
try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
data=FXCollections.observableArrayList();
//On excécute la requete la requete et on enregistre le resultat
ResultSet rs = connexion.createStatement().executeQuery("SELECT DISTINCT id_pret,nom_adherent,oeuvre,date_de_pret,date_de_remise_attendue FROM pret WHERE nom_adherent = '"+Ltest.getText()+"'"+" AND date_de_remise_reelle IS NULL");
while(rs.next()){
 // On obtient des types correspond au type de la table view (pour les dates ,on a choisit le type objet)
    data.add(new Pret (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getObject(4),rs.getObject(5)));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(AdherentPretController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    // Définir les valeurs des tables
    //NB.Property Value Factory doit être identique à celui défini dans la classe de modèle
    tablename.setCellValueFactory(new PropertyValueFactory<> ("nom"));
    tableoeuvre.setCellValueFactory(new PropertyValueFactory<> ("oeuvre"));
    date_de_pret.setCellValueFactory(data -> data.getValue().datepretProperty());
    date_de_remise.setCellValueFactory(data -> data.getValue().dateremiseProperty());
    table_id.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getId().intValue()).asObject());
    tableview.setItems(null);
    tableview.setItems(data);
     }

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        // si l'utilisateur sélectionne le bouton de retour
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
        
        
               int dialogButton = JOptionPane.YES_NO_OPTION;            // définition des bouttons de dialogue
            int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);   // valeur du boutton
           if(dialogResult == JOptionPane.YES_OPTION){      //si l'utilisateur choisit oui
               // on affiche un message 
        JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil",
                        "Information",

                        JOptionPane.INFORMATION_MESSAGE);
        
                // retour à la page d'accueil
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