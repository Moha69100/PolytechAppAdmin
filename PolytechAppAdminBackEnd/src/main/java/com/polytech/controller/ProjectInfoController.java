
package com.polytech.controller;

import com.polytech.dao.Etudiant;
import com.polytech.dao.Salle;

import com.polytech.dao.manager.AuthenticationManager;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.dao.manager.EtudiantManager;
import com.polytech.dao.manager.SalleManager;
import com.polytech.dao.manager.SessionManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.model.*;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@ComponentScan
@RestController
public class ProjectInfoController {

    SalleManager salleManager = new SalleManager();
    AuthenticationManager authManager = new AuthenticationManager();
    
    
    @RequestMapping("/")
    public ProjectInfo hello() {
        return new ProjectInfo("0.1.0", "PolytechAppAdmin");
    }

    //==================== SALLE ======================================
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
    public Object oneSalle(@PathVariable String id) {
        Salle salle = null;
        String error = "";
        try {
            salle = salleManager.getSalleById(Integer.parseInt(id));

        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return salle;
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
        salle.setLibelle("ZzZzzZzZzZ");

        try {
            salleManager.addSalle(salle);
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
        String retour;
        try{
                
            retour = "Utilisateur : TestUsr ; clé API : " + authManager.auth("TestUsr","TestPwd");
        }catch(Exception e){
            retour = "Erreur : "+e.getMessage();
        }
        
        return retour;
    }
}
