package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;

/**
 * Entretien generated by hbm2java
 */
public class Entretien implements java.io.Serializable {

    private int id;

    @JsonManagedReference("Ent_entretiens")
    private Entreprisepresence entreprisepresence;
    @JsonManagedReference("Etu_entretiens")
    private Etudiantevenement etudiantevenement;

    private Planning planning;
    private Salle salle;

    private Integer priorite;
    private Date horaire;
    private Date duree;

    public Entretien() {
    }

    public Entretien(int id) {
        this.id = id;
    }

    public Entretien(int id, Entreprisepresence entreprisepresence, Etudiantevenement etudiantevenement, Planning planning, Integer priorite, Date horaire, Salle salle, Date duree) {
        this.id = id;
        this.entreprisepresence = entreprisepresence;
        this.etudiantevenement = etudiantevenement;
        this.planning = planning;
        this.priorite = priorite;
        this.horaire = horaire;
        this.salle = salle;
        this.duree = duree;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entreprisepresence getEntreprisepresence() {
        return this.entreprisepresence;
    }

    public void setEntreprisepresence(Entreprisepresence entreprise) {
        this.entreprisepresence = entreprise;
    }

    public Etudiantevenement getEtudiantevenement() {
        return this.etudiantevenement;
    }

    public void setEtudiantevenement(Etudiantevenement etudiant) {
        this.etudiantevenement = etudiant;
    }

    public Planning getPlanning() {
        return this.planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public Integer getPriorite() {
        return this.priorite;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    public Date getHoraire() {
        return this.horaire;
    }

    public void setHoraire(Date horaire) {
        this.horaire = horaire;
    }

    public Salle getSalle() {
        return this.salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Date getDuree() {
        return this.duree;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

}
