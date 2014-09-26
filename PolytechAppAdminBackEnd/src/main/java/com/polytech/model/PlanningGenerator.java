package com.polytech.model;

import com.polytech.dao.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * @author Epulapp
 */
public class PlanningGenerator {
    
    private Evenement evt;
    private Planning planning;
    
    public PlanningGenerator(Evenement evt) {
        this.evt = evt;
        this.planning = new Planning(); // TODO création d'un planning
    }
    
    public void generate() {
        // recupération des etudiants / entreprises
//        ArrayList<Entreprise> listEntreprises;
//        ArrayList<Etudiant> listEtudiants;
        
        //
        // Structures de données
        //
        
        // recuperation des voeux
        HashMap<Entreprise, ArrayList<Etudiant>> voeuxEntreprises;
        HashMap<Etudiant, LinkedList<Entreprise>> voeuxEtudiants;
        
        // entretiens
        
        
        // 
        // Algo
        //
        
        // Suppression des voeux etudiants qui ne sont pas dans les voeux entreprise
        for (Map.Entry<Etudiant, LinkedList<Entreprise>> voeuxEtudiant : voeuxEtudiants.entrySet()) {
            for (Entreprise ent : voeuxEtudiant.getValue()) {
                if (! voeuxEntreprises.get(ent).contains(voeuxEtudiant.getKey())) {
                    voeuxEtudiant.getValue().remove(voeuxEtudiant.getValue().indexOf(ent));
                }
            }
        }
        
        // Gestion des horaires
        Calendar calDeb = Calendar.getInstance();
        calDeb.setTime(evt.getDateevt());
        calDeb.set(Calendar.HOUR_OF_DAY, evt.getHeuredebut());
        evt.getHeuredebut();
        evt.getHeurefin();
        
        // Creation du planning best-first
        
    }//generate()
    
}
