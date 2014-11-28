/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.Utilisateur;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Interrogation des utilisateurs
 * @author Epulapp
 */
public class UtilisateurManager {
    //Session session = SessionManager.openSession();
    
    public UtilisateurManager() {
        
    }
    
    public Utilisateur getUtilisateurById(int id) throws Exception{
        
        Session session = SessionManager.openSession();

        try {

            Utilisateur user = (Utilisateur) session.get(Utilisateur.class, id);;

            if (user == null) {
                throw new NullPointerException();
            }

            return user;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }
    }
    
    //Trouver un utilisateur avec son login
    public List<Utilisateur> getUtilisateurByLogin(String login) throws Exception{
        Session session = SessionManager.openSession();

        try {

           Query query = session.createQuery("from Utilisateur where login = '" + login + "'");
           List<Utilisateur> userList = query.list();
            if (userList.isEmpty() || userList == null) {
                throw new NullPointerException();
            }

            return userList;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }
        
    }
    
}
