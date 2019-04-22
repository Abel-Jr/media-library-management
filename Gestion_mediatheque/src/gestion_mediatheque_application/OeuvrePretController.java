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
public class OeuvrePretController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Oeuvre> tableview;
    @FXML
    private TableColumn<Oeuvre, String> tablename;
    @FXML
    private TableColumn<Oeuvre, String> table_oeuvre;
    @FXML
    private TableColumn<Oeuvre, String> table_auteur;
    @FXML
    private TableColumn<Oeuvre, Integer> table_exemplaire;
    @FXML
    private Button retour;
    @FXML
    private Button accueil;
    @FXML
    private Label Ltest;

    private ObservableList<Oeuvre> oeuvres;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  public void myFonction (String value){
       // Le label prend la valeur du comBo box passée en paramètre
        Ltest.setText(value);
         Connection connexion = null;
try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
oeuvres=FXCollections.observableArrayList();
//On excécute et sauvegarde la requete 
ResultSet rs = connexion.createStatement().executeQuery("SELECT DISTINCT pret.nom_adherent,oeuvre.titre,oeuvre.auteur,oeuvre.exemplaire FROM oeuvre INNER JOIN pret ON oeuvre.titre=pret.oeuvre WHERE pret.oeuvre = '"+Ltest.getText()+"'"+" AND date_de_remise_reelle IS NULL");
while(rs.next()){
 // On obtient des objets de type string, et int d'après le résultat de la requete
    oeuvres.add(new Oeuvre (rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
}


} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(AdherentPretController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    // // Définir les valeurs des tables
    //NB.Property Value Factory doit être identique à celui défini dans la classe de modèle
    tablename.setCellValueFactory(new PropertyValueFactory<> ("nom_adherent"));
    table_oeuvre.setCellValueFactory(new PropertyValueFactory<> ("titre"));
    table_auteur.setCellValueFactory(new PropertyValueFactory<> ("auteur"));
    table_exemplaire.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getExemplaire().intValue()).asObject());
            
    tableview.setItems(null);
    tableview.setItems(oeuvres);

        
        
    }
    
    
    
    
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
            // si l'utilisateur sélectionne le boutton retour à la page précédente
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
            // si l'utilisateur sélectionne le boutton accueil
              int dialogButton = JOptionPane.YES_NO_OPTION;     //définition du boutton de dialogue
            int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous continuer ? ","Attention",dialogButton);       // valeur de la reponse
           if(dialogResult == JOptionPane.YES_OPTION){  //si l'utilisateur sélectionne l'option 'oui'
               // On affiche un messsage de redirection
        JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(
                null,
                        "\n"+
                        "Vous serez rediriger vers la page d'accueil",
                        "Information",

                        JOptionPane.INFORMATION_MESSAGE);
                // On charge l'interface de la page d'accueil
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
