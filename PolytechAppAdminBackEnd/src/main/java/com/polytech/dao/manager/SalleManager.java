/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Salle;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Epulapp
 */
public class SalleManager {
      public SessionManager manager;
    
    public SalleManager(SessionManager manager) {
        this.manager = manager;
    }
    
    public List<Salle> getAllSalle() {
        
        Query query = manager.getSession().createQuery("from Salle");
        
        return query.list();
    }
    
    public Salle getSalleById(int id) {
        
        return (Salle) manager.getSession().get(Salle.class, id);
        
    }
    
    public Boolean deleteSalleById(int id) {
        
        Session sess = manager.getSession();
        Salle e = (Salle) sess.get(Salle.class, id);
        sess.delete(e);
        return true;
        
    }
}
