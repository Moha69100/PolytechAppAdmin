package com.polytech.planification;

import com.polytech.dao.Entreprisepresence;
import com.polytech.dao.Entretien;
import com.polytech.dao.Planning;



import java.util.*;

/**
 * Created by Epulapp on 26/11/2014.
 */
public class SimpleGenerator extends PlanningGenerator {

    private HashMap<Entreprisepresence, ArrayList<Entretien>> matrix;

    public SimpleGenerator(Planning planning) {
        super(planning);
        this.init();
    }

    public Set<Entretien> getEntretiens() {

        return null;
    }

    protected void init() {
        Set<Entreprisepresence> entreprisesPresentes = this.planning.getEvenement().getEntreprisepresences();
        for (Entreprisepresence entreprisePresente : entreprisesPresentes) {
            Calendar c = new GregorianCalendar();

            int dureeEntretienEnMinutes = this.calculeDureeEntretienEnMinutes(entreprisePresente.getDureeentretien());
            int dureeEvenementEnMinutes = this.calculateDureeEvenementEnMinutes();
            int nbEntretiens = dureeEvenementEnMinutes / dureeEntretienEnMinutes;

            ArrayList<Entretien> entretiens = new ArrayList<Entretien>(nbEntretiens);
            for (int i = 0 ; i < nbEntretiens ; i++) {
                Entretien ent = new Entretien();
                ent.setEntreprisepresence(entreprisePresente);
                ent.setEvenement(this.planning.getEvenement());

                // config l'heure de l'entretien
            }

            this.matrix.put(entreprisePresente, new ArrayList<Entretien>(nbEntretiens));
        }
    }

    protected int calculeDureeEntretienEnMinutes(Date dateInstance) {
        long time = dateInstance.getTime();
        return (int)(time / 1000 / 60);
    }

    protected int calculateDureeEvenementEnMinutes() {
        Date heureDebut, heureFin;
        long timeDebut, timeFin, dureeMillisecondes;
        int dureeMinutes;

        heureDebut = this.planning.getEvenement().getHeuredebut();
        heureFin = this.planning.getEvenement().getHeurefin();

        timeDebut = heureDebut.getTime();
        timeFin = heureFin.getTime();
        dureeMillisecondes = timeFin - timeDebut;

        dureeMinutes = (int) (dureeMillisecondes / 1000 / 60);

        return dureeMinutes;
    }

    public void generate() {

    }
}
