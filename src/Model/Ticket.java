/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author molka
 */
public class Ticket {
    private StringProperty id;
    private StringProperty description;
    private StringProperty priorite;
    private SimpleObjectProperty<Date> datecreation;
    private StringProperty etat;
    private StringProperty solution;
    private SimpleObjectProperty<Date> datecloture;
     private StringProperty responsable;


    public Ticket() {
            this.id = new SimpleStringProperty(this, "id");
        this.description = new SimpleStringProperty(this, "description");
        this.priorite = new SimpleStringProperty(this, "priorite");
        this.datecreation = new SimpleObjectProperty<>(this, "datecreation");
        this.etat = new SimpleStringProperty(this, "etat");
        this.solution = new SimpleStringProperty(this, "solution");
        this.datecloture = new SimpleObjectProperty<>(this, "dateCloture");
        this.responsable = new SimpleStringProperty(this, "responsable");
    }

    public Ticket(StringProperty id, StringProperty description, StringProperty priorite, SimpleObjectProperty<Date> datecreation, StringProperty etat, StringProperty solution, SimpleObjectProperty<Date> datecloture) {
        this.id = new SimpleStringProperty(this, "id");
        this.description = new SimpleStringProperty(this, "description");
        this.priorite = new SimpleStringProperty(this, "priorite");
        this.datecreation = new SimpleObjectProperty<>(this, "datecreation");
        this.etat = new SimpleStringProperty(this, "etat");
        this.solution = new SimpleStringProperty(this, "solution");
        this.datecloture = new SimpleObjectProperty<>(this, "dateCloture");
    }
public StringProperty idProperty() {
    return id;
}

public String getId() {
    return id.get();
}

public void setId(String newId) {
    id.set(newId);
}

public StringProperty descriptionProperty() {
    return description;
}

public String getDescription() {
    return description.get();
}

public void setDescription(String newDescription) {
    description.set(newDescription);
}

public StringProperty prioriteProperty() {
    return priorite;
}

public String getPriorite() {
    return priorite.get();
}

public void setPriorite(String newPriorite) {
    priorite.set(newPriorite);
}

public StringProperty etatProperty() {
    return etat;
}

public String getEtat() {
    return etat.get();
}

public void setEtat(String newEtat) {
    etat.set(newEtat);
}

public StringProperty solutionProperty() {
    return solution;
}

public String getSolution() {
    return solution.get();
}

public void setSolution(String newSolution) {
    solution.set(newSolution);
}

    
  public SimpleObjectProperty<Date> datecreationProperty() {
    return datecreation;
}

public Date getDatecreation() {
    return datecreation.get();
}

public void setDatecreation(Date newDatecreation) {
    datecreation.set(newDatecreation);
}

public SimpleObjectProperty<Date> dateClotureProperty() {
    return datecloture;
}

public Date getDateCloture() {
    return datecloture.get();
}

public void setDateCloture(Date newDateCloture) {
    datecloture.set(newDateCloture);
}
  
public StringProperty responsableProperty() {
    return responsable;
}

public String getResponsable() {
    return responsable.get();
}

public void setResponsable(String newResponsable) {
    responsable.set(newResponsable);
}    
}
