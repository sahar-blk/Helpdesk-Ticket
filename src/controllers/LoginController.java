/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sahar
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static int id;
    public static int getId() {
        return id;
    }
 
    public static void setId(int usr) {
        LoginController.id = usr;
    }
    @FXML
    private JFXButton btnlogin;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField email;
    
    @FXML
    private Button signup;
    private Stage stage;
     private Scene scene;
     private Parent root;
     java.sql.Connection conn ; 
     PreparedStatement  pst;
     ResultSet res ;

        
        
         public void Openlogin(ActionEvent event) throws  SQLException, IOException  {
        
        String username=email.getText();
        String password=txtpassword.getText();
        
        
        if (username.equals("") && password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "username or password blank");
            
        }
        else{
            try {
                
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                 conn =   DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk","root","");
                 String sql = "select * from utilisateur where email=? and password=? ";
                 
                 pst = conn.prepareStatement(sql);
                 pst.setString(1,username);
                 pst.setString(2,password);
                 
                 
                 res = pst.executeQuery();
                 System.out.println(res.toString());
                 
                 if (res.next())
                 {
                     
                     LoginController.setId(res.getInt("id"));
                     int id = LoginController.getId();
                     String type = res.getString("type");
                     if (type.equals("user")){
                      Stage logp = new Stage();
                      btnlogin.getScene().getWindow().hide();
                      Parent rootA;
                      rootA = FXMLLoader.load(getClass().getResource("../views/DashbordUtilisateur.fxml"));
                      Scene scene = new Scene(rootA);
                      logp.setScene(scene);
                      logp.show();
                      logp.setTitle("Acceuil");

                     
                     }
                     else if (type.equals("admin")){
                          Stage logp = new Stage();
                      btnlogin.getScene().getWindow().hide();
                      Parent rootA;
                      rootA = FXMLLoader.load(getClass().getResource("../views/DashbordAdmin.fxml"));
                      Scene scene = new Scene(rootA);
                      logp.setScene(scene);
                      logp.show();
                      logp.setTitle("Acceuil");
                      System.out.println(id);

                      
                     }
                     else if (type.equals("tech")){
                      Stage logp = new Stage();
                      btnlogin.getScene().getWindow().hide();
                      Parent rootA;
                      rootA = FXMLLoader.load(getClass().getResource("../views/DashbordTechnicien.fxml"));
                      Scene scene = new Scene(rootA);
                      logp.setScene(scene);
                      logp.show();
                      logp.setTitle("Acceuil");
                      System.out.println(id);
                 
                     
                     }
                     
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "login failed");
                 }
                 
                
            
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
    }
    @FXML
    void OpenSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
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
