package com.crowdfunding.metier;

import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;


import java.util.List;

public interface ProjetMetier {

    public Projet saveProjet(Projet pr);
    public List<Projet> listProjets();
    public List<Projet> projetsByUser(User user);
    public Projet updateProjet(Projet pr);
    public void deleteProjet(Projet pr);
    public Projet oneProjet(Projet pr);
    public void checkProjects();
    public List<Projet> getProjectbyfiltreOne(String titre, boolean etat );
    public List<Projet> getProjectbyfiltreTwo(String categorie ,String titre, boolean etat );


}
