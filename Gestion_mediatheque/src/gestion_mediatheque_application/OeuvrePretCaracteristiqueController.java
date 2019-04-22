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
import javafx.beans.property.SimpleStringProperty;
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

/**
 * FXML Controller class
 *
 * @author user
 */
public class OeuvrePretCaracteristiqueController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView image;
    @FXML
    private TableView<Oeuvre> tableview_oeuvre;
    @FXML
    private TableColumn<Oeuvre, String> tabletitre;
    @FXML
    private TableColumn<Oeuvre, String> tableauteur;
    @FXML
    private TableColumn<Oeuvre, Integer> tableexemplaire;
    @FXML
    private TableView<Pret> tableview_pret;
    @FXML
    private TableColumn<Pret, String> tablenom_adherent;
    @FXML
    private TableColumn<Pret, Integer> table_id_pret;
    
    @FXML
    private Button retour;
    @FXML
    private Label Ltest;

    private ObservableList<Oeuvre> oeuvre; 
    
    private ObservableList<Pret> pret;
    @FXML
    private TableColumn<Pret, Object> tabledatederemiseattendue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }    
   
    public void myFonction(String value){
        // Le label va prendre la valeur du Combo box passé en paramètre
        Ltest.setText(value);
         Connection connexion = null;
try{
    // On se connecte à la base de donnée
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
System.out.println("Le pilote JDBC MySQL a été chargé");
connexion = DriverManager.getConnection(DBConnection.url);
oeuvre=FXCollections.observableArrayList();
pret= FXCollections.observableArrayList();
//On excécute la requete et on sauvegarde le reslutat
ResultSet oeu = connexion.createStatement().executeQuery("SELECT DISTINCT oeuvre.titre,oeuvre.auteur,oeuvre.exemplaire FROM oeuvre WHERE oeuvre.titre = '"+Ltest.getText()+"'");
ResultSet pre = connexion.createStatement().executeQuery("SELECT DISTINCT pret.id_pret,pret.nom_adherent,pret.date_de_remise_attendue FROM pret INNER JOIN oeuvre ON oeuvre.titre=pret.oeuvre WHERE pret.oeuvre = '"+Ltest.getText()+"'"+" AND date_de_remise_reelle IS NULL");
while(oeu.next()){
 // On obtient des types String et int des valleurs de la requete
    oeuvre.add(new Oeuvre (oeu.getString(1),oeu.getString(2),oeu.getInt(3)));
}
while(pre.next()){
 // On obtient des types int, String et Object
    pret.add(new Pret (pre.getInt(1),pre.getString(2),pre.getObject(3)));
}

} catch (SQLException ex){
    System.out.println(ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(AdherentPretController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    // Définir les valeurs des tables
    //NB.Property Value Factory doit être identique à celui défini dans la classe de modèle
    
    tabletitre.setCellValueFactory(new PropertyValueFactory<> ("titre"));
    tableauteur.setCellValueFactory(new PropertyValueFactory<> ("auteur"));
    tableexemplaire.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getExemplaire().intValue()).asObject());
            
    tableview_oeuvre.setItems(null);
    tableview_oeuvre.setItems(oeuvre);

    table_id_pret.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getId().intValue()).asObject());
    tablenom_adherent.setCellValueFactory(new PropertyValueFactory<> ("nom"));
    tabledatederemiseattendue.setCellValueFactory(data -> data.getValue().dateremiseProperty());
    
    tableview_pret.setItems(null);
    tableview_pret.setItems(pret);
    }
    
    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        // si l'utilisateur sélectionne un retour à la page précedente
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
    
}
