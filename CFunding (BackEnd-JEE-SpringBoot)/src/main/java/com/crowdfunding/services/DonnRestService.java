package com.crowdfunding.services;
import com.crowdfunding.entities.Donnation;

import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;
import com.crowdfunding.metier.Donnationmetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DonnRestService {


    @RequestMapping(value = "/donnUser" , method = RequestMethod.POST)
    public List<Donnation> DonnationByUser(@RequestBody User user) {
        return donnationMetier.DonnationByUser(user);
    }

    @Autowired
    private Donnationmetier donnationMetier ;

    @RequestMapping(value = "/donn" , method = RequestMethod.POST)
    public Donnation saveDonnation(@RequestBody Donnation don) {
        don.setDateDonn(new Date());
        return donnationMetier.saveDonnation(don);
    }

    @RequestMapping(value = "/donn" , method = RequestMethod.GET)
    public List<Donnation> listDonnation() {
        return donnationMetier.listDonnation();
    }

    @RequestMapping(value = "/donnProj" , method = RequestMethod.POST)
    public Long DonnationByProjet(@RequestBody Projet pr) {
        return donnationMetier.DonnationByProjet(pr);
    }

    @RequestMapping(value = "/donnNbrProj" , method = RequestMethod.POST)
    public Long DonnationNbrProjet(@RequestBody Projet pr) {
        return donnationMetier.DonnationNbrProjet(pr);
    }

}
