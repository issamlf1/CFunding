package com.crowdfunding.metier;

import com.crowdfunding.entities.Donnation;
import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;

import java.util.List;

public interface Donnationmetier {

    public Donnation saveDonnation(Donnation don);
    public List<Donnation> listDonnation();
    public List<Donnation> DonnationByUser(User user);
    public Long DonnationByProjet(Projet pr);
    public Long DonnationNbrProjet(Projet pr);




}
