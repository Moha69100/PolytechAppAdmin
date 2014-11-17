/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.controller;

import com.polytech.dao.Entretien;
import com.polytech.dao.manager.EntretienManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
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
public class EntretienController {
    /* The database manager */

    EntretienManager entretienManager = new EntretienManager();

    /**
     * Return list of entretien where id planning = id
     */
    @RequestMapping(value = "/entretiensByEvent/{id}", method = RequestMethod.GET)
    public Object entretienByPlanning(@PathVariable String id) {
        List<Entretien> entretiens = null;
        String error = "";
        try {
            entretiens = entretienManager.getEntretiensByEvent(Integer.parseInt(id));

        } catch (Exception ex) {
            error = ex.getMessage();
            return ExceptionHandler.handle(ex);
        }
        return entretiens;
    }

    /**
     * This method returns all entretiens when the .../entretiens URL is called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/entretiens", method = RequestMethod.GET)
    public Object allEntretien() {

        try {
            List<Entretien> entretiens = entretienManager.getAllEntretien();
            return entretiens;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    /**
     * This method returns an entretien retrieved using its id passed as
     * parameter into the URL. For instance .../entretien/1.
     *
     * @param id The identifier of the entretien to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/entretien/{id}", method = RequestMethod.GET)
    public Object oneEntretien(@PathVariable String id) {
        Entretien entretien = null;
        String error = "";
        try {
            entretien = entretienManager.getEntretienByID(Integer.parseInt(id));

        } catch (Exception ex) {
            error = ex.getMessage();
            return ExceptionHandler.handle(ex);
        }
        return entretien;
    }

    /**
     * This method deletes the entretien from the database using the id passed
     * as parameter into the URL.
     *
     * @param id The identifier of the entretien to delete retrieved from the
     * URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/entretien/{id}", method = RequestMethod.DELETE)
    public Object deleteEntretien(@PathVariable String id) {

        String error = "";

        int idEntretien = Integer.parseInt(id);

        try {
            return SuccessHandler.handle(entretienManager.deleteEntretienById(idEntretien));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }

    /**
     * This method adds an entretien into the database. A JSON object passed
     * using POST represents the object to insert.
     *
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/entretien/add", method = RequestMethod.PUT)
    public @ResponseBody
    Object createEntretien(@RequestBody Entretien entretien) {

        // json fonctionnement d'envoi 
        String error = "";

        try {
            return SuccessHandler.handle(entretienManager.addEntretien(entretien));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }

    }

    /**
     * This method updates a entretien into the database. A JSON object passed
     * using POST represents the object to update.
     *
     * @param entretien Object to update created from the JSON file passed using
     * POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/entretien", method = RequestMethod.POST)
    public Object updateEntretien(@RequestBody Entretien entretien) {
        String error = "";

        try {
            return SuccessHandler.handle(entretienManager.updateEntretien(entretien));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }
}
