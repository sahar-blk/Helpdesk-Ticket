/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author molka
 */
public sealed class Utilisateur  permits Technicien{
       private StringProperty id;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty email;
    private StringProperty password;
    private StringProperty tel;
    private StringProperty poste;
    private StringProperty type;
    //private StringProperty user_img;

    public Utilisateur(StringProperty id, StringProperty nom, StringProperty prenom, StringProperty email, StringProperty password, StringProperty tel, StringProperty poste, StringProperty type) {
        this.id = new SimpleStringProperty(this, "id");
        this.nom = new SimpleStringProperty(this, "nom");
        this.prenom = new SimpleStringProperty(this, "prenom");
        this.email = new SimpleStringProperty(this, "email");
        this.password = new SimpleStringProperty(this, "password");
        this.tel = new SimpleStringProperty(this, "tel");
        this.poste = new SimpleStringProperty(this, "poste");
        this.type = new SimpleStringProperty(this, "type");
  
    
    }

    public Utilisateur() {
         this.id = new SimpleStringProperty(this, "id");
        this.nom = new SimpleStringProperty(this, "nom");
        this.prenom = new SimpleStringProperty(this, "prenom");
        this.email = new SimpleStringProperty(this, "email");
        this.password = new SimpleStringProperty(this, "password");
        this.tel = new SimpleStringProperty(this, "tel");
        this.poste = new SimpleStringProperty(this, "poste");
        this.type = new SimpleStringProperty(this, "type");
    }

    
    /**
     * @return the id
     */
    
  public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String newId) {
        id.set(newId);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String newNom) {
        nom.set(newNom);
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String newPrenom) {
        prenom.set(newPrenom);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String newEmail) {
        email.set(newEmail);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String newPassword) {
        password.set(newPassword);
    }

    public StringProperty telProperty() {
        return tel;
    }

    public String getTel() {
        return tel.get();
    }

    public void setTel(String newTel) {
        tel.set(newTel);
    }

    public StringProperty posteProperty() {
        return poste;
    }

    public String getPoste() {
        return poste.get();
    }

    public void setPoste(String newPoste) {
        poste.set(newPoste);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String newType) {
        type.set(newType);
    }
}  
    
