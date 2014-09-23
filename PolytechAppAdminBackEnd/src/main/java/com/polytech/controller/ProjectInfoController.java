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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@ComponentScan
@RestController
public class ProjectInfoController {

    EtudiantManager etuManager = new EtudiantManager();
    SalleManager salleManager = new SalleManager();

    @RequestMapping("/")
    public ProjectInfo hello() {
        return new ProjectInfo("0.1.0", "PolytechAppAdmin");
    }
    
    //==================== ETUDIANT ======================================
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
     *
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

    @RequestMapping(value = "/etudiant/add", method = RequestMethod.POST)
    public @ResponseBody
    String createEtudiant(@RequestBody Etudiant etu) {

        // json fonctionnement d'envoi 
        // {"id" : 33 ,"libelle":"Salle 9994","localisation":"36 eme etage fond","capacite":350}
        String error = "";
        etu.setAdresse("TEST ADRESSE");

        try {
            etuManager.addEtudiant(etu);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return " Erreur : " + error;

    }

    @RequestMapping(value = "/etudiants/upload", method = RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/etudiants/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                Ciell2CsvReader reader = new Ciell2CsvReader();
                reader.parse(bytes);

                return "You successfully uploaded file";
            } catch (Exception e) {
                return "You failed to upload => " + e.getMessage();
            }
        } else {
            return "You failed to upload file because the file was empty.";
        }
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
}
