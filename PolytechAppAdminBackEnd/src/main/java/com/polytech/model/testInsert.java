/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.model;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Entretien;
import com.polytech.dao.Etudiant;
import com.polytech.dao.Evenement;
import com.polytech.dao.Planning;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.dao.manager.EntretienManager;
import com.polytech.dao.manager.PlanningManager;
import com.polytech.dao.manager.SalleManager;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Epulapp
 */
public class testInsert {


   

    private final PlanningManager planningManager = new PlanningManager();
    private final EntretienManager entretienManager = new EntretienManager();
    private final EntrepriseManager entrepriseManager = new EntrepriseManager();
    private final SalleManager salleManager = new SalleManager();

    private Evenement evt;
    private Planning planning;

    public boolean generate() throws Exception {
      try {
          Entreprise e = entrepriseManager.getEnterpriseById(1);
          System.out.println("id " + e.getId()+"Adresse : " + e.getAdresse());
      }catch (Exception e){
          e.printStackTrace();
      }
        
        //createEntretien
        return true;

    }

    private Entretien createEntretien(Entreprise ent, Etudiant etu, Date time) {
        Entretien entretien = new Entretien();
        entretien.setEntreprise(ent);
        entretien.setEtudiant(etu);
        entretien.setHoraire(time);
        entretien.setPlanning(planning);
        //entretien.setSalleid();
        //entretien.setDuree(dureeEntretiens);
        return entretien;
    }

}
