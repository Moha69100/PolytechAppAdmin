package com.polytech.model;

import com.polytech.dao.*;
import com.polytech.dao.manager.EntretienManager;
import com.polytech.dao.manager.EvenementManager;
import com.polytech.dao.manager.PlanningManager;
import com.polytech.dao.manager.VoeuxEntrepriseManager;
import com.polytech.dao.manager.VoeuxEtudiantManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Epulapp
 *
 * dureeEntretiens : durée des entretiens en minutes
 */
public class PlanningGenerator {

    private PlanningManager planningManager = new PlanningManager();
    private EntretienManager entretienManager = new EntretienManager();
    private VoeuxEntrepriseManager voeuxEntrepriseManager = new VoeuxEntrepriseManager();
    private VoeuxEtudiantManager voeuxEtudiantManager = new VoeuxEtudiantManager();

    private Evenement evt;
    private Planning planning;

    int dureeEntretiens;

    public PlanningGenerator(Evenement evt, int dureeEntretiens) {
        this.evt = evt;
        this.dureeEntretiens = dureeEntretiens;
        this.planning = new Planning(); // TODO création d'un planning
        this.planning.setEvenement(evt);
        this.planning.setEntretiens(new HashSet<Entretien>());
    }

    public void generate() throws Exception {

        //
        // Structures de données
        //
        // recupération des etudiants / entreprises
//        ArrayList<Entreprise> listEntreprises;
//        ArrayList<Etudiant> listEtudiants;
        HashMap<Entreprise, ArrayList<Etudiant>> voeuxEntreprises = new HashMap();
        HashMap<Etudiant, LinkedList<Entreprise>> voeuxEtudiants = new HashMap();

        // Entretiens
        HashMap<Calendar, ArrayList<Entretien>> entretiensByCal = new HashMap();
//        HashMap<Calendar, ArrayList<Entreprise>> entrepriseByCal = new HashMap();
//        HashMap<Calendar, ArrayList<Etudiant>> etudiantByCal = new HashMap();

        //Récupération des entretiens du planning
        Set<Entretien> entretiens = this.planning.getEntretiens();
        

        //
        // recuperation des voeux
        //
        //Récupérations des voeux entreprises pour un évènement donné
        List<VoeuxEntreprise> dbVoeuxEntreprises = voeuxEntrepriseManager.getAllVoeuxEntrepriseByIDEvEnement(evt.getId());
        //Récupération des voeux étudiants pour un évènement donné
        List<VoeuxEtudiant> dbVoeuxEtudiant = voeuxEtudiantManager.getAllVoeuxEtudiantByIDEvEnement(evt.getId());


        for (VoeuxEntreprise voeuxEntreprise : dbVoeuxEntreprises) {
            ArrayList<Etudiant> etudiants;
            if (!voeuxEntreprises.containsKey(voeuxEntreprise.getEntreprise())) {
                etudiants = voeuxEntreprises.put(voeuxEntreprise.getEntreprise(), new ArrayList<Etudiant>());
            } else {
                etudiants = voeuxEntreprises.get(voeuxEntreprise.getEntreprise());
            }
            etudiants.add(voeuxEntreprise.getEtudiant());
        }

        for (VoeuxEtudiant voeuxEtudiant : dbVoeuxEtudiant) {
            LinkedList<Entreprise> entreprises;
            if (!voeuxEtudiants.containsKey(voeuxEtudiant.getEtudiant())) {
                entreprises = voeuxEtudiants.put(voeuxEtudiant.getEtudiant(), new LinkedList<Entreprise>());
            } else {
                entreprises = voeuxEtudiants.get(voeuxEtudiant.getEtudiant());
            }
            // TODO trier les voeux
            entreprises.add(voeuxEtudiant.getEntreprise());
        }

        // Suppression des voeux etudiants qui ne sont pas dans les voeux entreprise
        for (Map.Entry<Etudiant, LinkedList<Entreprise>> voeuxEtudiant : voeuxEtudiants.entrySet()) {
            for (Entreprise ent : voeuxEtudiant.getValue()) {
                if (!voeuxEntreprises.get(ent).contains(voeuxEtudiant.getKey())) {
                    voeuxEtudiant.getValue().remove(voeuxEtudiant.getValue().indexOf(ent));
                }
            }
        }

        // Detection des voeux Entreprise non dans les voeux Etudiants
        // TODO
        //
        // Generation des dates
        //
        // Date et heure de début des entretients
        Calendar calDeb = Calendar.getInstance();
        calDeb.setTime(evt.getDateevt());
        calDeb.set(Calendar.HOUR_OF_DAY, evt.getHeuredebut().getHours());
        calDeb.set(Calendar.MINUTE, evt.getHeuredebut().getMinutes());

        // Date et heure de Fin des entretients
        Calendar calEnd = (Calendar) calDeb.clone();
        calEnd.set(Calendar.MINUTE, evt.getHeurefin().getMinutes());

        // Date limite pour commencer le dernier entretien
        Calendar calLast = (Calendar) calEnd.clone();
        calLast.add(Calendar.MINUTE, -dureeEntretiens);

        entretiensByCal.put(calDeb, new ArrayList<Entretien>());
        for (Calendar calCur = (Calendar) calDeb.clone(); calCur.before(calLast); calCur.add(Calendar.MINUTE, dureeEntretiens)) {
            entretiensByCal.put(calCur, new ArrayList<Entretien>());
        }

        //
        // Creation du planning best-first
        // Pour chaque voeux (dans l'ordre de préférence), ajout dans le premier crenaux horaire disponible
        //
        // pour chaque crenaux d'entretiens
        for (Calendar calCur : entretiensByCal.keySet()) { // AMELIORATION : utilisation d'iterateurs
            ArrayList<Entretien> listEntretiens = entretiensByCal.get(calCur);
            // Ajout des voeux entreprise
            for (Entreprise entreprise : voeuxEntreprises.keySet()) {
                ArrayList<Etudiant> listEtu = voeuxEntreprises.get(entreprise);
                // si il reste des voeux entretprise
                if (!listEtu.isEmpty()) {
                    // récupération du premier étudiant disponible
                    Etudiant etu = getEtudiantDispo(listEtu, listEntretiens);
                    if (etu != null) {
                        Entretien entretien = createEntretien(entreprise, listEtu.remove(listEtu.indexOf(etu)), calCur.getTime());
                        listEntretiens.add(entretien);
                        entretiens.add(entretien);
                    }
                }
            }
            // Ajout des voeux etudiant
            for (Etudiant etu : voeuxEtudiants.keySet()) {
                LinkedList<Entreprise> listEntreprises = voeuxEtudiants.get(etu);

            }
        }

        //
        // Ajout du planning dans la base
        //
        try {
            planningManager.addPlanning(planning);
        } catch (Exception ex) {
            Logger.getLogger(PlanningGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Utilitaire pour la génération du planning
     *
     * @param etu
     * @param entretiens
     * @return true si l'étudiant a un entretien
     */
    private boolean isEtudiantDispo(Etudiant etu, ArrayList<Entretien> entretiens) {
        for (Entretien entretien : entretiens) {
            if (entretien.getEtudiant() == etu) {
                return false;
            }
        }
        return true;
    }//isEtudiantDispo()

    /**
     * Utilitaire pour la génération du planning
     *
     * @param listEtu
     * @param entretiens
     * @return un etudiant qui n'a pas d'entretien, ou null
     */
    private Etudiant getEtudiantDispo(ArrayList<Etudiant> listEtu, ArrayList<Entretien> entretiens) {
        for (Etudiant etu : listEtu) {
            if (isEtudiantDispo(etu, entretiens)) {
                return etu;
            }
        }
        return null;
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
