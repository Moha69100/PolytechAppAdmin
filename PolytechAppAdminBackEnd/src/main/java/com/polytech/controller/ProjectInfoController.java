package com.polytech.controller;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Etudiant;
import com.polytech.dao.Salle;
import com.polytech.dao.manager.AuthenticationManager;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.dao.manager.EtudiantManager;
import com.polytech.dao.manager.SalleManager;
import com.polytech.dao.manager.SessionManager;
import com.polytech.exception.ExceptionHandler;
import com.polytech.exception.SuccessHandler;
import com.polytech.model.*;
import com.sun.net.httpserver.Authenticator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@ComponentScan
@RestController
public class ProjectInfoController {

    EtudiantManager etuManager = new EtudiantManager();
    SalleManager salleManager = new SalleManager();
    AuthenticationManager authManager = new AuthenticationManager();
    
    @RequestMapping("/")
    public ProjectInfo hello() {
        return new ProjectInfo("0.1.0", "PolytechAppAdmin");
    }
    
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
