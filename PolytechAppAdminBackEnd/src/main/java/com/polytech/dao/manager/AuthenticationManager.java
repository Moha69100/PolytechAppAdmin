/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.Utilisateur;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Epulapp
 */
public class AuthenticationManager {

    
    public AuthenticationManager() {
        
    }
    
    /**
     * @Param _user le login transmi dans la requête
     * @Param _password le mot de passe transmi dans la requête
     * @Return la clé API si l'utilisateur est autorisé, 0 sinon
     * Authentification d'un utilisateur
    */
    public int auth(String _user, String _password) throws Exception{
        
        UtilisateurManager userManager = new UtilisateurManager();
        int APIkey = 0;
        
        try {
            List<Utilisateur> users = userManager.getUtilisateurByLogin(_user);
            Utilisateur user = users.get(0);
            if(user.getMdp().equals(_password)){
                BigInteger bigInt = new BigInteger(16, new Random());
                APIkey = bigInt.intValue();
            }
        }catch (Exception e){
            throw e;
        }
        
        
        return APIkey;
    }
}
