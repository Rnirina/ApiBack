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
public class TypeMatch {

    private int idTypeMatch;
    private Categorie categorie;
    private String nom;
    private String image;

    public TypeMatch() {
    }

    public TypeMatch(int idTypeMatch, Categorie categorie, String nom, String image) {
        this.idTypeMatch = idTypeMatch;
        this.categorie = categorie;
        this.nom = nom;
        this.image = image;
    }

    public TypeMatch(int idTypeMatch, int categorie, String nom, String image) {
        this.idTypeMatch = idTypeMatch;
        this.categorie = new Categorie();
        this.categorie.setIdCategorie(categorie);
        this.nom = nom;
        this.image = image;
    }

    public TypeMatch(int categorie, String nom, String image) {
        this.categorie = new Categorie();
        this.categorie.setIdCategorie(categorie);
        this.nom = nom;
        this.image = image;
    }

//    public TypeMatch(Categorie categorie, String nom, String image) {
//        this.categorie = categorie;
//        this.nom = nom;
//        this.image = image;
//    }
    public int getIdTypeMatch() {
        return idTypeMatch;
    }

    public void setIdTypeMatch(int idTypeMatch) {
        this.idTypeMatch = idTypeMatch;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
