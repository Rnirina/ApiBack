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
public class Pari {

    private int idPari;
    private Statut statut;
    private Match match;
    private Utilisateur utilisateur;
    private Double montant;
    private float score;
    private Date date;
    private Participant participant;

    public Pari() {
    }

    public Pari(int idPari, Statut status, Match match, Utilisateur utilisateur, Double montant, float score, Date date, Participant participant) {
        this.idPari = idPari;
        this.statut = status;
        this.match = match;
        this.utilisateur = utilisateur;
        this.montant = montant;
        this.score = score;
        this.date = date;
        this.participant = participant;
    }

    public Pari(int idPari, int status, int match, int utilisateur, Double montant, float score, Date date, int participant) {
        this.idPari = idPari;
        this.statut = new Statut();
        this.statut.setIdStatut(status);
        this.match = new Match();
        this.match.setIdMatch(match);
        this.utilisateur = new Utilisateur();
        this.utilisateur.setIdUtilisateur(utilisateur);
        this.montant = montant;
        this.score = score;
        this.date = date;
        this.participant = new Participant();
        this.participant.setIdParticipant(participant);
    }

    public Pari(int status, int match, int utilisateur, Double montant, float score, Date date, int participant) {
        this.statut = new Statut();
        this.statut.setIdStatut(status);
        this.match = new Match();
        this.match.setIdMatch(match);
        this.utilisateur = new Utilisateur();
        this.utilisateur.setIdUtilisateur(utilisateur);
        this.montant = montant;
        this.score = score;
        this.date = date;
        this.participant = new Participant();
        this.participant.setIdParticipant(participant);
    }

//    public Pari(Statut status, Match match, Utilisateur utilisateur, Double montant, float score, Date date, Participant participant) {
//        this.statut = status;
//        this.match = match;
//        this.utilisateur = utilisateur;
//        this.montant = montant;
//        this.score = score;
//        this.date = date;
//        this.participant = participant;
//    }
    public int getIdPari() {
        return idPari;
    }

    public void setIdPari(int idPari) {
        this.idPari = idPari;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut status) {
        this.statut = status;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

}
