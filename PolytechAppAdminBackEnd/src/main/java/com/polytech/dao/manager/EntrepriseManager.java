package com.polytech.dao.manager;

import com.polytech.dao.Entreprise;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class EntrepriseManager  {

    public SessionManager manager;
    
    public EntrepriseManager(SessionManager manager) {
        this.manager = manager;
    }
    
    public List<Entreprise> getAllEnterprise() {
        
        Query query = manager.getSession().createQuery("from Entreprise");
        
        return query.list();
    }
    
    public Entreprise getEnterpriseById(int id) {
        
        return (Entreprise) manager.getSession().get(Entreprise.class, id);
        
    }
    
    public Boolean deleteEnterpriseById(int id) {
        
        Session sess = manager.getSession();
        Entreprise e = (Entreprise) sess.get(Entreprise.class, id);
        sess.delete(e);
        return true;
        
    }

}
