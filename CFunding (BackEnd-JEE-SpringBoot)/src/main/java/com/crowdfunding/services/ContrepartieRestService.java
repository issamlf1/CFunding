package com.crowdfunding.services;

import com.crowdfunding.entities.Contrepartie;
import com.crowdfunding.metier.ContrepartieMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ContrepartieRestService {
    @Autowired
    private ContrepartieMetier contrepartieMetier;
    @RequestMapping(value = "/contrepartie" , method = RequestMethod.POST)
    public Contrepartie saveContrep(@RequestBody Contrepartie contrepartie) {
        return contrepartieMetier.saveContrep(contrepartie);
    }
}
