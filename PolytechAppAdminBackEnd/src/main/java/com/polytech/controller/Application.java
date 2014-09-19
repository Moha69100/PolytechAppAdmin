
package com.polytech.controller;


import com.polytech.dao.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
//        SpringApplication.run(Application.class, args);
        
        SessionFactory session = (SessionFactory) NewHibernateUtil.getSessionFactory();
        Session session1 = session.openSession();

        Transaction tx = null;

        try {
            tx = session1.beginTransaction();
            
            Entreprise e = new Entreprise();
            
            e.setAdresse("31 Rue Pauline Borghese");
            e.setAdresse2("");
            e.setAnneeparticipforum("2014");
            e.setCp("92200");
            e.setEffectif("60");
            e.setNaf("C'est quoi NAF ?");
            e.setNbrapprenti(2);
            e.setOrganisme("CPAM");
            e.setRaison("SASU Société par actions simplifiée à associé unique");
            e.setSiret("48116365700010");
            e.setTel("04 75 58 45 21");
            e.setVille("NEUILLY SUR SEINE");
            e.setEntreprisepresences(new HashSet<Entreprisepresence>());
            e.setEntretiens(new HashSet<Entretien>());
            e.setOffrealternances(new HashSet<Offrealternance>());
            e.setPersonnecontacts(new HashSet<Personnecontact>());
//            e.setId(2);
            
            session1.save(e);
            
            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }

//        
//
//        System.out.println("Insert done");

    }
}
