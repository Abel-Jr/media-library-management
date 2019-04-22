/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Gestion_mediathequeController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane pane;
    @FXML
    private MenuBar menu_bar;
    @FXML
    private MenuItem nouvel_oeuvre;
    @FXML
    private MenuItem consulter_oeuvre;
    @FXML
    private MenuItem supprimeroeuvre;
    @FXML
    private MenuItem nouveau_adherent;
    @FXML
    private MenuItem consulter_adherents;
    @FXML
    private MenuItem supprimer_adherents;
    @FXML
    private MenuItem enregistrer_pret;
    @FXML
    private MenuItem remise;
    @FXML
    private MenuItem fermeture;
    @FXML
    private Menu oeuvres;
    @FXML
    private Menu adherents;
    @FXML
    private Menu pret;
    @FXML
    private ImageView image;
    @FXML
    private Menu quitter;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               try {
            DBConnection.getConnection();
            //DBConnection.CreateDatabase();
            DBConnection.AdherentTable();
            DBConnection.OeuvreTable();
            DBConnection.PretTable();
        } catch (Exception ex) {
            Logger.getLogger(Gestion_mediathequeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void NouvelleOeuvreAction(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Nouvelle_Oeuvre.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            
            stage.show();
            stage.setTitle("Nouvelle Oeuvre");

            
    }

    @FXML
    private void ConsulterOeuvreAction(ActionEvent event) throws IOException {
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
    private void SupprimerOeuvreAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Supprimer_Oeuvre.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(sc);
        stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
        stage.show();
        stage.setTitle("Supprimer une oeuvre");

            
    }

    @FXML
    private void NouveauAdherentAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Nouveau_Adherent.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(sc);
        ImageView iv = new ImageView(getClass().getResource("logo-java.png").toExternalForm());
        stage.show();
        stage.setTitle("Nouvel adhérent");
            
            
           
    }

    @FXML
    private void ConsulterAdherentsAction(ActionEvent event) throws IOException {
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
    private void SuppressionAdherentAction(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Supprimer_adherent.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Supprimer un adherent");

            
             
    }

    @FXML
    private void EnregistrerPretAction(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Enregistrer_Pret.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Enregistrer un pret");

            
             
    }

    @FXML
    private void RemiseAction(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RemiseAction.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
            stage.show();
            stage.setTitle("Enregistrer une remise");

            
             
    }

    @FXML
    private void FermetureAction(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Gestion_mediatheque.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(sc);
            stage.close();
    }
    
}
