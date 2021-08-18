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
public class Opposant {

    private int idOpposant;
    private Match match;
    private Participant adv1;
    private Participant adv2;

    public Opposant() {
    }

    public Opposant(int idOpposant, Match match, Participant adv1, Participant adv2) {
        this.idOpposant = idOpposant;
        this.match = match;
        this.adv1 = adv1;
        this.adv2 = adv2;
    }
    public Opposant(int id, int match, int adv1, int adv2) {
        this.idOpposant  = id;
        this.adv1 = new Participant();
        this.adv1.setIdParticipant(adv1);
        this.adv2 = new Participant();
        this.adv2.setIdParticipant(adv2);
        this.match = new Match();
        this.match.setIdMatch(match);
    }

    public Opposant(int match, int adv1, int adv2) {
        this.adv1 = new Participant();
        this.adv1.setIdParticipant(adv1);
        this.adv2 = new Participant();
        this.adv2.setIdParticipant(adv2);
        this.match = new Match();
        this.match.setIdMatch(match);
    }

    public int getIdOpposant() {
        return idOpposant;
    }

    public void setIdOpposant(int idOpposant) {
        this.idOpposant = idOpposant;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Participant getAdv1() {
        return adv1;
    }

    public void setAdv1(Participant adv1) {
        this.adv1 = adv1;
    }

    public Participant getAdv2() {
        return adv2;
    }

    public void setAdv2(Participant adv2) {
        this.adv2 = adv2;
    }

}
