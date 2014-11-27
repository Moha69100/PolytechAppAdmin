package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Etudiant generated by hbm2java
 */
public class Etudiantevenement implements java.io.Serializable {

    private int id;
    private Etudiant etudiant;

    @JsonIgnore
    private Evenement evenement;

    private Set voeuxEtudiant;

    public Etudiantevenement() {
    }

    public Etudiantevenement(int id, Etudiant etuid, Evenement evtid, Set voeuxEtudiant) {
        this.id = id;
        this.etudiant = etuid;
        this.evenement = evtid;
        this.voeuxEtudiant = voeuxEtudiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etuid) {
        this.etudiant = etuid;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evtid) {
        this.evenement = evtid;
    }

    public Set<VoeuxEtudiant> getVoeuxEtudiant() {
        return voeuxEtudiant;
    }

    public void setVoeuxEtudiant(Set<VoeuxEtudiant> voeuxEtudiant) {
        this.voeuxEtudiant = voeuxEtudiant;
    }
}
