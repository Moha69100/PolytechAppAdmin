/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.controller;

import com.polytech.dao.VoeuxEtudiant;
import com.polytech.dao.manager.VoeuxEtudiantManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Epulapp
 */
@ComponentScan
@RestController
public class VoeuxEtudiantController {

    /* The database manager */
    VoeuxEtudiantManager voeuxEtuManager = new VoeuxEtudiantManager();

    /**
     * This method returns all voeuxEtudiants when the .../voeuxetudiants URL is
     * called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/voeuxetudiants", method = RequestMethod.GET)
    public Object allVoeuxEtudiant() {

        try {

            List<VoeuxEtudiant> voeuxEtudiants = voeuxEtuManager.getAllVoeuxEtudiant();
            return voeuxEtudiants;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }

    }

    /**
     * This method returns an voeuxEtudiant retrieved using its id passed as
     * parameter into the URL. For instance .../voeuxEtudiant/1.
     *
     * @param id The identifier of the voeuxEtudiant to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/voeuxetudiants/{id}", method = RequestMethod.GET)
    public Object oneVoeuxEtudiant(@PathVariable String id) {

        try {

            VoeuxEtudiant voeuxEtudiant = voeuxEtuManager.getVoeuxEtudiantById(Integer.parseInt(id));
            return voeuxEtudiant;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }
    }

    /**
     * This method deletes the voeuxEtudiant from the database using the id passed
     * as parameter into the URL.
     *
     * @param id The identifier of the voeuxEtudiant to delete retrieved from the
     * URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/voeuxetudiants/{id}", method = RequestMethod.DELETE)
    public Object deleteEnterprise(@PathVariable String id) {

        try {

            int idInt = Integer.parseInt(id);
            return SuccessHandler.handle(voeuxEtuManager.deleteVoeuxEtudiantById(idInt));

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }
    }

    /**
     * This method adds an enterprise into the database. A JSON object passed
     * using POST represents the object to insert.
     *
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/voeuxetudiants/add", method = RequestMethod.PUT)
    public Object createEntreprise(@RequestBody VoeuxEtudiant voeuxEtu) {

        
        try {
            return SuccessHandler.handle(voeuxEtuManager.addVoeuxEtudiant(voeuxEtu));
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }

    }
    
    /**
     * This method updates a voeuxEtudiant into the database. A JSON object passed using
     * POST represents the object to update.
     *
     * @param voeuxEtu Object to update created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the update.
     */
    @RequestMapping(value = "/voeuxetudiant", method = RequestMethod.POST)
    public Object updateSalle(@RequestBody VoeuxEtudiant voeuxEtu) {
        String error = "";
        try {
            return SuccessHandler.handle(voeuxEtuManager.updateVoeuxEtudiant(voeuxEtu));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }

}
