package com.polytech.planification;

import com.polytech.dao.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;


import java.util.*;

/**
 * Created by Epulapp on 26/11/2014.
 */
public class SimpleGenerator extends PlanningGenerator {

    private HashMap<Integer, Entreprisepresence> mapEntreprisesPresentes;
    private HashMap<Integer, ArrayList<Entretien>> matrix;
    private ArrayList<VoeuxEntreprise> voeuxEntreprises;

    public SimpleGenerator(Planning planning) {
        super(planning);
    }

    public Set<Entretien> getEntretiens() {
        HashSet<Entretien> entretiensValides = new HashSet<Entretien>();
        for (ArrayList<Entretien> entretiens : matrix.values()) {
            Iterator<Entretien> it = entretiens.iterator();
            while (it.hasNext()) {
                Entretien entretien = it.next();
                if (entretien.getEtudiantevenement() != null) {
                    entretiensValides.add(entretien);
                }
            }
        }
        return entretiensValides; // cette collection peut constituer la liste des entretiens du planning
    }

    public void generate() {
        this.initializeSchedule(); // les entretiens possibles pour une entreprises sont créé mais n'ont pas d'étudiant

        ArrayList<Integer> sortedIds = new ArrayList<Integer>();
        HashMap<Integer, Etudiantevenement> etudiants = new HashMap<Integer, Etudiantevenement>();
        HashMap<Integer, LinkedList<VoeuxEntreprise>> sollicitationsEtudiant = new HashMap<Integer, LinkedList<VoeuxEntreprise>>();
        HashMap<Integer, LinkedList<Interval>> indisponibiliteEtudiant = new HashMap<Integer, LinkedList<Interval>>();

        this.initStructuresEtudiant(etudiants, sollicitationsEtudiant, indisponibiliteEtudiant);
        this.triEtudiants(sortedIds, sollicitationsEtudiant);

        this.createSchedule(sortedIds, etudiants, sollicitationsEtudiant, indisponibiliteEtudiant);
    }


    private void initializeSchedule() {
        this.matrix = new HashMap<Integer, ArrayList<Entretien>>();
        this.mapEntreprisesPresentes = new HashMap<Integer, Entreprisepresence>();

        Set<Entreprisepresence> entreprisesPresentes = this.planning.getEvenement().getEntreprisepresences();
        this.voeuxEntreprises = new ArrayList<VoeuxEntreprise>();
        for (Entreprisepresence entreprisePresente : entreprisesPresentes) {
            // on garde une trace des voeux de l'entreprise
            this.mapEntreprisesPresentes.put(entreprisePresente.getId(), entreprisePresente);
            this.voeuxEntreprises.addAll(entreprisePresente.getVoeuxEntreprise());

            DateTime debutEvenement = this.planning.getEvenement().getDateevt().plus(new Duration(this.planning.getEvenement().getHeuredebut()));

            Duration dureeEntretien = getDuree(entreprisePresente.getDureeentretien());
            Duration dureeEvenement = this.calculateDureeEvenementEnMinutes();
            int nbEntretiens = (int)(dureeEvenement.getStandardMinutes() / dureeEntretien.getStandardMinutes());

            ArrayList<Entretien> entretiens = new ArrayList<Entretien>(nbEntretiens);

            // initialise tous les entretiens potentiels, seul information manquante, l'étudiant
            // on detectera les entretiens qui ont un étudiant affecté pour tester sa validité
            for (int i = 0 ; i < nbEntretiens ; i++) {
                Entretien ent = new Entretien();

                DateTime debutEntretien = new DateTime(debutEvenement).plus(i*dureeEntretien.getMillis());
                ent.setEntreprisepresence(entreprisePresente);
                ent.setEvenement(this.planning.getEvenement());
                ent.setDuree(new DateTime(0).plus(dureeEntretien));
                ent.setHoraire(debutEntretien);
                ent.setSalle(ent.getSalle());

                entretiens.set(i, ent);
            }
            this.matrix.put(entreprisePresente.getId(), entretiens);
        }
    }

    protected Duration getDuree(DateTime dateInstance) {
        Duration duration = new Duration(dateInstance);
        return duration;
    }

    protected Duration calculateDureeEvenementEnMinutes() {
        DateTime heureDebut, heureFin;
        Duration duration;

        heureDebut = this.planning.getEvenement().getHeuredebut();
        heureFin = this.planning.getEvenement().getHeurefin();
        duration = new Duration(heureDebut, heureFin);

        return duration;
    }

