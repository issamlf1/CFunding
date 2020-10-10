package com.crowdfunding.dao;

import com.crowdfunding.entities.Problems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemsRepository extends JpaRepository<Problems,Integer> {
}
