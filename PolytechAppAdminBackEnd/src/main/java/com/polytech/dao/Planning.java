package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Planning generated by hbm2java
 */
public class Planning  implements java.io.Serializable {


     private int id;
     private Evenement evenement;
      @JsonBackReference
     private Set entretiens = new HashSet(0);

    public Planning() {
    }

	
    public Planning(int id) {
        this.id = id;
    }
    public Planning(int id, Evenement evenement, Set entretiens) {
       this.id = id;
       this.evenement = evenement;
       this.entretiens = entretiens;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Evenement getEvenement() {
        return this.evenement;
    }
    
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public Set getEntretiens() {
        return this.entretiens;
    }
    
    public void setEntretiens(Set entretiens) {
        this.entretiens = entretiens;
    }




}


