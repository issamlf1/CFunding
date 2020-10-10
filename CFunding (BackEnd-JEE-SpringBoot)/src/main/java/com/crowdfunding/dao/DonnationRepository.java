package com.crowdfunding.dao;

import com.crowdfunding.entities.Donnation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonnationRepository extends JpaRepository<Donnation , Integer> {
}
