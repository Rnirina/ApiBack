/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessCategorie;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Categorie;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceCategorie {

    private DbConnect db;
    private AccessCategorie access;
    private PoolConnection pool;

    public ServiceCategorie(PoolConnection pool) throws ClassNotFoundException {
        //this.db = new DbConnect();
        this.pool = pool;
        this.access = new AccessCategorie(this.pool);
    }

    public Categorie getUn(int id) throws ClassNotFoundException, SQLException {
        return this.access.getById(id);
    }

    public ArrayList<Categorie> getTous() throws ClassNotFoundException, SQLException {
        return this.access.getAll();
    }

    public void ajout(String nom, String image) throws ClassNotFoundException, SQLException {
        Categorie nouveau = new Categorie(nom, image);
        this.access.addNew(nouveau);
    }

    public void suppression(int id) throws SQLException {
        this.access.delete(id);
    }

    public void maj(int id, String nom, String image) throws SQLException {
        Categorie maj = new Categorie(id, nom, image);
        this.access.edit(maj);
    }
}
