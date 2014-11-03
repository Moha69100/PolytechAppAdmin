/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.OffreAlternance;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class OffreAlternanceManager {
    
   public List<OffreAlternance> getAllOffresAlternance() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from offrealternance");
            List<OffreAlternance> list = query.list();
            return list;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }

    }

    public OffreAlternance getOffreAlternanceById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            OffreAlternance o = (OffreAlternance) session.get(OffreAlternance.class, id);

            if (o == null) {
                throw new NullPointerException();
            }

            return o;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }

    }

    public Boolean deleteOffreAlternanceById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            OffreAlternance o = (OffreAlternance) session.get(OffreAlternance.class, id);
            session.delete(o);

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

    public Boolean addOffreAlternance(OffreAlternance offre) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(offre);

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

    /**
     * UPDATE An alternship position
     *
     * @param offre
     * @return
     * @throws Exception
     */
    public boolean updateOffreAlternance(OffreAlternance offre) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(offre);
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
