package com.crowdfunding.metier;

import com.crowdfunding.dao.ProjetRepository;
import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class ProjetMetierImpl implements ProjetMetier {

    @Autowired
    private ProjetRepository projetRepository ;
    @Autowired
    private Donnationmetier donnationmetier;
    @Autowired
    private ProblemsMetier problemsMetier;
    @Override
    public Projet saveProjet(Projet pr) {
//        List<Projet> list = projetRepository.findAll();
//        for (int i=0 ; i<list.size() ; i++){
//            if(list.get(i).getTitre().equals(pr.getTitre())){
//                return null;
//            }
//        }

        pr.setDateCre(new Date());
        pr.setNbrProb(0);
        return projetRepository.save(pr);
    }

    @Override
    public List<Projet> listProjets() {
        return projetRepository.findAll();
    }

    @Override
    public List<Projet> projetsByUser(User user) {
        List<Projet> list = projetRepository.findAll();
        for (int i=0 ; i<list.size() ; i++){
            if(list.get(i).getUser().getIdUser()!=user.getIdUser()){
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public Projet updateProjet(Projet pr) {
        return pr;
    }

    @Override
    public void deleteProjet(Projet pr) {
        projetRepository.delete(pr);
    }

    @Override
    public Projet oneProjet(Projet pr) {
        return projetRepository.findById(pr.getTitre()).get();
    }

    @Override
    public void checkProjects() {
        List<Projet> list = listProjets();
        for (Projet pr: list) {
            try {
                pr.setNbrProb(problemsMetier.problemByProject(pr));
                projetRepository.save(pr);
            }catch (Exception e){}
            try {
                Long montantActu = donnationmetier.DonnationByProjet(pr);
                System.err.println("projet--->"+pr.getTitre()+"------"+montantActu);
                pr.setMontantActuel(montantActu);
                projetRepository.save(pr);
            }catch (Exception e){}
            if (pr.getMontantActuel() > pr.getMontant()){
                System.err.println("tMontantActuel() "+pr.getMontantActuel());
                System.err.println("getMontant() "+pr.getMontant());
                pr.setEtat(true);
                projetRepository.save(pr);
            }
            try {
                pr.setJourRestant((int) ChronoUnit.DAYS.between(pr.getDateCre().toInstant(), pr.getDatFin().toInstant()));
                if(pr.getJourRestant() == 0){
                    pr.setEtat(true);
                }
                projetRepository.save(pr);

            }catch (Exception e){

            }

        }
    }

    @Override
    public List<Projet> getProjectbyfiltreOne(String titre, boolean etat) {
        return projetRepository.getProjetByfilterOne(titre,etat) ;
    }

    @Override
    public List<Projet> getProjectbyfiltreTwo(String categorie ,String titre, boolean etat) {
        return projetRepository.getProjetByfilterTwo(categorie,titre,etat);
    }


}
