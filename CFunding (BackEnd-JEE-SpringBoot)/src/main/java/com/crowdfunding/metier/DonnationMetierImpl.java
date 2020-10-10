package com.crowdfunding.metier;

import com.crowdfunding.dao.DonnationRepository;
import com.crowdfunding.entities.Donnation;
import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonnationMetierImpl implements Donnationmetier {

    @Autowired
    private DonnationRepository donnationRepository ;
    @Override
    public Donnation saveDonnation(Donnation don) {
        return donnationRepository.save(don);
    }

    @Override
    public List<Donnation> listDonnation() {
        return donnationRepository.findAll();
    }

    @Override
    public List<Donnation> DonnationByUser(User user) {
        List<Donnation> donn = donnationRepository.findAll();
        System.err.println(donn);
        for (int i = 0 ; i< donn.size() ; i++){
           if(donn.get(i).getDonneur().getIdUser() != user.getIdUser()) {
               donn.remove(i);
               System.err.println("yes remove!!");
           }

        }
        System.err.println(donn);
        return donn;
    }

    @Override
    public Long DonnationByProjet(Projet pr) {
        List<Donnation> donnationList = listDonnation();
        Long som = 0L ;

        for (Donnation var : donnationList)
        {
            if (var.getProjet().getTitre().equals(pr.getTitre()))
                som += var.getMontant();
        }
        System.err.println("DonnationByProjet projet--->"+pr.getTitre()+"------"+som);

        return som;
    }

    @Override
    public Long DonnationNbrProjet(Projet pr) {
        List<Donnation> donnationList = listDonnation();
        Long som = 0L ;

        for (Donnation var : donnationList)
        {
            if (var.getProjet().getTitre().equals(pr.getTitre()))
                som ++;
        }
        System.err.println("DonnationByProjet projet--->"+pr.getTitre()+"------"+som);

        return som;
    }
}
