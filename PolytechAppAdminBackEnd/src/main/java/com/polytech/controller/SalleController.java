
package com.polytech.controller;

import com.polytech.dao.Salle;
import com.polytech.dao.manager.SalleManager;
import com.polytech.exception.ExceptionHandler;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * This method returns all salles when the .../salles URL is 
     * called.
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
     * This method returns an salle retrieved using its id passed as
     * parameter into the URL. For instance .../salle/1.
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
        }
        return salle;
    }

   /**
     * This method deletes the salle from the database using the id passed
     * as parameter into the URL.
     * 
     * @param id The identifier of the salle to delete retrieved from the 
     * URL.
     * @return A HTTP status regarding the status of the deletion.
     */
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

   /**
     * This method adds an salle into the database. A JSON object passed
     * using POST represents the object to insert.
     * 
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/salle/add", method = RequestMethod.POST)
    public @ResponseBody
    String createSalle(@RequestBody Salle salle) {

        // json fonctionnement d'envoi 
        // {"id" : 33 ,"libelle":"Salle 9994","localisation":"36 eme etage fond","capacite":350}
        String error = "";
        

        try {
            salleManager.addSalle(salle);
        } catch (Exception e) {
            error = e.getMessage();
        }
        return " Erreur : " + error;

    }
}
