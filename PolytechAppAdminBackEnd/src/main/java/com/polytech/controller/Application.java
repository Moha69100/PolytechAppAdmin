package com.polytech.controller;

import com.polytech.dao.Offrealternance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import webadmin.NewHibernateUtil;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        SessionFactory session = (SessionFactory) NewHibernateUtil.getSessionFactory();
        Session session1 = session.openSession();

        Transaction tx = null;

        try {
            tx = session1.beginTransaction();
            Offrealternance offre = new Offrealternance();
            offre.setTitre("He4444 test");
            offre.setEntreprise(null);
            offre.setUrl("he44o");
            
            session1.save(offre);
            
            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
            System.out.println("Insert done");
        }

        System.exit(0);

        

    }
}
