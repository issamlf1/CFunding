package com.crowdfunding.metier;

import com.crowdfunding.dao.ProblemsRepository;
import com.crowdfunding.entities.Problems;
import com.crowdfunding.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemsMetierImpl implements ProblemsMetier {

    @Autowired
    private ProblemsRepository problemsRepository;

    @Override
    public Problems addProblem(Problems pr) {
        return problemsRepository.save(pr);
    }

    @Override
    public List<Problems> listProblems() {
        return problemsRepository.findAll();
    }

    @Override
    public int problemByProject(Projet pr) {
        System.err.println("problemByProject====>"+pr.getTitre());
        List<Problems> problems = listProblems();
        System.err.println("List<Problems>====>"+problems);
        int NbrProb = 0 ;
        for (Problems prob: problems) {
            if(prob.getProjet().getTitre().equals(pr.getTitre()))
                NbrProb++;
        }
        return NbrProb;
    }
}
