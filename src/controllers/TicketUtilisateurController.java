/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
import Model.Ticket;
import Model.TicketCreationException;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author molka
 */
public class TicketUtilisateurController implements Initializable {

    @FXML
    private TextArea Description;

    @FXML
    private BorderPane border;

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
    private ComboBox<?> listetech;

    @FXML
    private TableColumn<Ticket, String> priorite;
 @FXML
    private TableColumn<Ticket, String> responsable;
    @FXML
    private ComboBox<String> prioriteform;

    @FXML
    private TableView<Ticket> tableticket;
      
    private String[] pri ={"faible", "moyenne","urgent"};
    
    
        Connection con;
    PreparedStatement pst;
     PreparedStatement pst1;
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
    
     int userId= LoginController.getId();
      @FXML
    void Add(ActionEvent event) throws TicketCreationException {
        
            String sdescript,stprior,stdatecrea,stetat,stsolu,stdarecloture;
            sdescript = Description.getText();
            if (sdescript.isEmpty() || prioriteform.getSelectionModel().isEmpty() || groupeliste.getSelectionModel().isEmpty()) {
        throw new TicketCreationException("Veuillez remplir tous les champs obligatoires");
}

        try 
        {

            pst = con.prepareStatement("INSERT INTO ticket(description, priorite, datecreation, etat, solution,responsable,proprietaire) values(?,?,?,?,?,?,?)");
            pst.setString(1, sdescript);
            if(prioriteform.getSelectionModel().getSelectedItem().toString().equals("Haute")){
              pst.setString(2,"Haute" );
            }
            if(prioriteform.getSelectionModel().getSelectedItem().toString().equals("Moyenne")){
              pst.setString(2,"Moyenne" );
            }
             if(prioriteform.getSelectionModel().getSelectedItem().toString().equals("Faible")){
              pst.setString(2,"Faible" );
            }
            
            pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
             pst.setString(4, "non cloturer");
               pst.setString(5, "");

             if(groupeliste.getSelectionModel().getSelectedItem().toString().equals("software")){
              pst.setInt(6,1 );
            }
             if(groupeliste.getSelectionModel().getSelectedItem().toString().equals("hardware")){
              pst.setInt(6,2 );
            }
             pst.setInt(7, userId);
            pst.executeUpdate();
          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout Ticket");
        
        alert.setHeaderText("Ticket ajouté");
        alert.setContentText("Ajouter!");
        alert.showAndWait();
            
            Description.setText("");
           
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(AdminUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
     public void table()
      {
          Connect();
          ObservableList<Ticket> tickets = FXCollections.observableArrayList();
       try 
       {

           pst = con.prepareStatement("SELECT id, description, priorite, datecreation, etat, solution, datecloture, responsable FROM ticket where proprietaire=? AND etat!='cloturé'");  
            pst.setInt(1, userId);
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
           Ticket st = new Ticket();
            st.setId(rs.getString("id"));
            st.setDescription(rs.getString("description"));
            st.setPriorite(rs.getString("priorite"));
            st.setDatecreation(rs.getDate("datecreation"));
            st.setEtat(rs.getString("etat"));
            st.setSolution(rs.getString("solution")); 
            st.setResponsable(rs.getString("responsable"));
              
           
            tickets.add(st);
       }
    } 
                tableticket.setItems(tickets);
                idCol.setCellValueFactory(f -> f.getValue().idProperty());
                
                description.setCellValueFactory(f -> f.getValue().descriptionProperty());
                priorite.setCellValueFactory(f -> f.getValue().prioriteProperty());
                datecreation.setCellValueFactory(f -> f.getValue().datecreationProperty());
                etat.setCellValueFactory(f -> f.getValue().etatProperty());
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
    
         @FXML
    void Delete(ActionEvent event) {
     myIndex = tableticket.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(tableticket.getItems().get(myIndex).getId()));
            System.out.println(id);         
        try 
        {
            pst = con.prepareStatement("delete from ticket where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression ticket");
        
        alert.setHeaderText("Suppression ticket");
        alert.setContentText("Deletedd!");
        alert.showAndWait();
                  table();
        } 
        catch (SQLException ex)
        {
          //  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    
    
   ObservableList listegr=FXCollections.observableArrayList();
    ObservableList listepr=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            if (border == null) {
                System.err.println("BorderPane is not injected! Check your FXML file.");
                return;
            }

            Parent sidebar = FXMLLoader.load(getClass().getResource("../views/sidebardashbordutilisateur.fxml"));
     listegr.addAll("software","hardware");
        groupeliste.setItems(listegr);
     listepr.addAll("Haute","Moyenne","Faible");        
            prioriteform.setItems(listepr);
            
            
            
            
            
            
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