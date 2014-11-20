package com.polytech.dao.manager;

import com.polytech.dao.Entretien;
import com.polytech.dao.Planning;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Epulapp
 */
public class EntretienManager {

    /**
     * Retrieve all students in database
     *
     * @return
     */
    public List<Entretien> getAllEntretien() throws Exception {

        Session session = SessionManager.openSession();

        try {
            Query query = session.createQuery("from Entretien");
            List<Entretien> result = query.list();
            return result;

        } catch (Exception e) {
            throw (e);
        } finally {
            session.close();

        }

    }

    /**
     * Delete one etudiant from database
     *
     * @param etu_id
     * @throws Exception
     */
    public boolean deleteEntretienById(int etu_id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Entretien ex = (Entretien) session.get(Entretien.class, etu_id);
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
    public Entretien getEntretienByID(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {
            return (Entretien) session.get(Entretien.class, id);

        } catch (Exception e) {
            throw (e);
        } finally {
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
    public boolean addEntretien(Entretien etu) throws Exception {

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
    public boolean updateEntretien(Entretien etu) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
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

    public List<Entretien> getEntretiensByEvent(int id) throws Exception {
        Session session = SessionManager.openSession();

        try {
            PlanningManager planningManager = new PlanningManager();
            List<Planning> planning = planningManager.getPlanningByEvt(id);
            Planning onePlanning = planning.get(0);

            /*String sql = "Select e.* "
                    + "from appschema.Planning p, appschema.entretien e "
                    // remplacer la requete par l'appele par PlanningManager.getPlanningByEvent()
                    + "where p.evtid = :evt_id and p.id = (select distinct id from appschema.planning limit 1) "
                    + "and e.planid=p.id";*/
            
            //Query query = session.createQuery("from appschema.entretien where planid = " + onePlanning.getId());

            //SQLQuery query = session.createSQLQuery(sql);
            //query.addEntity(Entretien.class);
            //query.setParameter("evt_id", id);
            
            String sql = "Select * "
             + "from appschema.entretien "
             + "where planid = " + onePlanning.getId()
             + "ORDER BY horaire ASC";
            
            System.out.println("REQUETE SQL : " + sql);

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Entretien.class);
            return query.list();
            
            //return query.list();

        } catch (Exception e) {
            throw (e);
        } finally {
            session.close();

        }

    }

}
