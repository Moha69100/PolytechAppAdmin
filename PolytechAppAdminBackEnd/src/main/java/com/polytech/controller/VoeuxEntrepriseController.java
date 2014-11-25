/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.controller;

import com.polytech.dao.VoeuxEntreprise;
import com.polytech.dao.manager.VoeuxEntrepriseManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Epulapp
 */
@ComponentScan
@RestController
public class VoeuxEntrepriseController {

    /* The database manager */
    VoeuxEntrepriseManager voeuxEntrepriseManager = new VoeuxEntrepriseManager();

    /**
     * This method returns all voeuxEntreprises when the .../voeuxEntreprises
     * URL is called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/voeuxentreprises", method = RequestMethod.GET)
    public Object allVoeuxEnterprise() {

        try {

            List<VoeuxEntreprise> voeuxEntreprises = voeuxEntrepriseManager.getAllVoeuxEntreprise();
            return voeuxEntreprises;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }

    }

    /**
     * This method returns an voeuxEntreprise retrieved using its id passed as
     * parameter into the URL. For instance .../voeuxEntreprise/1.
     *
     * @param id The identifier of the voeuxEntreprise to get retrieved from the
     * URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/voeuxentreprises/{id}", method = RequestMethod.GET)
    public Object oneVoeuxEnterprise(@PathVariable String id) {

        try {

            VoeuxEntreprise voeuxEntreprise = voeuxEntrepriseManager.getVoeuxEntrepriseById(Integer.parseInt(id));
            return voeuxEntreprise;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }
    }

    /**
     * This method deletes the voeuxEntreprise from the database using the id
     * passed as parameter into the URL.
     *
     * @param id The identifier of the voeuxEntreprise to delete retrieved from
     * the URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/voeuxentreprises/{id}", method = RequestMethod.DELETE)
    public Object deleteVoeuxEnterprise(@PathVariable String id) {

        try {

            int idInt = Integer.parseInt(id);
            return SuccessHandler.handle(voeuxEntrepriseManager.deleteVoeuxEnterpriseById(idInt));

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }
    }

    /**
     * This method adds an voeuxEntreprise into the database. A JSON object
     * passed using POST represents the object to insert.
     *
     * @param voeuxEnt Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/voeuxentreprises/add", method = RequestMethod.PUT)
    public Object createVoeuxEntreprise(@RequestBody VoeuxEntreprise voeuxEnt) {

        try {
            return SuccessHandler.handle(voeuxEntrepriseManager.addVoeuxEntreprise(voeuxEnt));
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }

    }
    
    /**
     * This method updates a voeuxEntreprise into the database. A JSON object passed using
     * POST represents the object to update.
     *
     * @param voeuxEnt Object to update created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the update.
     */
    @RequestMapping(value = "/voeuxentreprise", method = RequestMethod.POST)
    public Object updateSalle(@RequestBody VoeuxEntreprise voeuxEnt) {
        String error = "";
        try {
            return SuccessHandler.handle(voeuxEntrepriseManager.updateVoeuxEntreprise(voeuxEnt));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }
}
