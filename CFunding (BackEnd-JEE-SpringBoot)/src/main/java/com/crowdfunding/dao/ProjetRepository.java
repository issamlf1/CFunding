package com.crowdfunding.dao;

import com.crowdfunding.entities.Projet;
import com.crowdfunding.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet,String> {
    @Query("select p from Projet p where p.titre like %:x% and p.etat = :y")
    public List<Projet> getProjetByfilterOne(@Param("x")String titre, @Param("y") boolean etat);

    @Query("select p from Projet p where p.titre like %:x% and p.etat = :y  and p.categorie = :z")
    public List<Projet> getProjetByfilterTwo(@Param("z") String categorie ,@Param("x")String titre, @Param("y") boolean etat);

}
