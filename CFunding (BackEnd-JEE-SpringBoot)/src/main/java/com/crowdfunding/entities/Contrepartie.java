package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
public class Contrepartie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long montant;
    private String titre;
    private String description;
    private Long quantite;
    private Date livraison;
    @ManyToOne
    @JoinColumn(name = "projet" ,nullable = false)
    private Projet projets;
    public Contrepartie() {
    }

    public Contrepartie(int id, Long montant, String titre, String description, Long quantite, Date livraison, Projet projets) {
        this.id = id;
        this.montant = montant;
        this.titre = titre;
        this.description = description;
        this.quantite = quantite;
        this.livraison = livraison;
        this.projets = projets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Date getLivraison() {
        return livraison;
    }

    public void setLivraison(Date livraison) {
        this.livraison = livraison;
    }
    @JsonIgnore
    public Projet getProjets() {
        return projets;
    }
    @JsonSetter
    public void setProjets(Projet projets) {
        this.projets = projets;
    }
}
