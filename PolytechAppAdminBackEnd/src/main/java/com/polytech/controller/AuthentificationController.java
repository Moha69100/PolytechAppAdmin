/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.controller;

import com.polytech.dao.manager.AuthenticationManager;
import com.polytech.exception.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public Object authentication(HttpSession session){
        
        try{
            
            session.setAttribute("api_key", authManager.auth("TestUsr","TestPwd"));
            String jsonKey = "{ \"api_key\" : " + session.getAttribute("api_key") + " }";
            return jsonKey;
            
        }catch(Exception e){
            return ExceptionHandler.handle(e);
        }
        
    }
    
}
