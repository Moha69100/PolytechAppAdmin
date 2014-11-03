/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.dao.manager;

import com.polytech.dao.Offrealternance;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class OffreAlternanceManager {
    
   public List<Offrealternance> getAllOffresAlternance() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from offrealternance");
            List<Offrealternance> list = query.list();
            return list;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }

    }

    public Offrealternance getOffreAlternanceById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            Offrealternance o = (Offrealternance) session.get(Offrealternance.class, id);

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

            Offrealternance o = (Offrealternance) session.get(Offrealternance.class, id);
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

    public Boolean addOffreAlternance(Offrealternance offre) throws Exception {

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
    public boolean updateOffreAlternance(Offrealternance offre) throws Exception {

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
