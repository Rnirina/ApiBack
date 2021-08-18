/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.modeles;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class Participant {

    private int idParticipant;
    private Statut statut;
    private Match match;
    private String nom;
    private float score;
    private String image;

    public Participant() {
    }

    public Participant(int idParticipant, Statut statut, Match match, String nom, float score, String image) {
        this.idParticipant = idParticipant;
        this.statut = statut;
        this.match = match;
        this.nom = nom;
        this.score = score;
        this.image = image;
    }

    public Participant(int idParticipant, int statut, int match, String nom, float score, String image) {
        this.idParticipant = idParticipant;
        this.statut = new Statut();
        this.statut.setIdStatut(statut);
        this.match = new Match();
        this.match.setIdMatch(match);
        this.nom = nom;
        this.score = score;
        this.image = image;
    }

    public Participant(int statut, int match, String nom, float score, String image) {
        this.statut = new Statut();
        this.statut.setIdStatut(statut);
        this.match = new Match();
        this.match.setIdMatch(match);
        this.nom = nom;
        this.score = score;
        this.image = image;
    }

//    public Participant(Statut statut, Match match, String nom, float score, String image) {
//        this.statut = statut;
//        this.match = match;
//        this.nom = nom;
//        this.score = score;
//        this.image = image;
//    }
    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
