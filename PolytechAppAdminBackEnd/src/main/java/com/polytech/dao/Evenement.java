package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.polytech.json.deserialization.JsonDateDeserializer;
import com.polytech.json.deserialization.JsonDateDeserializer_hour;
import com.polytech.json.deserialization.JsonJodaDateDeserializer;
import com.polytech.json.deserialization.JsonJodaHourDeserializer;
import com.polytech.json.serialization.JsonDateSerializer;
import com.polytech.json.serialization.JsonDateSerializer_hour;
import com.polytech.json.serialization.JsonJodaDateSerializer;
import com.polytech.json.serialization.JsonJodaHourSerializer;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Evenement generated by hbm2java
 */
public class Evenement implements java.io.Serializable {

    private int id;
    private String typeevt;
    private Date dateevt;
    private String duree;
    private Date heuredebut;
    private Date heurefin;
    private String description;

    private Set entreprisepresences = new HashSet(0);
    private Set<Planning> plannings = new HashSet(0);
    private Set<Etudiantevenement> etudiantpresents = new HashSet(0);

    public Evenement() {
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(int id, String typeevt, Date dateevt, String duree, Date heuredebut, Date heurefin, String description, Set entreprisepresences, Set<Planning> plannings, Set<Etudiantevenement> etudiantpresents) {
        this.id = id;
        this.typeevt = typeevt;
        this.dateevt = dateevt;
        this.duree = duree;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.description = description;
        this.entreprisepresences = entreprisepresences;
        this.plannings = plannings;
        this.etudiantpresents = etudiantpresents;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeevt() {
        return this.typeevt;
    }

    public void setTypeevt(String typeevt) {
        this.typeevt = typeevt;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getDateevt() {
        return this.dateevt;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setDateevt(Date dateevt) {
        this.dateevt = dateevt;
    }

    public String getDuree() {
        return this.duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @JsonSerialize(using = JsonDateSerializer_hour.class)
    public Date getHeuredebut() {
        return this.heuredebut;
    }

    @JsonDeserialize(using = JsonDateDeserializer_hour.class)
    public void setHeuredebut(Date heuredebut) {
        this.heuredebut = heuredebut;
    }

    @JsonSerialize(using = JsonDateSerializer_hour.class)
    public Date getHeurefin() {
        return this.heurefin;
    }

    @JsonDeserialize(using = JsonDateDeserializer_hour.class)
    public void setHeurefin(Date heurefin) {
        this.heurefin = heurefin;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Entreprisepresence> getEntreprisepresences() {
        return this.entreprisepresences;
    }

    public void setEntreprisepresences(Set<Entreprisepresence> entreprisepresences) {
        this.entreprisepresences = entreprisepresences;
    }

    public Set<Planning> getPlannings() {
        return this.plannings;
    }

    public void setPlannings(Set<Planning> plannings) {
        this.plannings = plannings;
    }

    public Set<Etudiantevenement> getEtudiantpresents() {
        return this.etudiantpresents;
    }

    public void setEtudiantpresents(Set<Etudiantevenement> etudiants) {
        this.etudiantpresents = etudiants;
    }

}
