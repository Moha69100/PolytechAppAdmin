package com.polytech.dao;
// Generated 22 sept. 2014 11:47:31 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Salle generated by hbm2java
 */
public class Salle  implements java.io.Serializable {


     private int id;
     private String libelle;
     private String localisation;
     private Integer capacite;
     private Set entreprisepresences = new HashSet(0);
     private Set evenementsalles = new HashSet(0);

    public Salle() {
    }

	
    public Salle(int id) {
        this.id = id;
    }
    public Salle(int id, String libelle, String localisation, Integer capacite, Set entreprisepresences, Set evenementsalles) {
       this.id = id;
       this.libelle = libelle;
       this.localisation = localisation;
       this.capacite = capacite;
       this.entreprisepresences = entreprisepresences;
       this.evenementsalles = evenementsalles;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getLocalisation() {
        return this.localisation;
    }
    
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    public Integer getCapacite() {
        return this.capacite;
    }
    
    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }
    public Set getEntreprisepresences() {
        return this.entreprisepresences;
    }
    
    public void setEntreprisepresences(Set entreprisepresences) {
        this.entreprisepresences = entreprisepresences;
    }
    public Set getEvenementsalles() {
        return this.evenementsalles;
    }
    
    public void setEvenementsalles(Set evenementsalles) {
        this.evenementsalles = evenementsalles;
    }




}


