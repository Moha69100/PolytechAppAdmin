/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Etudiant;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Epulapp
 */
public class EtudiantManager {

    public Session session;

    public EtudiantManager(SessionManager manager) {

        this.session = manager.getSession();

    }

    public List<Etudiant> getList() {

        Query query = session.createQuery("from Etudiant");

        return query.list();
    }

    public void deleteEtudiant(int etu_id) throws Exception {

       
    
            Etudiant ex = (Etudiant) session.get(Etudiant.class, etu_id);
            session.delete(ex);

        
      
    }

}
