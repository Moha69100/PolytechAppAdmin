/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import com.polytech.dao.Entreprise;
import com.polytech.dao.Etudiant;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Epulapp
 */
public class EtudiantManager {

    public SessionManager manager;

    public EtudiantManager(SessionManager manager) {
        this.manager = manager;
    }

    /**
     * List all etudiant in database
     * @return 
     */
    public List<Etudiant> getList() {
        Query query = manager.getSession().createQuery("from Etudiant");
        return query.list();
    }

    /**
     *
     * @param id : id etudiant
     * @return etudiant avec id correspondant
     */
    public Etudiant getEtudiantByID(int id)  {        
        return (Etudiant) manager.getSession().get(Etudiant.class, id);
    }

}
