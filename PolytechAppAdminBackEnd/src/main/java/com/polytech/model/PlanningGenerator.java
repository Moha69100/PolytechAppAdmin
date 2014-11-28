package com.polytech.model;

import com.polytech.dao.*;
import com.polytech.dao.manager.EntretienManager;
import com.polytech.dao.manager.PlanningManager;
import com.polytech.dao.manager.SalleManager;

import java.util.*;
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
        HashMap<Entreprisepresence, ArrayList<Etudiantevenement>> voeuxEntreprises = new HashMap();
        HashMap<Etudiantevenement, LinkedList<Entreprisepresence>> voeuxEtudiants = new HashMap();

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
        
        Set<Entreprisepresence> listEntpres = evt.getEntreprisepresences();
        for (Entreprisepresence entpres : listEntpres) {
            //Récupérations des voeux entreprises pour un évènement donné
            Set<VoeuxEntreprise> dbVoeuxEntreprises = entpres.getVoeuxEntreprise();
            for (VoeuxEntreprise voeuxEntreprise : dbVoeuxEntreprises) {
                ArrayList<Etudiantevenement> etudiants;
                if (!voeuxEntreprises.containsKey(voeuxEntreprise.getEntreprisepresence())) {
                    etudiants = new ArrayList<Etudiantevenement>();
                    voeuxEntreprises.put(voeuxEntreprise.getEntreprisepresence(), etudiants);
                } else {
                    etudiants = voeuxEntreprises.get(voeuxEntreprise.getEntreprisepresence());
                }
                etudiants.add(voeuxEntreprise.getEtudiantevenement());
            }
        }
        
        Set<Etudiantevenement> listEtuevt = evt.getEtudiantpresents();
        for (Etudiantevenement etuevt : listEtuevt) {
            //Récupération des voeux étudiants pour un évènement donné
            Set<VoeuxEtudiant> dbVoeuxEtudiant = etuevt.getVoeuxEtudiant();

            for (VoeuxEtudiant voeuxEtudiant : dbVoeuxEtudiant) {
                LinkedList<Entreprisepresence> entreprises;
                if (!voeuxEtudiants.containsKey(voeuxEtudiant.getEtudiantevenement())) {
                    entreprises = new LinkedList<Entreprisepresence>();
                    voeuxEtudiants.put(voeuxEtudiant.getEtudiantevenement(), entreprises);
                } else {
                    entreprises = voeuxEtudiants.get(voeuxEtudiant.getEtudiantevenement());
                }
                // TODO trier les voeux
                entreprises.add(voeuxEtudiant.getEntreprisepresence());
            }
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
            for (Entreprisepresence entreprise : voeuxEntreprises.keySet()) {
                ArrayList<Etudiantevenement> listEtu = voeuxEntreprises.get(entreprise);
                // si il reste des voeux entretprise
                if (!listEtu.isEmpty()) {
                    // récupération du premier étudiant disponible
                    Etudiantevenement etu = getEtudiantDispo(listEtu, listEntretiens);
                    if (etu != null) {
                        Entretien entretien = createEntretien(entreprise, listEtu.remove(listEtu.indexOf(etu)), calCur.getTime());
                        listEntretiens.add(entretien);
                        entretiens.add(entretien);
                    }
                }
            }
            // Ajout des voeux etudiant
            for (Etudiantevenement etu : voeuxEtudiants.keySet()) {
                LinkedList<Entreprisepresence> listEntreprises = voeuxEtudiants.get(etu);
                // si il reste des voeux des étudiants
                if (!listEntreprises.isEmpty()) {
                    //récupération de la première entreprise dispo
                    Entreprisepresence entr = getEntrepriseDispo(listEntreprises, listEntretiens);
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
    private boolean isEtudiantDispo(Etudiantevenement etu, ArrayList<Entretien> entretiens) {
        for (Entretien entretien : entretiens) {
            if (entretien.getEtudiantevenement() == etu) {
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
    private boolean isEntrepriseDispo(Entreprisepresence entr, ArrayList<Entretien> entretiens) {
        for (Entretien entretien : entretiens) {
            if (entretien.getEntreprisepresence() == entr) {
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
    private Etudiantevenement getEtudiantDispo(ArrayList<Etudiantevenement> listEtu, ArrayList<Entretien> entretiens) {
        for (Etudiantevenement etu : listEtu) {
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
    private Entreprisepresence getEntrepriseDispo(LinkedList<Entreprisepresence> listEntreprises, ArrayList<Entretien> entretiens) {
        for (Entreprisepresence entr : listEntreprises) {
            if (isEntrepriseDispo(entr, entretiens)) {
                return entr;
            }
        }

        return null;
    }

    private Entretien createEntretien(Entreprisepresence ent, Etudiantevenement etu, Date time) {
        Entretien entretien = new Entretien();
        //Date dateDureeEntretien = new Date();
        //dateDureeEntretien.setMinutes(dureeEntretiens);
        entretien.setEntreprisepresence(ent);
        entretien.setEtudiantevenement(etu);
        entretien.setHoraire(time);
        entretien.setPlanning(planning);
        entretien.setDuree(ent.getDureeentretien());
        entretien.setSalle(ent.getSalle());
//        try {
//            entretien.setSalle(salleManager.getSalleById(0));
//        } catch (Exception ex) {
//            Logger.getLogger(PlanningGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return entretien;
    }

}
