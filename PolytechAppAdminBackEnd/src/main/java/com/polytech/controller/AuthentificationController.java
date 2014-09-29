/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.controller;

import com.polytech.dao.manager.AuthenticationManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Epulapp
 */
@ComponentScan
@RestController
public class AuthentificationController {
    
     AuthenticationManager authManager = new AuthenticationManager();
        //AUTHENTIFICATION
      //Page d'authentification
    /*
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public int authentication(@PathVariable String user, @PathVariable String password){
        return authManager.auth(user,password);
    }*/
    
    
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authentication(){
        String retour;
        try{
            retour = "Utilisateur : TestUsr ; clé API : " + authManager.auth("TestUsr","TestPwd");
        }catch(Exception e){
            retour = "Erreur : "+e.getMessage();
        }
        
        return retour;
    }
}
