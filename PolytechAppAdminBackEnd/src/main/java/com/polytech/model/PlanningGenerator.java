package com.polytech.model;

import com.polytech.dao.*;
import com.polytech.dao.manager.EntretienManager;
import com.polytech.dao.manager.PlanningManager;
import com.polytech.dao.manager.SalleManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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

    private final PlanningManager planningManager = new PlanningManager();
    private final EntretienManager entretienManager = new EntretienManager();
    private final SalleManager salleManager = new SalleManager();

    private Evenement evt;
    private Planning planning;

    int dureeEntretiens;

    public PlanningGenerator(Evenement evt, int dureeEntretiens) {
        this.evt = evt;
        this.dureeEntretiens = dureeEntretiens;

//        this.planning = new Planning(); // TODO création d'un planning
//        this.planning.setEvenement(evt);
        try {
            this.planning = (Planning) planningManager.getPlanningByEvt(evt.getId()).get(0);
        } catch (Exception ex) {
            Logger.getLogger(PlanningGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.planning.setEntretiens(new HashSet<Entretien>());
    }

    public boolean generate() throws Exception {

        //
        // Structures de données
        //
        // recupération des etudiants / entreprises
//        ArrayList<Entreprise> listEntreprises;
//        ArrayList<Etudiant> listEtudiants;
        HashMap<Entreprise, ArrayList<Etudiant>> voeuxEntreprises = new HashMap();
        HashMap<Etudiant, LinkedList<Entreprise>> voeuxEtudiants = new HashMap();

        // Entretiens
        LinkedHashMap<Calendar, ArrayList<Entretien>> entretiensByCal = new LinkedHashMap();
//        HashMap<Calendar, ArrayList<Entreprise>> entrepriseByCal = new HashMap();
//        HashMap<Calendar, ArrayList<Etudiant>> etudiantByCal = new HashMap();

        //Récupération de la reference des entretiens du planning (vide, pour la remplir)
        Set<Entretien> entretiens = this.planning.getEntretiens();

        // Suppression des anciens entretiens
        for (Entretien entretien : entretiens) {
            entretienManager.deleteEntretienById(entretien.getId());
        }
        entretiens = new HashSet<Entretien>();

        //Solution de contournement pour la suppression des entretiens du planning courant
        List<Entretien> entretiensToDelete = entretienManager.getEntretiensByEvent(this.planning.getId());
        // Suppression des anciens entretiens
        for (Entretien entretienSupp : entretiensToDelete) {
            entretienManager.deleteEntretienById(entretienSupp.getId());
        }

        //
        // recuperation des voeux
        //
        //Récupérations des voeux entreprises pour un évènement donné
        Set<VoeuxEntreprise> dbVoeuxEntreprises = evt.getVoeuxEntreprises();
        //Récupération des voeux étudiants pour un évènement donné
        Set<VoeuxEtudiant> dbVoeuxEtudiant = evt.getVoeuxEtudiants();

        for (VoeuxEntreprise voeuxEntreprise : dbVoeuxEntreprises) {
            ArrayList<Etudiant> etudiants;
            if (!voeuxEntreprises.containsKey(voeuxEntreprise.getEntreprise())) {
                etudiants = new ArrayList<Etudiant>();
                voeuxEntreprises.put(voeuxEntreprise.getEntreprise(), etudiants);
            } else {
                etudiants = voeuxEntreprises.get(voeuxEntreprise.getEntreprise());
            }
            etudiants.add(voeuxEntreprise.getEtudiant());
        }

        for (VoeuxEtudiant voeuxEtudiant : dbVoeuxEtudiant) {
            LinkedList<Entreprise> entreprises;
            if (!voeuxEtudiants.containsKey(voeuxEtudiant.getEtudiant())) {
                entreprises = new LinkedList<Entreprise>();
                voeuxEtudiants.put(voeuxEtudiant.getEtudiant(), entreprises);
            } else {
                entreprises = voeuxEtudiants.get(voeuxEtudiant.getEtudiant());
            }
            // TODO trier les voeux
            entreprises.add(voeuxEtudiant.getEntreprise());
        }

        //
        // Generation des dates
        //
        // Date et heure de début des entretients
        Calendar calDeb = Calendar.getInstance();
        calDeb.setTime(evt.getDateevt());
        calDeb.set(Calendar.HOUR_OF_DAY, evt.getHeuredebut().getHours());
        calDeb.set(Calendar.MINUTE, evt.getHeuredebut().getMinutes());
//        System.out.println(calDeb.toString());

        // Date et heure de Fin des entretients
        Calendar calEnd = (Calendar) calDeb.clone();
        calEnd.set(Calendar.HOUR_OF_DAY, evt.getHeurefin().getHours());
        calEnd.set(Calendar.MINUTE, evt.getHeurefin().getMinutes());
//        System.out.println(calEnd.toString());

        // Date limite pour commencer le dernier entretien
        Calendar calLast = (Calendar) calEnd.clone();
        calLast.add(Calendar.MINUTE, -dureeEntretiens);
//        System.out.println(calLast.toString());

        entretiensByCal.put(calDeb, new ArrayList<Entretien>());
        for (Calendar calCur = (Calendar) calDeb.clone(); calCur.before(calLast); calCur.add(Calendar.MINUTE, dureeEntretiens)) {
            entretiensByCal.put((Calendar) calCur.clone(), new ArrayList<Entretien>());
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
                // si il reste des voeux des étudiants
                if (!listEntreprises.isEmpty()) {
                    //récupération de la première entreprise dispo
                    Entreprise entr = getEntrepriseDispo(listEntreprises, listEntretiens);
                    if (entr != null) {
                        Entretien entretien = createEntretien(listEntreprises.remove(listEntreprises.indexOf(entr)), etu, calCur.getTime());
                        listEntretiens.add(entretien);
                        entretiens.add(entretien);
                    }
                }

            }
        }

        //
        // Ajout du planning dans la base
        //
        try {
//            planningManager.addPlanning(planning);
            planningManager.updatePlanning(planning);
            for (Entretien entretien : entretiens) {
                entretienManager.addEntretien(entretien);
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanningGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
     * Détermine si une entreprise est disponible pour un entretien dans la
     * liste donnée
     *
     * @param entr l'entreprise
     * @param entretiens la liste d'entretiens que l'on souhaite vérifier
     * @return TRUE si l'entreprise est disponible, FALSE sinon
     */
    private boolean isEntrepriseDispo(Entreprise entr, ArrayList<Entretien> entretiens) {
        for (Entretien entretien : entretiens) {
            if (entretien.getEntreprise() == entr) {
                return false;
            }
        }
        return true;
    }

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

    /**
     * Retourne la première entreprise disponible en fonction de la liste des
     * entretiens planifiés
     *
     * @param listEntreprises liste des entreprises à considérer
     * @param entretiens les entretiens déjà planifiés
     * @return une entreprise ou NULL
     */
    private Entreprise getEntrepriseDispo(LinkedList<Entreprise> listEntreprises, ArrayList<Entretien> entretiens) {
        for (Entreprise entr : listEntreprises) {
            if (isEntrepriseDispo(entr, entretiens)) {
                return entr;
            }
        }

        return null;
    }

    private Entretien createEntretien(Entreprise ent, Etudiant etu, Date time) {
        Entretien entretien = new Entretien();
        Date dateDureeEntretien = new Date();
        dateDureeEntretien.setMinutes(dureeEntretiens);
        entretien.setEntreprise(ent);
        entretien.setEtudiant(etu);
        entretien.setHoraire(time);
        entretien.setPlanning(planning);
        entretien.setDuree(dateDureeEntretien);
        try {
            entretien.setSalle(salleManager.getSalleById(0));
        } catch (Exception ex) {
            Logger.getLogger(PlanningGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entretien;
    }

}
