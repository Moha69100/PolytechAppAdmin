/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Etudiant;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class EtudiantManager {

    /**
     * Retrieve all students in database
     * @return 
     */
    public List<Etudiant> getAllEtudiant() throws Exception {
        
        Session session = SessionManager.openSession();
        
        try {
            
            Query query = session.createQuery("from Etudiant");
            return query.list();
            
        } finally {
            
            session.close();
            
        }
        
        
    }

    /**
     * Delete one etudiant from database
     * @param etu_id
     * @throws Exception 
     */
    public Boolean deleteEtudiantById(int etu_id) throws Exception {
        
        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Etudiant ex = (Etudiant) session.get(Etudiant.class, etu_id);
            session.delete(ex);

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
     *
     * @param id : id etudiant
     * @return etudiant avec id correspondant
     */
    public Etudiant getEtudiantByID(int id) throws Exception  {
        
        Session session = SessionManager.openSession();
        
        try {
            
            return (Etudiant) session.get(Etudiant.class, id);
            
        } finally {
            
            session.close();
            
        }
    }
    
    
    public Boolean addEtudiant(Etudiant etu) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(etu);

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

}
