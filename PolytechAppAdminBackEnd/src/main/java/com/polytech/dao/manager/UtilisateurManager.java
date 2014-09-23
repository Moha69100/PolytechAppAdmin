/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.Utilisateur;
import java.util.List;
import org.hibernate.Query;

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
    public List<Utilisateur> getUtilisateurByLogin(String login){
        String hql = "from appschema.utilisateur where user='"+login+"'";
         Query query = manager.getSession().createQuery(hql);
         return  query.list();
    }
    
}
