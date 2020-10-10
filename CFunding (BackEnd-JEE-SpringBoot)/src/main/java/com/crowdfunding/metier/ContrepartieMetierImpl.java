package com.crowdfunding.metier;

import com.crowdfunding.dao.ContrepartieRepository;
import com.crowdfunding.entities.Contrepartie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContrepartieMetierImpl implements ContrepartieMetier{
    @Autowired
    private ContrepartieRepository contrepartieRepository;
    @Override
    public Contrepartie saveContrep(Contrepartie contrepartie) {
        return contrepartieRepository.save(contrepartie);
    }
}
