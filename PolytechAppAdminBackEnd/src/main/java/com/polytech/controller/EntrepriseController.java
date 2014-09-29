package com.polytech.controller;

import com.polytech.dao.Entreprise;
import com.polytech.dao.manager.EntrepriseManager;
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
 * This class is the web controller for entreprise's webservice.
 * 
 * @author Florian Courtial
 */
@ComponentScan
@RestController
public class EntrepriseController {
    
    /* The database manager */
    EntrepriseManager entrepriseManager = new EntrepriseManager();
     
    /**
     * This method returns all entreprises when the .../entreprises URL is 
     * called.
     * 
     * @return A JSON object or a HTTP status in case of an error. 
     */
    @RequestMapping(value = "/entreprises", method = RequestMethod.GET)
    public Object allEnterprise() {

        try {

            List<Entreprise> entreprises = entrepriseManager.getAllEnterprise();
            return entreprises;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }

    }

    /**
     * This method returns an entreprise retrieved using its id passed as
     * parameter into the URL. For instance .../entreprise/1.
     * 
     * @param id The identifier of the entreprise to get retrieved from the URL.
     * @return A JSON object or a HTTP status in case of an error.
     */
    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.GET)
    public Object oneEnterprise(@PathVariable String id) {

        try {

            Entreprise entreprise = entrepriseManager.getEnterpriseById(Integer.parseInt(id));
            return entreprise;

        } catch (Exception ex) {

            return ExceptionHandler.handle(ex);

        }
    }

    /**
     * This method deletes the entreprise from the database using the id passed
     * as parameter into the URL.
     * 
     * @param id The identifier of the entreprise to delete retrieved from the 
     * URL.
     * @return A HTTP status regarding the status of the deletion.
     */
    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.DELETE)
    public Object deleteEnterprise(@PathVariable String id) {
        
        try {
            
            int idInt = Integer.parseInt(id);
            return SuccessHandler.handle(entrepriseManager.deleteEnterpriseById(idInt));
            
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
    @RequestMapping(value = "/entreprise/add", method = RequestMethod.PUT)
    public Object createEntreprise(@RequestBody Entreprise ent) {

      
        try {
            return SuccessHandler.handle(entrepriseManager.addEntreprise(ent));
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }

    }
    
}
