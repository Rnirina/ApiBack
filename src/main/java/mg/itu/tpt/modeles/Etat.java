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
public class Etat {
    private int idEtat;
    private int etat;

    public Etat() {
    }

    public Etat(int idEtat, int etat) {
        this.idEtat = idEtat;
        this.etat = etat;
    }

    public Etat(int etat) {
        this.etat = etat;
    }

    public int getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(int idEtat) {
        this.idEtat = idEtat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}
