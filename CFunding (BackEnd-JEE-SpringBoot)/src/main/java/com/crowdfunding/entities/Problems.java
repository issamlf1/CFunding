package com.crowdfunding.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Problems implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProb ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;
    private String description;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet ;

    Problems(){
    }

    public Problems(User user, String description, Projet projet) {
        this.user = user;
        this.description = description;
        this.projet = projet;
    }

    public int getIdProb() {
        return idProb;
    }

    public void setIdProb(int idProb) {
        this.idProb = idProb;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "Problems{" +
                "idProb=" + idProb +
                ", user=" + user +
                ", description='" + description + '\'' +
                '}';
    }
}
