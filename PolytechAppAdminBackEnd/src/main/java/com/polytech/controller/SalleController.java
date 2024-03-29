package com.polytech.controller;

import com.polytech.dao.Salle;
import com.polytech.dao.manager.SalleManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Epulapp
 *
 */
@ComponentScan
@RestController
public class SalleController {

    /* The database manager */
    SalleManager salleManager = new SalleManager();

    /**
     * This method returns all salles when the .../salles URL is called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/salles", method = RequestMethod.GET)
    public Object allSalle() {

        try {
            List<Salle> salles = salleManager.getAllSalle();
            return salles;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    /**
     * This method returns an salle retrieved using its id passed as parameter
     * into the URL. For instance .../salle/1.
     *
     * @param id The identifier of the salle to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/salle/{id}", method = RequestMethod.GET)
    public Object oneSalle(@PathVariable String id) {
        Salle salle = null;
        String error = "";
        try {
            salle = salleManager.getSalleById(Integer.parseInt(id));

        } catch (Exception ex) {
            error = ex.getMessage();
            return ExceptionHandler.handle(ex);
        }
        return salle;
    }

    /**
     * This method deletes the salle from the database using the id passed as
     * parameter into the URL.
     *
     * @param id The identifier of the salle to delete retrieved from the URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/salle/{id}", method = RequestMethod.DELETE)
    public Object deleteSalle(@PathVariable String id) {

        String error = "";

        int idSalle = Integer.parseInt(id);

        try {
            return SuccessHandler.handle(salleManager.deleteSalleById(idSalle));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }

    /**
     * This method adds an salle into the database. A JSON object passed using
     * POST represents the object to insert.
     *
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/salle/add", method = RequestMethod.PUT)
    public @ResponseBody
    Object createSalle(@RequestBody Salle salle) {

        // json fonctionnement d'envoi 
        // {"id" : 33 ,"libelle":"Salle 9994","localisation":"36 eme etage fond","capacite":350}
        String error = "";

        try {
            return SuccessHandler.handle(salleManager.addSalle(salle));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }

    }

    /**
     * This method updates a salle into the database. A JSON object passed using
     * POST represents the object to update.
     *
     * @param salle Object to update created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/salle", method = RequestMethod.POST)
    public Object updateSalle(@RequestBody Salle salle) {
        String error = "";
        
        
     
        try {
            return SuccessHandler.handle(salleManager.updateSalle(salle));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }
}
