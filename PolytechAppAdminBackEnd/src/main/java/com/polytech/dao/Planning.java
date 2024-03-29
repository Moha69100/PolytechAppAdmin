package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.polytech.dao.manager.PlanningManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Planning generated by hbm2java
 */
public class Planning implements java.io.Serializable {

    private final static Logger logger = Logger.getLogger(Planning.class.getSimpleName());

    private int id;
    private Evenement evenement;

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

    public Set<Entretien> getEntretiens() {
        return this.entretiens;
    }

    public void setEntretiens(Set<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public void generate() {
        if (this.deleteEntretiens()) {
            HashMap<Entreprisepresence, ArrayList<VoeuxEntreprise>> voeuxEntreprises;
            HashMap<Etudiantevenement, ArrayList<VoeuxEtudiant>> voeuxEtudiants;

            voeuxEntreprises = this.getVoeuxEntreprises();
            voeuxEtudiants = this.getVoeuxEtudiants();

        } else {
            logger.log(Level.ALL, "Impossible de supprimer les anciens entretiens");
        }
    }

    private HashMap<Entreprisepresence, ArrayList<VoeuxEntreprise>> getVoeuxEntreprises() {
        HashMap<Entreprisepresence, ArrayList<VoeuxEntreprise>> voeuxEntreprises = new HashMap<Entreprisepresence, ArrayList<VoeuxEntreprise>>();

        for (Entreprisepresence ent : this.evenement.getEntreprisepresences()) {
            ArrayList<VoeuxEntreprise> voeux = new ArrayList<VoeuxEntreprise>(ent.getVoeuxEntreprise());
            voeuxEntreprises.put(ent, voeux);
        }
        return voeuxEntreprises;
    }

    private HashMap<Etudiantevenement, ArrayList<VoeuxEtudiant>> getVoeuxEtudiants() {
        HashMap<Etudiantevenement, ArrayList<VoeuxEtudiant>> voeuxEtudiants = new HashMap<Etudiantevenement, ArrayList<VoeuxEtudiant>>();

        for (Etudiantevenement etu : this.evenement.getEtudiantpresents()) {
            ArrayList<VoeuxEtudiant> voeux = new ArrayList<VoeuxEtudiant>(etu.getVoeuxEtudiant());
            voeuxEtudiants.put(etu, voeux);
        }

        return voeuxEtudiants;
    }


    private Boolean deleteEntretiens() {
        PlanningManager manager = new PlanningManager();
        return manager.deleteEntretiens(this.id);
    }


}


