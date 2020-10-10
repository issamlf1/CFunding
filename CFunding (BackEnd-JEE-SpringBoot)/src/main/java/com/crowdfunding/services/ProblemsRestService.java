package com.crowdfunding.services;


import com.crowdfunding.entities.Problems;
import com.crowdfunding.metier.ProblemsMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProblemsRestService {

    @Autowired
    private ProblemsMetier problemsMetier;

    @RequestMapping(value = "/problems" , method = RequestMethod.POST)
    public Problems addProblem(@RequestBody Problems pr) {
        return problemsMetier.addProblem(pr);
    }
    @RequestMapping(value = "/problems" , method = RequestMethod.GET)
    public List<Problems> listProblems() {
        return problemsMetier.listProblems();
    }


}
