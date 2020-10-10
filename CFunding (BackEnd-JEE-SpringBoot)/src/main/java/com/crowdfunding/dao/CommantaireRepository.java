package com.crowdfunding.dao;

import com.crowdfunding.entities.Commantaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommantaireRepository extends JpaRepository<Commantaire , Integer> {
}
