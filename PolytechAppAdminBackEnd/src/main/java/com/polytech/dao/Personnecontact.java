package com.polytech.dao;
// Generated 16 sept. 2014 15:04:06 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Personnecontact generated by hbm2java
 */
@Entity
@Table(name="personnecontact"
    ,schema="appschema"
)
public class Personnecontact  implements java.io.Serializable {


     private int id;
     private Entreprise entreprise;
     private String civilite;
     private String nom;
     private String prenom;
     private String role;
     private String telfixe;
     private String telportable;
     private String email;
     private Set entreprisepresences = new HashSet(0);

    public Personnecontact() {
    }

	
    public Personnecontact(int id) {
        this.id = id;
    }
    public Personnecontact(int id, Entreprise entreprise, String civilite, String nom, String prenom, String role, String telfixe, String telportable, String email, Set entreprisepresences) {
       this.id = id;
       this.entreprise = entreprise;
       this.civilite = civilite;
       this.nom = nom;
       this.prenom = prenom;
       this.role = role;
       this.telfixe = telfixe;
       this.telportable = telportable;
       this.email = email;
       this.entreprisepresences = entreprisepresences;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="entid")
    public Entreprise getEntreprise() {
        return this.entreprise;
    }
    
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    
    @Column(name="civilite")
    public String getCivilite() {
        return this.civilite;
    }
    
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    
    @Column(name="nom")
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="prenom")
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    @Column(name="role")
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    
    @Column(name="telfixe")
    public String getTelfixe() {
        return this.telfixe;
    }
    
    public void setTelfixe(String telfixe) {
        this.telfixe = telfixe;
    }

    
    @Column(name="telportable")
    public String getTelportable() {
        return this.telportable;
    }
    
    public void setTelportable(String telportable) {
        this.telportable = telportable;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="personnecontact")
    public Set getEntreprisepresences() {
        return this.entreprisepresences;
    }
    
    public void setEntreprisepresences(Set entreprisepresences) {
        this.entreprisepresences = entreprisepresences;
    }




}


