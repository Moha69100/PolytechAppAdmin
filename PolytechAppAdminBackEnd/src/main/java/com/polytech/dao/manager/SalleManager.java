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

    public Session session;

    public SalleManager(SessionManager manager) {
        this.session = manager.getSession();

    }

    public List<Salle> getAllSalle() {

        Query query = session.createQuery("from Salle");

        return query.list();
    }

    public Salle getSalleById(int id) {

        return (Salle) session.get(Salle.class, id);

    }

    public Boolean deleteSalleById(int id) {

        Salle e = (Salle) session.get(Salle.class, id);
        session.delete(e);
        return true;

    }

    public Boolean addSalle(Salle salle) throws Exception {

        session.save(salle);
        return true;
    }

}
