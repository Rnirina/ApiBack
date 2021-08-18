/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.modeles;

import java.sql.Date;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class Utilisateur {
    private int idUtilisateur;
    private TypeUtilisateur typeUtilisateur;
    private String nom;
    private String username;
    private Date naissance;
    private String mail;
    private String pass;
    private String contact;
    private Double montantBet;

    public Utilisateur() {
    }

    public Utilisateur(int idUtilisateur, TypeUtilisateur typeUtilisateur, String nom, String username, Date naissance, String mail, String pass, String contact, Double montantBet) {
        this.idUtilisateur = idUtilisateur;
        this.typeUtilisateur = typeUtilisateur;
        this.nom = nom;
        this.username = username;
        this.naissance = naissance;
        this.mail = mail;
        this.pass = pass;
        this.contact = contact;
        this.montantBet = montantBet;
    }
    public Utilisateur(int idUtilisateur, int typeUtilisateur, String nom, String username, Date naissance, String mail, String pass, String contact, Double montantBet) {
        this.idUtilisateur = idUtilisateur;
        this.typeUtilisateur = new TypeUtilisateur();this.typeUtilisateur.setIdTypeUtilisateur(typeUtilisateur);
        this.nom = nom;
        this.username = username;
        this.naissance = naissance;
        this.mail = mail;
        this.pass = pass;
        this.contact = contact;
        this.montantBet = montantBet;
    }
    public Utilisateur(int typeUtilisateur, String nom, String username, Date naissance, String mail, String pass, String contact, Double montantBet) {
        this.typeUtilisateur = new TypeUtilisateur();this.typeUtilisateur.setIdTypeUtilisateur(typeUtilisateur);
        this.nom = nom;
        this.username = username;
        this.naissance = naissance;
        this.mail = mail;
        this.pass = pass;
        this.contact = contact;
        this.montantBet = montantBet;
    }

//    public Utilisateur(TypeUtilisateur typeUtilisateur, String nom, String username, Date naissance, String mail, String pass, String contact, Double montantBet) {
//        this.typeUtilisateur = typeUtilisateur;
//        this.nom = nom;
//        this.username = username;
//        this.naissance = naissance;
//        this.mail = mail;
//        this.pass = pass;
//        this.contact = contact;
//        this.montantBet = montantBet;
//    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Double getMontantBet() {
        return montantBet;
    }

    public void setMontantBet(Double montantBet) {
        this.montantBet = montantBet;
    }
    
    
}
