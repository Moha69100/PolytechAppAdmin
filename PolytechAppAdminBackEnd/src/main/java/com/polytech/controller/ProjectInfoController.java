package com.polytech.controller;

import com.polytech.dao.Entreprise;
import com.polytech.dao.manager.SessionManager;
import com.polytech.dao.manager.EntrepriseManager;
import com.polytech.model.*;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller("ProjectInfoController")
@ComponentScan
@RestController
public class ProjectInfoController {

    SessionManager manager = new SessionManager();
    EntrepriseManager dao =  new EntrepriseManager(manager);

    @RequestMapping("/")
    public ProjectInfo hello() {
        return new ProjectInfo("0.1.0", "PolytechAppAdmin");
    }

    // lire les headers
    @RequestMapping(value = "/entreprises", method = RequestMethod.GET)
    public List<Entreprise> allEnterprise() {

        List<Entreprise> entreprises = dao.getList();
        return entreprises;

    }

    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.GET)
    public String oneEnterprise(@PathVariable String id) {
        return "L'entreprise " + id + ".";
    }

    @RequestMapping(value = "/entreprise/{id}", method = RequestMethod.DELETE)
    public String deleteEnterprise(@PathVariable String id) {
        return "Delete l'entreprise " + id + ".";
    }
}
