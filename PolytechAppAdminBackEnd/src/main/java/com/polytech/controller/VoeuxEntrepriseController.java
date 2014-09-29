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
public class VoeuxEntrepriseController {

    /* The database manager */
    VoeuxEntrepriseManager voeuxEntrepriseManager = new VoeuxEntrepriseManager();

    /**
     * This method returns all voeuxEntreprises when the .../voeuxEntreprises
     * URL is called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/voeuxEntreprises", method = RequestMethod.GET)
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
    @RequestMapping(value = "/voeuxEntreprise/{id}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/voeuxEntreprise/{id}", method = RequestMethod.DELETE)
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
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/voeuxEntreprise/add", method = RequestMethod.PUT)
    public Object createVoeuxEntreprise(@RequestBody VoeuxEntreprise voeuxEnt) {

        try {
            return SuccessHandler.handle(voeuxEntrepriseManager.addVoeuxEntreprise(voeuxEnt));
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }

    }
}
