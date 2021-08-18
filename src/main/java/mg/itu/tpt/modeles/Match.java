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
public class Match {

    private int idMatch;
    private TypeMatch typeMatch;
    private Etat etat;
    private Date date;
    private String image;
    public Match() {
    }

    public Match(int idMatch, TypeMatch typeMatch, Etat etat, Date date, String image) {
        this.idMatch = idMatch;
        this.typeMatch = typeMatch;
        this.etat = etat;
        this.date = date;
        this.image = image;
    }

    public Match(int id, int type, int etat, Date date, String image) {
        this.typeMatch = new TypeMatch();
        this.typeMatch.setIdTypeMatch(type);
        this.etat = new Etat();
        this.etat.setIdEtat(etat);
        this.date = date;
        this.image = image;
    }
//    public Match(TypeMatch typeMatch, Etat etat, Date date, String image) {
//        this.typeMatch = typeMatch;
//        this.etat = etat;
//        this.date = date;
//        this.image = image;
//    }

    public Match(int type, int etat, Date date, String image) {
        this.typeMatch = new TypeMatch();
        this.typeMatch.setIdTypeMatch(type);
        this.etat = new Etat();
        this.etat.setIdEtat(etat);
        this.date = date;
        this.image = image;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public TypeMatch getTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(TypeMatch typeMatch) {
        this.typeMatch = typeMatch;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
