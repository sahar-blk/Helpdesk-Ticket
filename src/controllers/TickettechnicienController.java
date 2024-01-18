/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Model.Technicien;
import Model.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import Model.Utilisateur;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author molka
 */
public class TickettechnicienController implements Initializable {
  @FXML
    private BorderPane border;

    @FXML
    private TableColumn<Ticket, Date> datecreation;

    @FXML
    private TableColumn<Ticket, String> description;

    @FXML
    private TableColumn<Ticket, String> etat;

    @FXML
    private TableColumn<Ticket, String> id;

    @FXML
    private TableColumn<Ticket, String> priorite;

    @FXML
    private TableView<Ticket> tableticket;
      @FXML
    private ComboBox<String> prioriteform;
    /**
     * Initializes the controller class.
     */
      
    private String[] pri ={"faible", "moyenne","urgent"};
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            if (border == null) {
                System.err.println("BorderPane is not injected! Check your FXML file.");
                return;
            }

            Parent sidebar = FXMLLoader.load(getClass().getResource("../views/sidebardashbordtechnicien.fxml"));
   
            if (sidebar == null) {
                System.err.println("FXML file not loaded properly!");
                return;
            }

           border.setLeft(sidebar);
           
          
            
            

          
            
        } catch (IOException ex) {
            System.err.println("Exception while loading FXML: " + ex);
        }
    }
}

