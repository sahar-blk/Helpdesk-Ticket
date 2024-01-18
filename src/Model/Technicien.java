/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.beans.property.StringProperty;

/**
 *
 * @author 
 */
public final class Technicien extends Utilisateur{

    public Technicien(StringProperty id, StringProperty nom, StringProperty prenom, StringProperty email, StringProperty password, StringProperty tel, StringProperty poste, StringProperty type) {
        super(id, nom, prenom, email, password, tel, poste, type);
    }

    public Technicien() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
    
}
