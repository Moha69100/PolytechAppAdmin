package com.polytech.controller;


import com.polytech.dao.Evenement;
import com.polytech.dao.manager.EvenementManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the web controller for entreprise's webservice.
 *
 * @author
 */
@ComponentScan
@RestController
public class EvenementController {
    /* The database manager */

    EvenementManager evenementManager = new EvenementManager();

    /**
     * This method returns all evenements when the .../evenements URL is called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/evenements", method = RequestMethod.GET)
    public Object allEvenement() {

        try {
            List<Evenement> evenements = evenementManager.getAllEvenement();
            return evenements;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    /**
     * This method returns an evenement retrieved using its id passed as parameter
     * into the URL. For instance .../evenement/1.
     *
     * @param id The identifier of the evenement to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/evenement/{id}", method = RequestMethod.GET)
    public Object oneEvenement(@PathVariable String id) {
        Evenement evenement = null;
        String error = "";
        try {
            evenement = evenementManager.getEvenementById(Integer.parseInt(id));

        } catch (Exception ex) {
            error = ex.getMessage();
            return ExceptionHandler.handle(ex);
        }
        return evenement;
    }

    /**
     * This method deletes the evenement from the database using the id passed as
     * parameter into the URL.
     *
     * @param id The identifier of the evenement to delete retrieved from the URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/evenement/{id}", method = RequestMethod.DELETE)
    public Object deleteEvenement(@PathVariable String id) {

        String error = "";

        int idEvenement = Integer.parseInt(id);

        try {
            return SuccessHandler.handle(evenementManager.deleteEvenementById(idEvenement));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }

    /**
     * This method adds an evenement into the database. A JSON object passed using
     * POST represents the object to insert.
     *
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/evenement/add", method = RequestMethod.PUT)
    public @ResponseBody
    Object createEvenement(@RequestBody Evenement evenement) {

        // json fonctionnement d'envoi 
      
        String error = "";

        try {
            return SuccessHandler.handle(evenementManager.addEvenement(evenement));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }

    }

    /**
     * This method updates a evenement into the database. A JSON object passed using
     * POST represents the object to update.
     *
     * @param evenement Object to update created from the JSON file passed using
     * POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/evenement", method = RequestMethod.POST)
    public Object updateEvenement(@RequestBody Evenement evenement) {
        String error = "";

        try {
            return SuccessHandler.handle(evenementManager.updateEvenement(evenement));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }
}
