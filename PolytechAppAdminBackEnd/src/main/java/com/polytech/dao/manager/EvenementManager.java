/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Evenement;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class EvenementManager {

    public List<Evenement> getAllEvenement() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from Evenement");
            return query.list();

        } finally {

            session.close();

        }

    }

    public Evenement getEvenementById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            return (Evenement) session.get(Evenement.class, id);

        } finally {

            session.close();

        }

    }

    public Boolean deleteEvenementById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Evenement e = (Evenement) session.get(Evenement.class, id);
            session.delete(e);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e; // or display error message
        } finally {
            session.close();
        }
        return true;

    }

    public Boolean addEvenement(Evenement evenement) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(evenement);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e; // or display error message
        } finally {
            session.close();
        }
        return true;
    }

    /**
     * UPDATE A STUDENT INTO DATABASE
     *
     * @param evenement
     * @return
     * @throws Exception
     */
    public boolean updateEvenement(Evenement evenement) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(evenement);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e; // or display error message
        } finally {
            session.close();
        }

        return true;
    }

}
