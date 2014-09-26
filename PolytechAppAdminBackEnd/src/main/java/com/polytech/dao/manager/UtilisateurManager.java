/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.Utilisateur;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Interrogation des utilisateurs
 * @author Epulapp
 */
public class UtilisateurManager {
    Session session = SessionManager.openSession();
    
    public UtilisateurManager() {
        
    }
    
    public Utilisateur getUtilisateurManagerById(int id) {
        return (Utilisateur) session.get(Utilisateur.class, id);
    }
    
    //Trouver un utilisateur avec son login
    public List<Utilisateur> getUtilisateurByLogin(String login){
         Query query = session.createQuery("from Utilisateur where login = '" + login + "'");
         return  query.list();
    }
    
}
