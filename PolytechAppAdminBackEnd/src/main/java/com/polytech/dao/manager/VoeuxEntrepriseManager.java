/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.VoeuxEntreprise;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class VoeuxEntrepriseManager {

    public List<VoeuxEntreprise> getAllVoeuxEntreprise() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from VoeuxEntreprise");
            List<VoeuxEntreprise> list = query.list();
            return list;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }

    }

    public VoeuxEntreprise getVoeuxEntrepriseById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            VoeuxEntreprise e = (VoeuxEntreprise) session.get(VoeuxEntreprise.class, id);

            if (e == null) {
                throw new NullPointerException();
            }

            return e;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }

    }

    public Boolean deleteVoeuxEnterpriseById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            VoeuxEntreprise e = (VoeuxEntreprise) session.get(VoeuxEntreprise.class, id);
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

    public Boolean addVoeuxEntreprise(VoeuxEntreprise ent) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(ent);

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
