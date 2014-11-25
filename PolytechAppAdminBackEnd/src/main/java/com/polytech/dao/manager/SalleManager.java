/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Salle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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

        }catch(Exception e){
            throw(e);
        } finally {

            session.close();

        }

    }

    public Salle getSalleById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {
            
            Salle s = (Salle) session.get(Salle.class, id);
            return s;

        }catch(Exception e){
            throw(e);
        } finally {

            session.close();

        }

    }

    public Boolean deleteSalleById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Salle e = (Salle) session.get(Salle.class, id);
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

    public Boolean addSalle(Salle salle) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(salle);

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
     * @param salle
     * @return
     * @throws Exception 
     */
    public boolean updateSalle(Salle salle) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println("Update de la salle :" + salle.getId() + " libelle: " + salle.getLibelle() + " // loc : " + salle.getLocalisation());
            session.update(salle);
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
