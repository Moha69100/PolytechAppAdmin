
package com.polytech.controller;

import com.polytech.dao.manager.PersonneContactManager;
import com.polytech.exception.ExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the spring web controller for the personne contact web 
 * service.
 * @author Florian.Courtial
 */
@ComponentScan
@RestController
public class PersonneContactController {
    
    PersonneContactManager pcm = new PersonneContactManager();
    
    /**
     * This method returns a list of personne contact regarding the id of the 
     * entreprise pass as parameter.
     * @param id Id of the entreprise.
     * @return List of personne contact.
     */
    @RequestMapping(value = "/contact/byEntreprise/{id}", method = RequestMethod.GET)
    public Object algoPlanning(@PathVariable String id) {

        try {
            
            return pcm.getPersonContactForACompany(Integer.parseInt(id));
            
        } catch (Exception ex) {
            
            return ExceptionHandler.handle(ex);
            
        }
    }
    
}
