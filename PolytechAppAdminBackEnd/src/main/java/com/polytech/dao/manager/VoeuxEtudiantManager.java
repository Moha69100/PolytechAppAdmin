/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.VoeuxEtudiant;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class VoeuxEtudiantManager {
      public List<VoeuxEtudiant> getAllVoeuxEtudiant() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from VoeuxEtudiant");
            List<VoeuxEtudiant> list = query.list();
            return list;

        } catch (Exception e) {

            throw e;

        } finally {

            session.close();

        }

    }

    public VoeuxEtudiant getVoeuxEtudiantById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            VoeuxEtudiant e = (VoeuxEtudiant) session.get(VoeuxEtudiant.class, id);

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

    public Boolean deleteVoeuxEtudiantById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            VoeuxEtudiant e = (VoeuxEtudiant) session.get(VoeuxEtudiant.class, id);
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

    public Boolean addVoeuxEtudiant(VoeuxEtudiant ent) throws Exception {

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
    
    /**
     * UPDATE A VoeuxEtudiant INTO DATABASE
     * @param voeuxEtu
     * @return
     * @throws Exception 
     */
    public boolean updateVoeuxEtudiant(VoeuxEtudiant voeuxEtu) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(voeuxEtu);
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
