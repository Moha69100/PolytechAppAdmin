package com.polytech.dao.manager;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Entreprise;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webadmin.NewHibernateUtil;

public class EntrepriseManager  {

    public SessionManager manager;
    
    public EntrepriseManager(SessionManager manager) {
        this.manager = manager;
    }
    
    public List<Entreprise> getList() {
        
        Query query = manager.getSession().createQuery("from Entreprise");
        
        return query.list();
    }

}
