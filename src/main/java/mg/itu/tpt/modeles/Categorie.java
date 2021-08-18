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
public class Categorie {
    private int idCategorie;
    private String nom;
    private String image;

    public Categorie() {
    }

    public Categorie(int idCategorie, String nom, String image) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.image = image;
    }

    public Categorie(String nom, String image) {
        this.nom = nom;
        this.image = image;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
