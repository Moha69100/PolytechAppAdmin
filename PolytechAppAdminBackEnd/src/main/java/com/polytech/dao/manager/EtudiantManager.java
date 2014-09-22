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

/**
 *
 * @author Epulapp
 */
public class EtudiantManager {

    public SessionManager manager;

    public EtudiantManager() {
        this.manager = manager;
    }

    public List<Etudiant> getList() {

        Query query = manager.openSession().createQuery("from Etudiant");

        return query.list();
    }

}
