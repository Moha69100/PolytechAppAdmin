package com.polytech.controller;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Etudiant;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.dao.manager.EtudiantManager;
import com.polytech.exception.ExceptionHandler;
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

    EntrepriseManager entrepriseManager =  new EntrepriseManager();
    EtudiantManager etuManager = new EtudiantManager();
    
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
    public String deleteEnterprise(@PathVariable String id) {
        return "Delete l'entreprise " + id + ".";
    }
    
    
    //ETUDIANTS
    
       // lire les headers
    @RequestMapping(value = "/etudiants", method = RequestMethod.GET)
    public List<Etudiant> allEtudiant() {

        List<Etudiant> etudiants = etuManager.getList();
        return etudiants;

    }

    @RequestMapping(value = "/etudiant/{id}", method = RequestMethod.GET)
    public String oneEtudiant(@PathVariable String id) {
        return "L'entreprise " + id + ".";
    }

 
    
    
}
