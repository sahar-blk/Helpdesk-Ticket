/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 
 */
public class SidebardashbordutilisateurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button ticketUser;
    @FXML
    private Button sedeconnecterUser;


    @FXML
    void OpensedeconnectersedeconnecterUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    
    private Stage stage ;
     private Scene scene;
     private Parent root;
     @FXML
    private Button helpdesk2;
    @FXML
    void openhelpdesk2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/DashbordUtilisateur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML    
    void OpenTicketUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/TicketUtilisateur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

      
    
}