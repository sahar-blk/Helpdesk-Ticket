/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author molka
 */
public class Groupe {
    private String id;
    private String specialite;
   private Map<String, Technicien> techniciens;

    public Groupe() {
     this.techniciens = new HashMap<>();
    }

   
    public Groupe(String id, String specialite, Map<Integer, Technicien> techniciens) {
        this.id = id;
        this.specialite = specialite;
        this.techniciens = new HashMap<>();
    }
  public void ajouterTechnicien(Technicien technicien) {
        techniciens.put(technicien.getId(), technicien);
    }

    public void supprimerTechnicien(int idTechnicien) {
        techniciens.remove(idTechnicien);
    }

    // Mettre à jour les détails d'un technicien dans la map
    public void mettreAJourTechnicien(String idTechnicien, Technicien nouveauTechnicien) {
        if (techniciens.containsKey(idTechnicien)) {
            techniciens.put(idTechnicien, nouveauTechnicien);
        } else {
            System.out.println("Le technicien avec l'ID " + idTechnicien + " n'existe pas.");
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Map<String, Technicien> getTechniciens() {
        return techniciens;
    }

    public void setTechniciens(Map<String, Technicien> techniciens) {
        this.techniciens = techniciens;
    }

    
     

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Groupe{");
        sb.append("id=").append(id);
        sb.append(", specialite=").append(specialite);
        sb.append(", techniciens=").append(techniciens);
        sb.append('}');
        return sb.toString();
    }
    
 
}
