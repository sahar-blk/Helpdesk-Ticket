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
public class SidebardashbordtechnicienController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button baseDeConnaissanceTech;

    @FXML
    private Button ticketAttTech;
    @FXML
    private Button sedeconnecterTech;
    @FXML
    private Button helpdesk1;
    @FXML
    void openhelpdesk1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/DashbordTechnicien.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void OpensedeconnecterTech(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private Button ticketTech;
    private Stage stage ;
     private Scene scene;
     private Parent root;
    @FXML
    void OpenBaseDeConnaissanceTech(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/BaseDeConnaissanceTech.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OpenTicketAttTech(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/Ticketaffectetechnicien.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OpenTicketTech(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/TicketAdmin.fxml"));
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