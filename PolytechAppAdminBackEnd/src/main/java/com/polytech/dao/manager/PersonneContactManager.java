package com.polytech.dao.manager;

import com.polytech.dao.Personnecontact;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import webadmin.NewHibernateUtil;

/**
 * This class is the database manager for the Personnecontact table.
 * 
 * @author Florian.Courtial
 */
public class PersonneContactManager {
    
     /**
      * This method returns a list of personne contact regarding their entreprise
      * id.
      * 
      * @param entId The id of the entreprise.
      * @return A list of personne contact.
      * @throws Exception 
      */
     public List<Personnecontact> getPersonContactForACompany(int entId) throws Exception {

        SessionFactory sessionFactory = (SessionFactory) NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Personnecontact where entid = " + entId);
            List<Personnecontact> list = query.list();

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
    
}
