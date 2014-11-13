package com.polytech.controller;

import com.polytech.dao.Evenement;
import com.polytech.dao.Planning;
import com.polytech.dao.manager.EvenementManager;
import com.polytech.dao.manager.PlanningManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import com.polytech.model.PlanningGenerator;
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
public class PlanningController {
    
    /* The database manager */

    PlanningManager planningManager = new PlanningManager();

    
    /**
     * used to call the planning algorithm
     * @id id of the evenement
     */
    @RequestMapping(value = "/planningGenerate/{id}", method = RequestMethod.GET)
    public void algoPlanning(@PathVariable String id) {
       
        String error = "";
        try {
            EvenementManager evenementManager = new EvenementManager();
            Evenement evt = evenementManager.getEvenementById(Integer.parseInt(id));
            PlanningGenerator planning = new PlanningGenerator(evt, 30);
            planning.generate();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }
    
    /**
     * This method returns all planning when the .../plannings URL is called.
     *
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/plannings", method = RequestMethod.GET)
    public Object allPlanning() {

        try {
            List<Planning> planning = planningManager.getAllPlanning();
            return planning;
        } catch (Exception ex) {
            return ExceptionHandler.handle(ex);
        }

    }

    /**
     * This method returns an planning retrieved using its id passed as parameter
     * into the URL. For instance .../planning/1.
     *
     * @param id The identifier of the planning to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/planning/{id}", method = RequestMethod.GET)
    public Object onePlanning(@PathVariable String id) {
        Planning planning = null;
        String error = "";
        try {
            planning = planningManager.getPlanningById(Integer.parseInt(id));

        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return planning;
    }

    /**
     * This method deletes the planning from the database using the id passed as
     * parameter into the URL.
     *
     * @param id The identifier of the planning to delete retrieved from the URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/planning/{id}", method = RequestMethod.DELETE)
    public Object deletePlanning(@PathVariable String id) {

        String error = "";

        int idPlanning = Integer.parseInt(id);

        try {
            return SuccessHandler.handle(planningManager.deletePlanningById(idPlanning));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }

    /**
     * This method adds an planning into the database. A JSON object passed using
     * POST represents the object to insert.
     *
     * @param ent Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/planning/add", method = RequestMethod.PUT)
    public @ResponseBody
    Object createPlanning(@RequestBody Planning planning) {

        // json fonctionnement d'envoi 
      
        String error = "";

        try {
            return SuccessHandler.handle(planningManager.addPlanning(planning));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }

    }

    /**
     * This method updates a planning into the database. A JSON object passed using
     * POST represents the object to update.
     *
     * @param planning Object to update created from the JSON file passed using
     * POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/planning", method = RequestMethod.POST)
    public Object updatePlanning(@RequestBody Planning planning) {
        String error = "";

        try {
            return SuccessHandler.handle(planningManager.updatePlanning(planning));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }
    
}
