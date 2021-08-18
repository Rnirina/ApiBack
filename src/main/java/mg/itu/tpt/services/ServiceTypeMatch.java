/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessTypeMatch;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.TypeMatch;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceTypeMatch {
    private DbConnect db;
    private AccessTypeMatch access;
    private PoolConnection pool;
    public ServiceTypeMatch(PoolConnection pool) throws ClassNotFoundException {
        //this.db =new DbConnect();
        this.pool = pool;
        this.access = new AccessTypeMatch(this.pool);
    }
    
    
    public TypeMatch getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<TypeMatch> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int categorie, String nom, String image) throws ClassNotFoundException, SQLException{
        TypeMatch nouveau = new TypeMatch(categorie, nom, image);
        this.access.addNew(nouveau);
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id, int categorie, String nom, String image) throws SQLException, ClassNotFoundException{
        TypeMatch update = new TypeMatch(id, categorie, nom, image);
        this.access.addNew(update);
    }
}
