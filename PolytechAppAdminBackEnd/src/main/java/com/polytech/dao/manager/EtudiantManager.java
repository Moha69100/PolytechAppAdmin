/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Etudiant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 *
 * @author Epulapp
 */
public class EtudiantManager {

    /**
     * Retrieve all students in database
     *
     * @return
     */
    public List<Etudiant> getAllEtudiant() throws Exception {

        Session session = SessionManager.openSession();

        try {
            Query query = session.createQuery("from Etudiant");
            return query.list();
            

        }catch(Exception e){
            throw(e);
        }finally {
            session.close();

        }

    }

    /**
     * Delete one etudiant from database
     *
     * @param etu_id
     * @throws Exception
     */
    public boolean deleteEtudiantById(int etu_id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Etudiant ex = (Etudiant) session.get(Etudiant.class, etu_id);
            session.delete(ex);

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
     *
     * @param id : id etudiant
     * @return etudiant avec id correspondant
     */
    public Etudiant getEtudiantByID(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {
            return (Etudiant) session.get(Etudiant.class, id);

        } catch(Exception e){
            throw(e);
        }finally {
            session.close();

        }
    }

    /**
     * ADD A STUDENT INTO DATABASE
     *
     * @param etu
     * @return
     * @throws Exception
     */
    public boolean addEtudiant(Etudiant etu) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(etu);

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
     * UPDATE A STUDENT INTO DATABASE
     *
     * @param etu
     * @return
     * @throws Exception
     */
    public boolean updateEtudiant(Etudiant etu) throws Exception {

        
        Session session = SessionManager.openSession();
        Transaction tx = null;

        System.out.println("ATTENTION A LA DATE DE LUPDATE OU DE LAJOUT  :" + etu.getDatenaissance());
        try {
            tx = session.beginTransaction();
            session.update(etu);

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