    private void initStructuresEtudiant(
            HashMap<Integer, Etudiantevenement> etudiants,
            HashMap<Integer, LinkedList<VoeuxEntreprise>> sollicitationsEtudiant,
            HashMap<Integer, LinkedList<Interval>> indisponibiliteEtudiant)
    {
        for (VoeuxEntreprise voeux : this.voeuxEntreprises) {
            Etudiantevenement etudiantPresent = voeux.getEtudiantevenement();
            int idEtudiant = etudiantPresent.getEtuid().getId();

            // on référence l'étudiant si on le rencontre pour la première fois dans toutes les structures
            if (!etudiants.containsKey(etudiantPresent.getEtuid().getId())) {
                etudiants.put(idEtudiant, etudiantPresent);
                sollicitationsEtudiant.put(idEtudiant, new LinkedList<VoeuxEntreprise>());
                indisponibiliteEtudiant.put(idEtudiant, new LinkedList<Interval>());
            }
            sollicitationsEtudiant.get(idEtudiant).add(voeux);
        }
    }

    private void triEtudiants(ArrayList<Integer> sortedIds, HashMap<Integer, LinkedList<VoeuxEntreprise>> sollicitations) {
        int currentMax = 0;

        HashMap<Integer, LinkedList<Integer>> sorter = new HashMap<Integer, LinkedList<Integer>>();
        Iterator<Integer> it = sollicitations.keySet().iterator(); // on itère sur les clés pour garder l'information de l'id etudiant

        LinkedList<VoeuxEntreprise> voeuxConcernantEtudiant;
        int idEtudiant, nbVoeux;
        while (it.hasNext()) {
            idEtudiant = it.next();
            voeuxConcernantEtudiant = sollicitations.get(idEtudiant);
            nbVoeux = voeuxConcernantEtudiant.size();
            if (currentMax < nbVoeux) currentMax = nbVoeux;
            if (!sorter.containsKey(nbVoeux)) {
                sorter.put(nbVoeux, new LinkedList<Integer>()); // une strate par nombre différent de voeux est créée
            }
            sorter.get(nbVoeux).add(idEtudiant);
        }

        for (nbVoeux = currentMax ; nbVoeux > 0 ; nbVoeux--) {
            LinkedList<Integer> listeEtudiants;
            if (sorter.containsKey(nbVoeux)) {
                listeEtudiants = sorter.get(nbVoeux);
                for (int id : listeEtudiants) {
                    sortedIds.add(id);
                }
            }
        }
    }

    protected void createSchedule( // traite les etudiants qui ont été l'objet du plus de voeux entreprise en premier
            ArrayList<Integer> etudiants,
            HashMap<Integer, Etudiantevenement> mapEtudiants,
            HashMap<Integer,LinkedList<VoeuxEntreprise>> sollicitations,
            HashMap<Integer, LinkedList<Interval>> indisponibilites)
    {
        int idEtudiant;
        LinkedList<VoeuxEntreprise> voeux;
        Entreprisepresence entreprise;
        ArrayList<Entretien> entretiens;
        Entretien entretien;

        Iterator<Integer> itEtudiants = etudiants.iterator();
        while(itEtudiants.hasNext()) {
            idEtudiant = itEtudiants.next();
            voeux = sollicitations.get(idEtudiant);

            for (VoeuxEntreprise unVoeux : voeux) {
                entreprise = this.mapEntreprisesPresentes.get(unVoeux.getEntreprisepresence().getId()); // réutiliser les instances déjà rencontrées
                entretiens = matrix.get(entreprise);
                for (int i = 0 ; i < entretiens.size() ; i++) {
                    entretien = entretiens.get(i);
                    if (entretien.getEtudiantevenement() == null) {
                        if (estDisponible(indisponibilites.get(idEtudiant), entretien)) {
                            entretien.setEtudiantevenement(mapEtudiants.get(idEtudiant));
                            indisponibilites.get(idEtudiant).add(new Interval(entretien.getHoraire(), new Duration(entretien.getDuree())));
                            break;
                        }
                    }
                } // si aucun creneau n'est disponible, le voeux ne sera pas satisfait
                // les voeux des étudiants sont completement ignorés
            }

        }
    }

    private boolean estDisponible (LinkedList<Interval> indisponibilites, Entretien entretien) {
        DateTime debutEntretien = entretien.getHoraire();

        Iterator<Interval> it = indisponibilites.iterator();
        while (it.hasNext()) {
            Interval indisponibilite = it.next();
            if (indisponibilite.contains(debutEntretien)) {
                return false;
            }
        }
        return true;
    }
}
