/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.controller;

import com.polytech.dao.Offrealternance;
import com.polytech.dao.manager.OffreAlternanceManager;
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
public class OffreAlternanceController {
    
    /* The database manager */
    OffreAlternanceManager offreAlternanceManager = new OffreAlternanceManager();
    
    /**
     * This method returns all offers when the .../offres URL is 
     * called.
     * 
     * @return A JSON object or a HTTP status in case of an error. 
     */
    @RequestMapping(value = "/offres", method = RequestMethod.GET)
    public Object allOffreAlternance() {

        try {

            List<Offrealternance> offres = offreAlternanceManager.getAllOffresAlternance();
            return offres;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }

    }
    
    /**
     * This method returns an offer retrieved using its id passed as
     * parameter into the URL. For instance .../offre/1.
     * 
     * @param id The identifier of the offer to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/offre/{id}", method = RequestMethod.GET)
    public Object oneOffreAlternance(@PathVariable String id) {

        try {

            Offrealternance offre = offreAlternanceManager.getOffreAlternanceById(Integer.parseInt(id));
            return offre;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }
    }
    

    /**
     * This method deletes the offer from the database using the id passed
     * as parameter into the URL.
     * 
     * @param id The identifier of the offer to delete retrieved from the 
     * URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/offre/{id}", method = RequestMethod.DELETE)
    public Object deleteOffreAlternance(@PathVariable String id) {
        
        try {
            
            int idInt = Integer.parseInt(id);
            return SuccessHandler.handle(offreAlternanceManager.deleteOffreAlternanceById(idInt));
            
        } catch (Exception ex) {
            
            return ExceptionHandler.handle(ex);
            
        }
    }
    
    
    /**
     * This method adds an offer into the database. A JSON object passed
     * using POST represents the object to insert.
     * 
     * @param offre Object to insert created from the JSON file passed using POST.
     * @return A HTTP status regarding the status of the insertion.
     */
    @RequestMapping(value = "/offre/add", method = RequestMethod.PUT,consumes="application/json" )
    public Object createOffreAlternance(@RequestBody Offrealternance offre) {

      
        try {
            return SuccessHandler.handle(offreAlternanceManager.addOffreAlternance(offre));
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }

    }
    
    
    /**
     * This method updates a company into database
     * @param offre Company to update into database
     * @return A HTTP status regarding the status of the update.
     */
    @RequestMapping(value = "/offre", method = RequestMethod.POST)
    public Object updateOffreAlternance(@RequestBody Offrealternance offre) {
        String error = "";
        try {
            return SuccessHandler.handle(offreAlternanceManager.updateOffreAlternance(offre));
        } catch (Exception e) {
            error = e.getMessage();
            return ExceptionHandler.handle(e);
        }
    }
    
}
