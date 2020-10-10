package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class Projet implements Serializable  {

    @Id
    private String titre;
    private String description;
    private Long montant;
    private Long montantActuel;
    private String type;

    private int jourRestant ;
    private String categorie;
    private String image;
    private boolean etat;
    @Column(columnDefinition ="LONGTEXT")
    private String srcimage;
    private int NbrProb ; // ***********
    private Long duree ; // en jour
    private Date dateCre;
    private Date datFin;
    @OneToMany(mappedBy = "projets" , fetch= FetchType.LAZY)
    private Collection<Contrepartie> contreparties ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;
    @OneToMany(mappedBy = "projet" , fetch=FetchType.LAZY)
    private Collection<Problems> problems ; // un projet peut avoir plusieurs probl
    @OneToMany(mappedBy = "projet" , fetch=FetchType.LAZY)
    private Collection<Donnation> donnations ;
    @OneToMany(mappedBy = "projet" , fetch=FetchType.LAZY)
    private Collection<Commantaire> commantaires ; // un projet peut avoir pls comm

   Projet(){

   }

    public Projet(String titre, String description, Long montant, Long montantActuel, String type, String categorie, String image, boolean etat, Long duree, Date dateCre, Date datFin, User user) {
        this.titre = titre;
        this.description = description;
        this.montant = montant;
        this.montantActuel = montantActuel;
        this.type = type;
        this.categorie = categorie;
        this.image = image;
        this.etat = etat;
        this.duree = duree;
        this.dateCre = dateCre;
        this.datFin = datFin;
        this.user = user;
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

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Long getMontantActuel() {
        return montantActuel;
    }

    public void setMontantActuel(Long montantActuel) {
        this.montantActuel = montantActuel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public Date getDateCre() {
        return dateCre;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @JsonIgnore
    public Collection<Problems> getProblems() {
        return problems;
    }
    @JsonSetter
    public void setProblems(Collection<Problems> problems) {
        this.problems = problems;
    }
    @JsonIgnore
    public Collection<Donnation> getDonnations() {
        return donnations;
    }
    @JsonSetter
    public void setDonnations(Collection<Donnation> donnations) {
        this.donnations = donnations;
    }

    public Collection<Commantaire> getCommantaires() {
        return commantaires;
    }

    public void setCommantaires(Collection<Commantaire> commantaires) {
        this.commantaires = commantaires;
    }

    public Collection<Contrepartie> getContreparties() {
        return contreparties;
    }
    @JsonSetter
    public void setContreparties(Collection<Contrepartie> contreparties) {
        this.contreparties = contreparties;
    }

    public int getNbrProb() {
        return NbrProb;
    }

    public void setNbrProb(int nbrProb) {
        NbrProb = nbrProb;
    }

    public Date getDatFin() {
        return datFin;
    }

    public void setDatFin(Date datFin) {
        this.datFin = datFin;
    }
    @Override
    public String toString() {
        return "Projet{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", montant=" + montant +
                ", montantActuel=" + montantActuel +
                ", type='" + type + '\'' +
                ", categorie='" + categorie + '\'' +
                ", image='" + image + '\'' +
                ", etat=" + etat +
                ", duree=" + duree +
                ", dateCre=" + dateCre +
                '}';
    }

    public String getSrcimage() {
        return srcimage;
    }

    public void setSrcimage(String srcimage) {
        this.srcimage = srcimage;
    }

    public int getJourRestant() {
        return jourRestant;
    }

    public void setJourRestant(int jourRestant) {
        this.jourRestant = jourRestant;
    }


}
