package com.polytech.controller;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Etudiant;
import com.polytech.dao.Salle;
import com.polytech.dao.manager.AuthenticationManager;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.dao.manager.EtudiantManager;
import com.polytech.dao.manager.SalleManager;
import com.polytech.dao.manager.SessionManager;
import com.polytech.model.*;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@ComponentScan
@RestController
public class ProjectInfoController {

    SessionManager manager = new SessionManager();
    EntrepriseManager entrepriseManager = new EntrepriseManager(manager);
    EtudiantManager etuManager = new EtudiantManager(manager);
    SalleManager salleManager = new SalleManager(manager);
    AuthenticationManager authManager = new AuthenticationManager(manager);

    @RequestMapping("/")
    public ProjectInfo hello() {
        return new ProjectInfo("0.1.0", "PolytechAppAdmin");
    }

    //ENTREPRISE
    @RequestMapping(value = "/entreprises", method = RequestMethod.GET)
    public List<Entreprise> allEnterprise() {

        List<Entreprise> entreprises = entrepriseManager.getAllEnterprise();
        return entreprises;

    }

    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.GET)
    public Entreprise oneEnterprise(@PathVariable String id) {
        return entrepriseManager.getEnterpriseById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.DELETE)
    public String deleteEnterprise(@PathVariable String id) {
        return "Delete l'entreprise " + id + ".";
    }

    //ETUDIANTS
    /**
     * GET all students
     *
     * @return
     */
    @RequestMapping(value = "/etudiants", method = RequestMethod.GET)
    public List<Etudiant> allEtudiant() {
        List<Etudiant> etudiants = etuManager.getAllEtudiant();
        return etudiants;
    }

    /**
     * GET one etudiant
     *
     * @param id : id Etudiant
     * @return
     */
    @RequestMapping(value = "/etudiant/{id}", method = RequestMethod.GET)
    public Etudiant oneEtudiant(@PathVariable String id) {
        Etudiant etudiant = etuManager.getEtudiantByID(Integer.parseInt(id));
        return etudiant;
    }

    /**
     * GET all etudiants
     * @param id
     * @return 
     */
    @RequestMapping(value = "/etudiant/{id}", method = RequestMethod.DELETE)
    public String deleteEtudiant(@PathVariable String id) {

        String error = "";
        int idEtu = Integer.parseInt(id);

        try {
            etuManager.deleteEtudiantById(idEtu);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return " Erreur : " + error;
    }

    //SALLES
    @RequestMapping(value = "/salles", method = RequestMethod.GET)
    public List<Salle> allSalle() {

        List<Salle> salles = salleManager.getAllSalle();
        return salles;

    }

    @RequestMapping(value = "/salle/{id}", method = RequestMethod.GET)
    public String oneSalle(@PathVariable String id) {
        return "La salle " + id + ".";
    }

    @RequestMapping(value = "/salle/{id}", method = RequestMethod.DELETE)
    public String deleteSalle(@PathVariable String id) {

        String error = "";

        int idSalle = Integer.parseInt(id);

        try {
            salleManager.deleteSalleById(idSalle);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return " Erreur : " + error;
    }
    
    
    //AUTHENTIFICATION
      //Page d'authentification
    /*
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public int authentication(@PathVariable String user, @PathVariable String password){
        return authManager.auth(user,password);
    }*/
    
    
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authentication(){
        int myInt = authManager.auth("TestUsr","TestPwd");
        String APIKey = Integer.toString(myInt);
        return "Utilisateur : TestUsr ; clé API : "+APIKey;
    }
}
