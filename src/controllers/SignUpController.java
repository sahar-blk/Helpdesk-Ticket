/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sahar
 */

public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection con;
    PreparedStatement pst;
    int myIndex;
   int id;
    
    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private JFXButton btnsignup;

   

    @FXML
    private TextField email;
    
     
    
       @FXML
    private Button signup;
     
     @FXML
    private Button login;
      @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField post;
    

    
       
    private Stage stage;
     private Scene scene;
     private Parent root;
    @FXML
    void OpenLogin(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     
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
    void openLog(ActionEvent event)throws SQLException, IOException   {
        String stnom,stprenom,stemail,stposte,sttel,stpassw;
            stnom = lastname.getText();
            stprenom=firstname.getText();
            stemail=email.getText();
            stposte=post.getText();
            sttel=phone.getText();
            stpassw=password.getText();
//            mobile = txtMobile.getText();
//            course = txtCourse.getText();
        try 
        {
            Connect();
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
          



        btnsignup.getScene().getWindow().hide();
        Stage logp = new Stage();
        Parent roota = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Scene scn = new Scene(roota);
        logp.setScene(scn);
        logp.show();
            }catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
}
    }  
     
       
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
