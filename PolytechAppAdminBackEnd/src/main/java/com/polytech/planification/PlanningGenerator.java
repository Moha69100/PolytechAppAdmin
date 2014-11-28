package com.polytech.planification;

import com.polytech.dao.Entretien;
import com.polytech.dao.Planning;

import java.util.Set;

/**
 * Created by Epulapp on 26/11/2014.
 */
public abstract class PlanningGenerator {
    // garantie la récupération d'une liste d'entretiens pour le planning retenu
    // cette liste d'entretiens doit être cohérente avec les paramètres du planning

    protected Planning planning;

    public abstract Set<Entretien> getEntretiens();

    public PlanningGenerator(Planning planning) {
        this.planning = planning;
    }
}
