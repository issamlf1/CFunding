package com.crowdfunding.metier;

import com.crowdfunding.entities.Problems;
import com.crowdfunding.entities.Projet;

import java.util.List;

public interface ProblemsMetier {

    public Problems addProblem(Problems pr);
    public List<Problems> listProblems();
    public int problemByProject(Projet pr);
}
