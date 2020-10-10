package com.crowdfunding.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String nomUser;
    private String preUser ;
    @Column(unique = true)
    private String emailUser ;
    private String teleUser;
    private String image;
    private boolean activation ;
    private String password;
    private String role;
    @Column(columnDefinition ="LONGTEXT")
    private String srcimage;
    @OneToMany(mappedBy = "user" , fetch=FetchType.LAZY)
    private Collection<Projet> projets ;
    @OneToMany(mappedBy = "user" ,  fetch=FetchType.LAZY)
    private Collection<Problems> problems ;
    @OneToMany(mappedBy = "donneur" , fetch=FetchType.LAZY)
    private Collection<Donnation> donnations; // un user peut faire plusieurs donnations
    @OneToMany(mappedBy = "user" , fetch=FetchType.LAZY)
    private Collection<Commantaire> commantaires ;

    public User() {
    }

    public User(String nomUser, String preUser, String emailUser, String teleUser, boolean activation, Collection<Projet> projets, Collection<Problems> problems, Collection<Donnation> donnations, Collection<Commantaire> commantaires) {
        this.nomUser = nomUser;
        this.preUser = preUser;
        this.emailUser = emailUser;
        this.teleUser = teleUser;
        this.activation = activation;
        this.projets = projets;
        this.problems = problems;
        this.donnations = donnations;
        this.commantaires = commantaires;
    }

    public User(String nomUser, String preUser, String emailUser, String teleUser, String password, String role) {
        this.nomUser = nomUser;
        this.preUser = preUser;
        this.emailUser = emailUser;
        this.teleUser = teleUser;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPreUser() {
        return preUser;
    }

    public void setPreUser(String preUser) {
        this.preUser = preUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getTeleUser() {
        return teleUser;
    }

    public void setTeleUser(String teleUser) {
        this.teleUser = teleUser;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    @JsonIgnore
    public Collection<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Collection<Projet> projets) {
        this.projets = projets;
    }

    @JsonIgnore
    public Collection<Problems> getProblems() {
        return problems;
    }

    public void setProblems(Collection<Problems> problems) {
        this.problems = problems;
    }

    @JsonIgnore
    public Collection<Donnation> getDonnations() {
        return donnations;
    }

    public void setDonnations(Collection<Donnation> donnations) {
        this.donnations = donnations;
    }
    @JsonIgnore
    public Collection<Commantaire> getCommantaires() {
        return commantaires;
    }

    public void setCommantaires(Collection<Commantaire> commantaires) {
        this.commantaires = commantaires;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSrcimage() {
        return srcimage;
    }

    public void setSrcimage(String srcimage) {
        this.srcimage = srcimage;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nomUser='" + nomUser + '\'' +
                ", preUser='" + preUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", teleUser='" + teleUser + '\'' +
                ", activation=" + activation +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
