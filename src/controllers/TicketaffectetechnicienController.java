package controllers;

import Model.GestionnaireTaches;
import Model.Tache;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
public class TicketaffectetechnicienController implements Initializable {
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
    
    
      @FXML
    void Add(ActionEvent event) {
        
            String sdescript,stprior,stdatecrea,stetat,stsolu,stdarecloture;
            sdescript = Description.getText();
            

        try 
        {
            pst = con.prepareStatement("INSERT INTO ticket(description, priorite, datecreation, etat, solution,responsable) values(?,?,?,?,?,?)");
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
    
     @FXML
    void Update(ActionEvent event) {
       
       String stnom,stprenom,stemail,stposte,sttel,stpassw;
        
         myIndex = tableticket.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(tableticket.getItems().get(myIndex).getId()));
           
           
            stnom = Description.getText();
           
        try 
        {
            pst = con.prepareStatement("UPDATE ticket SET etat=?,solution=?,datecloture=? where id = ?  ");
            pst.setString(1, "cloturé");
            pst.setString(2, stnom);
            pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            pst.setInt(4, id); 
              
          
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
        int groupe;
        int userId= LoginController.getId();

    public void table()
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
           pst = con.prepareStatement("SELECT id, description, priorite, datecreation, etat, solution, datecloture, responsable FROM ticket WHERE responsable=? AND etat!='cloturé'");  
           pst.setInt(1, groupe);
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
            //st.setType(rs.getString("type"));
              
           
            tickets.add(st);
       }
    } 
      

         List<Ticket> sortedTickets = tickets.stream()
                .sorted((t1, t2) -> {
                    String priority1 = t1.getPriorite().toLowerCase();
                    String priority2 = t2.getPriorite().toLowerCase();

                    if (priority1.equals("haute")) return -1;
                    if (priority2.equals("haute")) return 1;
                    if (priority1.equals("moyenne") && !priority2.equals("haute")) return -1;
                    if (priority2.equals("moyenne") && !priority1.equals("haute")) return 1;
                    return 0;
                })
                .collect(Collectors.toList());

        tableticket.setItems(FXCollections.observableArrayList(sortedTickets));
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
            pst = con.prepareStatement("UPDATE ticket SET etat='EC' where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
              Tache tache = new Tache("Prendre en charge", 3);
                GestionnaireTaches gestionnaire = new GestionnaireTaches();
        gestionnaire.exécuterTâche(tache, t -> {
            System.out.println("Prendre en charge : ticket"  );
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ticket en cours de traitement");
        
        alert.setHeaderText("Nouvelle tache");
        alert.setContentText("tache ajouté!");
        alert.showAndWait();
        });
                  table();
        } 
        catch (SQLException ex)
        {
          //  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
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
           Connect();
           table();
          
            
            
            
          
            
        } catch (IOException ex) {
            System.err.println("Exception while loading FXML: " + ex);
        }
    }
}