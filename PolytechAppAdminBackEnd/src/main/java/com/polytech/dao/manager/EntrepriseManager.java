package com.polytech.dao.manager;

import com.polytech.dao.Entreprise;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class EntrepriseManager {

    public List<Entreprise> getAllEnterprise() throws Exception {

        Session session = SessionManager.openSession();

        try {

            Query query = session.createQuery("from Entreprise");
            List<Entreprise> list = query.list();
            return list;
            
        } catch(Exception e) {
            
            throw e;
            
        } finally {

            session.close();

        }

    }

    public Entreprise getEnterpriseById(int id) throws Exception {
        
        Session session = SessionManager.openSession();
        
        try {

            Entreprise e = (Entreprise) session.get(Entreprise.class, id);
            
            if (e == null) {
                throw new NullPointerException();
            }
            
            return e;
            
        } catch(Exception e) {
            
            throw e;
            
        } finally {
            
            session.close();
            
        }

    }

    public Boolean deleteEnterpriseById(int id) throws Exception {
        
        Session session = SessionManager.openSession();
        
        try {
            
            Entreprise e = (Entreprise) session.get(Entreprise.class, id);
            session.delete(e);
            
        } finally {
            
            session.close();
            
        }
        
        return true;

    }

}
