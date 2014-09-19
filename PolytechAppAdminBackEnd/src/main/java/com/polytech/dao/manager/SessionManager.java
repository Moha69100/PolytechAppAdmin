/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import webadmin.NewHibernateUtil;

/**
 *
 * @author Florian Courtial
 */
public final class SessionManager {

    private SessionFactory sessionFactory;

    public SessionManager() {

        sessionFactory = (SessionFactory) NewHibernateUtil.getSessionFactory();

    }
    
    public Session getSession() {
        
        return sessionFactory.openSession();
        
    }
}
