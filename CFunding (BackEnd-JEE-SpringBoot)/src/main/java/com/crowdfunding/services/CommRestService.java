package com.crowdfunding.services;
import com.crowdfunding.entities.Commantaire;
import com.crowdfunding.metier.CommantaireMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class CommRestService {


    @Autowired
    private CommantaireMetier commantaireMetier ;

    @RequestMapping(value = "/comm" , method = RequestMethod.POST)
    public Commantaire addComm(@RequestBody Commantaire com) {
        return commantaireMetier.addComm(com);
    }

    @RequestMapping(value = "/comm" , method = RequestMethod.GET)
    public List<Commantaire> listComm() {
        return commantaireMetier.listComm();
    }




}
