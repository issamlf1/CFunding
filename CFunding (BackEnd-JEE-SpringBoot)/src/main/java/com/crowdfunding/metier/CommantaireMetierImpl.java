package com.crowdfunding.metier;


import com.crowdfunding.dao.CommantaireRepository;
import com.crowdfunding.entities.Commantaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommantaireMetierImpl implements CommantaireMetier{

    @Autowired
    private CommantaireRepository commantaireRepository;
    @Override
    public Commantaire addComm(Commantaire com) {
        return commantaireRepository.save(com);
    }

    @Override
    public List<Commantaire> listComm() {
        return commantaireRepository.findAll();
    }
}
