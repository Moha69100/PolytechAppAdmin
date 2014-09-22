/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.Utilisateur;

/**
 * Interrogation des utilisateurs
 * @author Epulapp
 */
public class UtilisateurManager {
    public SessionManager manager;
    
    public UtilisateurManager(SessionManager manager) {
        this.manager = manager;
    }
    
    public Utilisateur getUtilisateurManagerById(int id) {
        return (Utilisateur) manager.getSession().get(Utilisateur.class, id);
    }
    
    //Trouver un utilisateur avec son login
    public Utilisateur getUtilisateurByLogin(String login){
        return null;
    }
    
}
