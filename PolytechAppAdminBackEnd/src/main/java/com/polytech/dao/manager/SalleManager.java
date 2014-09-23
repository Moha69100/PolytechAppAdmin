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

    public List<Salle> getAllSalle() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from Salle");
            return query.list();

        } finally {

            session.close();

        }

    }

    public Salle getSalleById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            return (Salle) session.get(Salle.class, id);

        } finally {

            session.close();

        }

    }

    public Boolean deleteSalleById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            Salle e = (Salle) session.get(Salle.class, id);
            session.delete(e);
            return true;

        } finally {

            session.close();

        }

    }

    public Boolean addSalle(Salle salle) throws Exception {

        Session session = SessionManager.openSession();
        try {
            session.save(salle);
            return true;

        } finally {

            session.close();

        }
    }

}
