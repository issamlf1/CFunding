package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Commantaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComm ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;
    private String commantaire ;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet ;

    Commantaire(){

    }

    public Commantaire(User user, String commantaire, Projet projet) {
        this.user = user;
        this.commantaire = commantaire;
        this.projet = projet;
    }

    public int getIdComm() {
        return idComm;
    }

    public void setIdComm(int idComm) {
        this.idComm = idComm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommantaire() {
        return commantaire;
    }

    public void setCpmmantaire(String commantaire) {
        this.commantaire = commantaire;
    }

    public Projet getProjet() {
        return projet;
    }
    @JsonSetter
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
