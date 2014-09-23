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
    
    public SessionManager manager;
    //Pour faire les requêtes sur les utilisateurs
    public UtilisateurManager userManager;
    //La clé API retournée
    public int APIkey = 0;
    
    public AuthenticationManager(SessionManager manager) {
        this.manager = manager;
    }
    
    /**
     * @Param _user le login transmi dans la requête
     * @Param _password le mot de passe transmi dans la requête
     * @Return la clé API si l'utilisateur est autorisé, 0 sinon
     * Authentification d'un utilisateur
    */
    public int auth(String _user, String _password){
        userManager = new UtilisateurManager(manager);
        List<Utilisateur> users = userManager.getUtilisateurByLogin(_user);
        Utilisateur user = users.get(0);
            if(user.getMdp().equals(_password)){
                BigInteger bigInt = new BigInteger(16, new Random());
                APIkey = bigInt.intValue();
            }
        return APIkey;
    }
}
