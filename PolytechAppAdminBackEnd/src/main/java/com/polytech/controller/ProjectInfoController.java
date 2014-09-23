package com.polytech.controller;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Etudiant;
import com.polytech.dao.Salle;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.dao.manager.EtudiantManager;
import com.polytech.dao.manager.SalleManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import com.polytech.model.*;
import com.sun.net.httpserver.Authenticator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@RestController
public class ProjectInfoController {

    EntrepriseManager entrepriseManager =  new EntrepriseManager();
    EtudiantManager etuManager = new EtudiantManager();
    SalleManager salleManager = new SalleManager();
    
    @RequestMapping("/")
    public ProjectInfo hello() {
        return new ProjectInfo("0.1.0", "PolytechAppAdmin");
    }

    @RequestMapping(value = "/entreprises", method = RequestMethod.GET)
    public Object allEnterprise() {
        
        try {
            
            List<Entreprise> entreprises = entrepriseManager.getAllEnterprise();
            return entreprises;
            
        } catch (Exception ex) {
            
            return ExceptionHandler.handle(ex);
            
        }

    }

    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.GET)
    public Object oneEnterprise(@PathVariable String id) {
        
        try {
            
            Entreprise e = entrepriseManager.getEnterpriseById(Integer.parseInt(id));
            return e;
            
        } catch (Exception ex) {
            
            return ExceptionHandler.handle(ex);
            
        }
    }

    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.DELETE)
    public Object deleteEnterprise(@PathVariable String id) {
        
        try {
            
            int idInt = Integer.parseInt(id);
            return SuccessHandler.handle(entrepriseManager.deleteEnterpriseById(idInt));
            
        } catch (Exception ex) {
            
            return ExceptionHandler.handle(ex);
            
        }
        
    }
    
    
   
    //ETUDIANTS
    /**
     * GET all students
     *
     * @return
     */
    @RequestMapping(value = "/etudiants", method = RequestMethod.GET)
    public Object allEtudiant() {
        try {
            List<Etudiant> etudiants = etuManager.getAllEtudiant();
            return etudiants;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }
    }

    /**
     * GET one etudiant
     *
     * @param id : id Etudiant
     * @return
     */
    @RequestMapping(value = "/etudiant/{id}", method = RequestMethod.GET)
    public Object oneEtudiant(@PathVariable String id) {
        try {
            Etudiant etudiant = etuManager.getEtudiantByID(Integer.parseInt(id));
            return etudiant;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }
    }

    /**
     * GET all etudiants
     * @param id
     * @return 
     */
    @RequestMapping(value = "/etudiant/{id}", method = RequestMethod.DELETE)
    public Object deleteEtudiant(@PathVariable String id) {

        String error = "";
        int idEtu = Integer.parseInt(id);

        try {
            etuManager.deleteEtudiantById(idEtu);
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
        return " Erreur : " + error;
    }

    //SALLES
    @RequestMapping(value = "/salles", method = RequestMethod.GET)
    public Object allSalle() {

        try {
            List<Salle> salles = salleManager.getAllSalle();
            return salles;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    @RequestMapping(value = "/salle/{id}", method = RequestMethod.GET)
    public String oneSalle(@PathVariable String id) {
            Salle salle = null;
            String error = "";
        try {
           salle = salleManager.getSalleById(Integer.parseInt(id));
            
        } catch (Exception ex) {
              error = ex.getMessage();
        }
        return " Erreur : " + error;
    }

    @RequestMapping(value = "/salle/{id}", method = RequestMethod.DELETE)
    public Object deleteSalle(@PathVariable String id) {

        String error = "";

        int idSalle = Integer.parseInt(id);

        try {
            salleManager.deleteSalleById(idSalle);
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
        return " Erreur : " + error;
    }

    @RequestMapping(value = "/salle/add", method = RequestMethod.POST)
    public @ResponseBody
    String createSalle(@RequestBody Salle salle) {
        
        
        // json fonctionnement d'envoi 
        // {"id" : 33 ,"libelle":"Salle 9994","localisation":"36 eme etage fond","capacite":350}
        
        String error = "";
        salle.setLibelle("SALUTTTTTTTT");

        try {
            salleManager.addSalle(salle);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return " Erreur : " + error;

        
    }
}
