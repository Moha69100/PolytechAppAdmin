package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

/**
 * Diplome generated by hbm2java
 */
public class Diplome  implements java.io.Serializable {


     private int id;
     private String typediplome;
     private Integer anneediplome;
     private String libelle;
     @JsonManagedReference("Etu_diplomes")
     private Set etudiants = new HashSet(0);

    public Diplome() {
    }

	
    public Diplome(int id) {
        this.id = id;
    }
    public Diplome(int id, String typediplome, Integer anneediplome, String libelle, Set etudiants) {
       this.id = id;
       this.typediplome = typediplome;
       this.anneediplome = anneediplome;
       this.libelle = libelle;
       this.etudiants = etudiants;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getTypediplome() {
        return this.typediplome;
    }
    
    public void setTypediplome(String typediplome) {
        this.typediplome = typediplome;
    }
    public Integer getAnneediplome() {
        return this.anneediplome;
    }
    
    public void setAnneediplome(Integer anneediplome) {
        this.anneediplome = anneediplome;
    }
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Set getEtudiants() {
        return this.etudiants;
    }
    
    public void setEtudiants(Set etudiants) {
        this.etudiants = etudiants;
    }




}


