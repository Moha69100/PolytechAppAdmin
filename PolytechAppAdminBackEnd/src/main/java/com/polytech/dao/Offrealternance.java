package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Offrealternance generated by hbm2java
 */
public class Offrealternance implements java.io.Serializable {

    private int id;
    //@JsonManagedReference("Ent_offrealternances")
    private Entreprise entreprise;
    private String titre;
    private String url;

    public Offrealternance() {
    }

    public Offrealternance(int id) {
        this.id = id;
    }

    public Offrealternance(int id, Entreprise entreprise, String titre, String url) {
        this.id = id;
        this.entreprise = entreprise;
        this.titre = titre;
        this.url = url;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entreprise getEntreprise() {
        return this.entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
