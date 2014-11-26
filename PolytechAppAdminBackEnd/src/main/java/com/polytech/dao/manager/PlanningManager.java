package com.polytech.dao.manager;

import com.polytech.dao.Entretien;
import com.polytech.dao.Planning;
import com.sun.media.jfxmedia.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Epulapp
 */
public class PlanningManager {

    public List<Planning> getAllPlanning() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from Planning");
            return query.list();

        } catch (Exception e) {
            throw (e);
        } finally {

            session.close();

        }

    }

    public Planning getPlanningById(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            return (Planning) session.get(Planning.class, id);

        } catch (Exception e) {
            throw (e);
        } finally {

            session.close();

        }

    }

    /**
     * Retourne le planning associé à un évènement La base de données permet
     * d'avoir plusieurs entretiens, mais uniquement 1 doit être utilisé La
     * requete est donc être la même que EntretienManager.getEntretienByEvent()
     *
     * @param id
     * @return
     * @throws Exception
     */
//    public List<Object> getPlanningByEvt(int id) throws Exception {
//        Session session = SessionManager.openSession();
//        try {
//            Query query = session.createQuery("from Planning where evtid = " + id + " limit 1");
//            return query.list();
//        } catch(Exception e){
//            throw(e);
//        }finally {
//            session.close();
//        }
//    }
/*
     Modifier requête, qui renvoie un seul planning
     SELECT * FROM planning WHERE planning.id = (
     SELECT MIN(id) FROM planning WHERE evtid = {id}
     )
    
     */
    public List<Planning> getPlanningByEvt(int id) throws Exception {

        Session session = SessionManager.openSession();

        try {

            /*Query query = session.createQuery("from planning WHERE planning.id = (SELECT MIN(planning.id) FROM planning WHERE planning.evtid = " + id + ")");
            //Query query = session.createQuery("from Planning where evtid = " + id);
            return query.list();*/
            // retourner liste entretiens associé au planning

            String sql = "Select * "
             + "from appschema.Planning "
             + "WHERE id = (SELECT MIN(id) FROM appschema.planning WHERE evtid = " + id + ")";
            
            System.out.println("REQUETE SQL : " + sql);

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Planning.class);
            return query.list();
            
        } catch (Exception e) {
            throw (e);
        } finally {

            session.close();

        }

    }

    public Boolean deletePlanningById(int id) throws Exception {

        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Planning e = (Planning) session.get(Planning.class, id);
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

    public Boolean deleteEntretiens(int planningId) {
        Session session = SessionManager.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Planning planning = (Planning) session.load(Planning.class, planningId);
            for ( Entretien entretien : planning.getEntretiens() ) {
                session.delete(entretien);
            }

            // delete() ne supprimme pas les objets, juste les instances de base de données : il faut réinitialiser la collection
            planning.getEntretiens().clear();
            tx.commit();

            return true;
        } catch (Exception e ) {
            tx.rollback();

            Logger.logMsg(Logger.ERROR, e.getMessage());
            return false;
        } finally {
            session.close();
        }

    }

    public Boolean addPlanning(Planning evenement) throws Exception {

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
    public boolean updatePlanning(Planning evenement) throws Exception {

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
