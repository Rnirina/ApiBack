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
public class TypeUtilisateur {
    private int idTypeUtilisateur;
    private String nom;

    public TypeUtilisateur() {
    }

    public TypeUtilisateur(int idTypeUtilisateur, String nom) {
        this.idTypeUtilisateur = idTypeUtilisateur;
        this.nom = nom;
    }

    public TypeUtilisateur(String nom) {
        this.nom = nom;
    }

    public int getIdTypeUtilisateur() {
        return idTypeUtilisateur;
    }

    public void setIdTypeUtilisateur(int idTypeUtilisateur) {
        this.idTypeUtilisateur = idTypeUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
