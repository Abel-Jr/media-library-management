/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_mediatheque_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Gestion_mediatheque extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Gestion_mediatheque.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/gestion_mediatheque_application/logo-java.png"));
        stage.show();
        stage.setTitle("Mediatheque");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        
        
        launch(args);
    }
    
}
