/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
import Model.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import Model.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author sahar
 */
public class DashbordTechnicienController implements Initializable {

 
    
  
    @FXML
    private BorderPane border;
    @FXML
    private Label nbrusers;
     @FXML
    private Label nbrticket;
      @FXML
    private Label nbrtechnicien;
      
      

    @FXML
    private TableColumn<Ticket, Date> datecreation;

    @FXML
    private TableColumn<Ticket, String> description;

    @FXML
    private TableColumn<Ticket, String> etat;

    @FXML
    private ComboBox<String> groupeliste;

    @FXML
    private TableColumn<Ticket,String > idCol;

 

    @FXML
    private TableColumn<Ticket, String> priorite;
 @FXML
    private TableColumn<Ticket, String> responsable;
  

    @FXML
    private TableView<Ticket> tableticket;
    @FXML
    private Label dep;

    @FXML
    private Label mail;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
     private Label tel;
    
     int myIndex;
   int id;
    Connection con;
    PreparedStatement pst;
     PreparedStatement pst2;
      PreparedStatement pst1; PreparedStatement pst3;PreparedStatement pst4;PreparedStatement pst5;
         int userId= LoginController.getId();
         
    public void Connect()
    {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk","root","");
            System.out.println("connected succefuly 2");
            System.out.println(userId);
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public void table() {
    Connect();

    try {pst1 = con.prepareStatement("SELECT groupe FROM utilisateur WHERE id =? ");  
            pst1.setInt(1, userId);
            try (ResultSet rs1 = pst1.executeQuery()) {
               
                if (rs1.next()) {
                    groupe = rs1.getInt("groupe");
                    System.out.println("***************************************************************************");
                    System.out.println(groupe);
                }}
        pst = con.prepareStatement("SELECT COUNT(*) AS ticketCountAtr FROM ticket where etat!='cloturé' and responsable=?");
        pst.setInt(1, groupe);
        pst2 = con.prepareStatement("SELECT COUNT(*) AS ticketCount FROM ticket where etat='cloturé'");
        pst3 = con.prepareStatement("SELECT COUNT(*) AS ticketCountEC FROM ticket where etat='EC' and responsable=?");
        pst3.setInt(1, groupe);

        ResultSet rs = pst.executeQuery();
        ResultSet rs2 = pst2.executeQuery();
        ResultSet rs3 = pst3.executeQuery();

        if (rs.next()) {
            int userCount = rs.getInt("ticketCountAtr");
         

            // Affichage du nombre d'utilisateurs dans un Text nommé "nombreUtilisateursText"
            nbrusers.setText(""+userCount);
         
            
        }
            if (rs2.next()) {
           
            int ticketCount = rs2.getInt("ticketCount");
      

            // Affichage du nombre d'utilisateurs dans un Text nommé "nombreUtilisateursText"
      
            nbrticket.setText(""+ticketCount);
        
            
        }
                if (rs3.next()) {
          
            int technicienCount = rs3.getInt("ticketCountEC");
            
            // Affichage du nombre d'utilisateurs dans un Text nommé "nombreUtilisateursText"
         
            nbrtechnicien.setText(""+technicienCount);
            
        }
    }
    catch (SQLException ex) {
        // Gérer les exceptions SQL
        ex.printStackTrace();
    }
  }
   
          
          
          
          
          
          
          
           int groupe ;
             public void table2()
      {
          Connect();
          ObservableList<Ticket> tickets = FXCollections.observableArrayList();
       try 
       {
           
            pst1 = con.prepareStatement("SELECT groupe FROM utilisateur WHERE id =? ");  
            pst1.setInt(1, userId);
            try (ResultSet rs1 = pst1.executeQuery()) {
               
                if (rs1.next()) {
                    groupe = rs1.getInt("groupe");
                    System.out.println("***************************************************************************");
                    System.out.println(groupe);
                }}
            
           pst = con.prepareStatement("SELECT  description, priorite, responsable FROM ticket where datecreation= CURDATE() and responsable=? ");
           pst.setInt(1, groupe);
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
           Ticket st = new Ticket();
           
            st.setDescription(rs.getString("description"));
            st.setPriorite(rs.getString("priorite"));
         
            st.setResponsable(rs.getString("responsable"));
              
           
            tickets.add(st);
       }
    } 
                tableticket.setItems(tickets);
                
                
                description.setCellValueFactory(f -> f.getValue().descriptionProperty());
                priorite.setCellValueFactory(f -> f.getValue().prioriteProperty());
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
                
    
      }
   
          
          
          
          
          
          
          
          
          
          
          
    
    public void profileTech(){
                Connect();
                try{
                    pst = con.prepareStatement("SELECT prenom FROM utilisateur WHERE id =? ");  
            pst.setInt(1, userId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String prenomU = rs.getString("prenom");
                    prenom.setText(prenomU);
                }
                
            }
             pst2 = con.prepareStatement("SELECT nom FROM utilisateur WHERE id =? ");  
            pst2.setInt(1, userId);
            try (ResultSet rs1 = pst2.executeQuery()) {
                if (rs1.next()) {
                    String nomU = rs1.getString("nom");
                    nom.setText(nomU);
                }
            }
            pst3 = con.prepareStatement("SELECT tel FROM utilisateur WHERE id =?");
            pst3.setInt(1, userId);
            try (ResultSet rs2 = pst3.executeQuery()) {
                if (rs2.next()) {
                    String telephone= rs2.getString("tel");
                    tel.setText(telephone);
                }
            }
            pst4 = con.prepareStatement("SELECT email FROM utilisateur WHERE id =?");
             pst4.setInt(1, userId);
             try (ResultSet rs3 = pst4.executeQuery()) {
                if (rs3.next()) {
                    String email = rs3.getString("email");
                    mail.setText(email);
                }
            }
                    
                }
        catch (SQLException ex) {
        // Gérer les exceptions SQL
        ex.printStackTrace();
    }
        
        
    }
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
       profileTech();
  
     try {
           Connect();
 table();
 table2();
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
