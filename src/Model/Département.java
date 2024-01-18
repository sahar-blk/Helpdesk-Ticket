/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedHashSet;
import java.util.Optional;

/**
 *
 * @author molka
 */
public class Département {
    private String nom;
    private LinkedHashSet<Groupe> groupes;

    public Département() {
    }

    public Département(String nom, LinkedHashSet<Groupe> groupes) {
        this.nom = nom;
        this.groupes =  this.groupes = new LinkedHashSet<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LinkedHashSet<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(LinkedHashSet<Groupe> groupes) {
        this.groupes = groupes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("D\u00e9partement{");
        sb.append("nom=").append(nom);
        sb.append(", groupes=").append(groupes);
        sb.append('}');
        return sb.toString();
    }
    
  public void ajouterGroupe(Groupe groupe) {
        groupes.add(groupe);
    }

    public void supprimerGroupe(Groupe groupe) {
        groupes.remove(groupe);
    }

    public void modifierGroupe(Groupe ancienGroupe, Groupe nouveauGroupe) {
        if (groupes.contains(ancienGroupe)) {
            groupes.remove(ancienGroupe);
            groupes.add(nouveauGroupe);
        }
    }

    public Optional<Groupe> chercherGroupe(String nomGroupe) {
        return groupes.stream().filter(groupe -> groupe.getSpecialite().equals(nomGroupe)).findFirst();
    }  

}
