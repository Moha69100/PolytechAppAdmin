package com.polytech.dao.manager;

import com.polytech.dao.Entreprise;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import webadmin.NewHibernateUtil;

public class EntrepriseManager {

    public List<Entreprise> getAllEnterprise() throws Exception {

        //Session session = SessionManager.openSession();
        SessionFactory sessionFactory = (SessionFactory) NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Entreprise");
            List<Entreprise> list = query.list();

            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {

           session.close();
        }

    }

    public Entreprise getEnterpriseById(int id) throws Exception {

        SessionFactory sessionFactory = (SessionFactory) NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Entreprise e = (Entreprise) session.get(Entreprise.class, id);

            tx.commit();
            return e;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e; // or display error message
        } finally {
            session.close();
        }
        

    }

    public Boolean deleteEnterpriseById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Entreprise e = (Entreprise) session.get(Entreprise.class, id);
            session.delete(e);

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

    public Boolean addEntreprise(Entreprise ent) throws Exception {

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
     * UPDATE A company INTO DATABASE
     *
     * @param ent
     * @return
     * @throws Exception
     */
    public boolean updateEntreprise(Entreprise ent) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(ent);
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
