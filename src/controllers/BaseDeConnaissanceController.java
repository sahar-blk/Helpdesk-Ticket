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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.layout.BorderPane;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author molka
 */
public class BaseDeConnaissanceController implements Initializable {

   
   @FXML
    private TextArea Description;

    @FXML
    private BorderPane border;

    @FXML
    private TableColumn<Ticket, Date> datecreation;
    
     @FXML
    private TableColumn<Ticket, Date> datecloture;
     
      
     @FXML
    private TableColumn<Ticket, String> solution;

    @FXML
    private TableColumn<Ticket, String> description;

    @FXML
    private TableColumn<Ticket, String> etat;

    

    @FXML
    private TableColumn<Ticket,String > idCol;


    @FXML
    private TableColumn<Ticket, String> priorite;
 @FXML
    private TableColumn<Ticket, String> responsable;
  

    @FXML
    private TableView<Ticket> tableticket;
      
 
    
    
        Connection con;
    PreparedStatement pst;
    int myIndex;
   int id;
    
    
    
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk","root","");
            System.out.println("connected succefuly 2");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
     public void table()
      {
          Connect();
          ObservableList<Ticket> tickets = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("SELECT  `description`, `priorite`, `datecreation`, `solution`, `datecloture`, `responsable` FROM ticket where etat='cloturé'");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
           Ticket st = new Ticket();
         
            st.setDescription(rs.getString("description"));
            st.setPriorite(rs.getString("priorite"));
            st.setDatecreation(rs.getDate("datecreation"));
            st.setSolution(rs.getString("solution")); 
            st.setDateCloture(rs.getDate("datecloture"));
            st.setResponsable(rs.getString("responsable"));
              
           
            tickets.add(st);
       }
    } 
                tableticket.setItems(tickets);
                
                description.setCellValueFactory(f -> f.getValue().descriptionProperty());
                priorite.setCellValueFactory(f -> f.getValue().prioriteProperty());
                datecreation.setCellValueFactory(f -> f.getValue().datecreationProperty());
                
                solution.setCellValueFactory(f -> f.getValue().solutionProperty());
    datecloture.setCellValueFactory(f -> f.getValue().dateClotureProperty());
                responsable.setCellValueFactory(f -> {
    String propertyValue = f.getValue().responsableProperty().getValue();

    if ("2".equals(propertyValue)) {
        System.out.println(propertyValue);
        return new SimpleStringProperty("hardware");
    } else {
        System.out.println(propertyValue);
        return new SimpleStringProperty("software");
    }
});

                
               
       }
       
       catch (SQLException ex) 
       {
         // Logger.getLogger(AdminUserControllerController.class.getName()).log(Level.SEVERE, null, ex);
       }
                tableticket.setRowFactory( tv -> {
             TableRow<Ticket> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableticket.getSelectionModel().getSelectedIndex();
         
                   id = Integer.parseInt(String.valueOf(tableticket.getItems().get(myIndex).getId()));

                   Description.setText(tableticket.getItems().get(myIndex).getDescription());
                
                           
                         
                           
                }
             });
                return myRow;
                   });
    
    
      }
    
       
    

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            if (border == null) {
                System.err.println("BorderPane is not injected! Check your FXML file.");
                return;
            }

            Parent sidebar = FXMLLoader.load(getClass().getResource("../views/sidebardashbord.fxml"));
  
            
            
            
            
            
            
            if (sidebar == null) {
                System.err.println("FXML file not loaded properly!");
                return;
            }

           border.setLeft(sidebar);
           Connect();
           table();
          
            
            
            
          
            
        } catch (IOException ex) {
            System.err.println("Exception while loading FXML: " + ex);
        }
    }
}

