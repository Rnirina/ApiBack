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
public class Statut {
    private int idStatut;
    private String designation;

    public Statut() {
    }

    public Statut(int idStatut, String designation) {
        this.idStatut = idStatut;
        this.designation = designation;
    }

    public Statut(String designation) {
        this.designation = designation;
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
}
