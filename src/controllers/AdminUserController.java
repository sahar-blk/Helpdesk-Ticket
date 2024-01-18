/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package controllers;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author sahar
 * 
 */

public class AdminUserController implements Initializable {

   

    @FXML
    private BorderPane border;
     @FXML
    private TextField Email;

    @FXML
    private PasswordField Mdp;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Poste;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Tel;

    

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private TableColumn<Utilisateur,String> email;

    @FXML
    private TableColumn<Utilisateur,String> idCol;
    

    @FXML
    private TableColumn<Utilisateur,String> nom;

    @FXML
    private TableColumn<Utilisateur,String> poste;

    @FXML
    private TableColumn<Utilisateur,String> prenom;

    @FXML
    private TableView<Utilisateur> tableuser;

    @FXML
    private TableColumn<Utilisateur,String> tel;
 

 @FXML
    private void handleButtonAction(ActionEvent event) {
    
    }
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
          ObservableList<Utilisateur> users = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select id,nom,prenom,email,password,tel,poste,type from utilisateur where type='user'");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Utilisateur st = new Utilisateur();
            st.setId(rs.getString("id"));
            st.setNom(rs.getString("nom"));
            st.setPrenom(rs.getString("prenom"));
            st.setEmail(rs.getString("email"));
            st.setPassword(rs.getString("password"));
            st.setTel(rs.getString("tel")); 
            st.setPoste(rs.getString("poste"));
              
           
            users.add(st);
       }
    } 
                tableuser.setItems(users);
                idCol.setCellValueFactory(f -> f.getValue().idProperty());
                
                nom.setCellValueFactory(f -> f.getValue().nomProperty());
                
                prenom.setCellValueFactory(f -> f.getValue().prenomProperty());
                email.setCellValueFactory(f -> f.getValue().emailProperty());
                poste.setCellValueFactory(f -> f.getValue().posteProperty());
                tel.setCellValueFactory(f -> f.getValue().telProperty());
                
                
               
       }
       
       catch (SQLException ex) 
       {
         // Logger.getLogger(AdminUserControllerController.class.getName()).log(Level.SEVERE, null, ex);
       }
                tableuser.setRowFactory( tv -> {
             TableRow<Utilisateur> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableuser.getSelectionModel().getSelectedIndex();
         
                   id = Integer.parseInt(String.valueOf(tableuser.getItems().get(myIndex).getId()));

                   Nom.setText(tableuser.getItems().get(myIndex).getNom());
                    Prenom.setText(tableuser.getItems().get(myIndex).getPrenom());
                  Tel.setText(tableuser.getItems().get(myIndex).getTel());
                  Email.setText(tableuser.getItems().get(myIndex).getEmail());
                  Mdp.setText(tableuser.getItems().get(myIndex).getPassword());
                     Poste.setText(tableuser.getItems().get(myIndex).getPoste());
                           
                         
                           
                }
             });
                return myRow;
                   });
    
    
      }
     
      @FXML
    void Add(ActionEvent event) {
        
            String stnom,stprenom,stemail,stposte,sttel,stpassw;
            stnom = Nom.getText();
            stprenom=Prenom.getText();
            stemail=Email.getText();
            stposte=Poste.getText();
            sttel=Tel.getText();
            stpassw=Mdp.getText();

        try 
        {
            pst = con.prepareStatement("INSERT INTO `utilisateur`(`nom`, `prenom`, `email`, `password`, `tel`, `poste`, `type`,`groupe`) values(?,?,?,?,?,?,?,?)");
            pst.setString(1, stnom);
            pst.setString(2, stprenom);
            pst.setString(3, stemail);
             pst.setString(4, stpassw);
               pst.setString(5, sttel);
               pst.setString(6, stposte);
               pst.setString(7, "user");
              pst.setInt(8, 0);

            
            pst.executeUpdate();
          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout Utilisateur");
        
        alert.setHeaderText("Utilisateur ajouté");
        alert.setContentText("Ajouter!");
        alert.showAndWait();
            table();
            
            Prenom.setText("");
            Nom.setText("");
            Email.setText("");
            Tel.setText("");
            Poste.setText("");
            Mdp.setText("");       } 
        catch (SQLException ex)
        {
            Logger.getLogger(AdminUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    


    
     @FXML
    void Delete(ActionEvent event) {
     myIndex = tableuser.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(tableuser.getItems().get(myIndex).getId()));
            System.out.println(id);         
        try 
        {
            pst = con.prepareStatement("delete from utilisateur where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression utilisateur");
        
        alert.setHeaderText("Suppression utilisateur");
        alert.setContentText("Deletedd!");
        alert.showAndWait();
                  table();
        } 
        catch (SQLException ex)
        {
          //  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void Update(ActionEvent event) {
       
       String stnom,stprenom,stemail,stposte,sttel,stpassw;
        
         myIndex = tableuser.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(tableuser.getItems().get(myIndex).getId()));
           
           
            stnom = Nom.getText();
            stprenom=Prenom.getText();
            stemail=Email.getText();
            stposte=Poste.getText();
            sttel=Tel.getText();
            stpassw=Mdp.getText();
        try 
        {
            pst = con.prepareStatement("UPDATE `utilisateur` SET `nom`=?,`prenom`=?,`email`=?,`password`=?,`tel`=?,`poste`=?,`type`=?,`groupe`=? WHERE id=? ");
            pst.setString(1, stnom);
            pst.setString(2, stprenom);
            pst.setString(3, stemail);
             pst.setString(4, stpassw);
               pst.setString(5, sttel);
               pst.setString(6, stposte);
               pst.setString(7, "user");
               pst.setInt(8, 0);
             pst.setInt(9, id);
           pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modification d'utilisateur");
        
        alert.setHeaderText("Utilisateur modifié");
        alert.setContentText("Updateddd!");
        alert.showAndWait();
                table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(AdminUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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