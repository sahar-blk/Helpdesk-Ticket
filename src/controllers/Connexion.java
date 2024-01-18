/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sahar
 */
public class Connexion {
    private static Connection con;


    public static Connection seConnecter() {
        if (con == null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk", "root","");
                System.out.println("connexion établie");

            } catch (ClassNotFoundException e) {
                System.out.println("driver non trouvé" + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("bd non trouvé ou problème d'identification " + ex.getMessage());
            }
        }
        return con;
    }
    
    
    
    

//   java.sql.PreparedStatement  pst;
//     ResultSet res ;
//        public int getId(String email,String password) throws SQLException{
//            con =   DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk","root","");
//                 String sql = "select * from utilisateur where email=? and password=? ";
//                 
//                 pst = con.prepareStatement(sql);
//                 pst.setString(1,email);
//                 pst.setString(2,password);
//                  res = pst.executeQuery();
//                 System.out.println(res.toString());
//                 
//                 if (res.next())
//                 {
//                     return res.getInt("id");
//                 }
//        return 0;
//            
//        }
}