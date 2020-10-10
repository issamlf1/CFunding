package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Donnation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDonn ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User donneur ;
    private Long montant ;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet ;


    private Date dateDonn;

    Donnation(){

    }

    public Donnation(User donneur, Long montant, Projet projet) {
        this.donneur = donneur;
        this.montant = montant;
        this.projet = projet;
    }

    public int getIdDonn() {
        return idDonn;
    }

    public void setIdDonn(int idDonn) {
        this.idDonn = idDonn;
    }

    public User getDonneur() {
        return donneur;
    }

    public void setDonneur(User donneur) {
        this.donneur = donneur;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Projet getProjet() {
        return projet;
    }
    @JsonSetter
    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "Donnation{" +
                "idDonn=" + idDonn +
                ", donneur=" + donneur +
                ", montant=" + montant +
                ", projet=" + projet +
                '}';
    }


    public Date getDateDonn() {
        return dateDonn;
    }

    public void setDateDonn(Date dateDonn) {
        this.dateDonn = dateDonn;
    }
}


