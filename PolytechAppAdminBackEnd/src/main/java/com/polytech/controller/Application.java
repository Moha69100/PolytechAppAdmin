package com.polytech.controller;

import com.polytech.dao.Evenement;
import com.polytech.dao.manager.EvenementManager;
import com.polytech.model.PlanningGenerator;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        String error = "";
        String id = "2";
        
        try {
            EvenementManager evenementManager = new EvenementManager();
            //Evenement evt = null;
            //evt = evenementManager.getEvenementById(Integer.parseInt(id));
            Evenement evt = evenementManager.getEvenementById(Integer.parseInt(id));
            PlanningGenerator planning = new PlanningGenerator(evt, 30);

            planning.generate();
        } catch (Exception ex) {
            error = ex.getMessage();
        }

    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("8192KB");
        factory.setMaxRequestSize("8192KB");
        return factory.createMultipartConfig();
    }
}
