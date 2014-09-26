/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.controller;

import com.polytech.dao.Etudiant;
import com.polytech.dao.manager.EtudiantManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.model.Ciell2CsvReader;
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

/**
 *
 * @author Epulapp
 */
@ComponentScan
@RestController
public class EtudiantController {

    /* The database manager */
    EtudiantManager etuManager = new EtudiantManager();

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
    
    @RequestMapping(value = "/etudiants/upload", method = RequestMethod.POST)
    public @ResponseBody
    Object handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Ciell2CsvReader reader = new Ciell2CsvReader(); 
                return reader.parse(bytes);
            } catch (Exception e) {
                return ExceptionHandler.handle(e);
            }
        } else {
            return ExceptionHandler.handle(new Exception());
        }
    }

}
