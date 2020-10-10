package com.crowdfunding.metier;

import com.crowdfunding.entities.Commantaire;

import java.util.List;

public interface CommantaireMetier {

    public Commantaire addComm(Commantaire com);
    public List<Commantaire> listComm();
}
