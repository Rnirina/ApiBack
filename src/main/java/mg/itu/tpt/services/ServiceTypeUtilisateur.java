/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessTypeUtilisateur;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.TypeUtilisateur;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceTypeUtilisateur {
    private PoolConnection pool;
    private DbConnect db;
    private AccessTypeUtilisateur access;

    public ServiceTypeUtilisateur(PoolConnection pool) throws ClassNotFoundException {
        //this.db =new DbConnect();
        this.pool = pool;
        this.access = new AccessTypeUtilisateur(pool);
    }
    
    public TypeUtilisateur getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<TypeUtilisateur> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(String nom) throws ClassNotFoundException, SQLException{
        this.access.addNew(new TypeUtilisateur(nom));
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id, String nom) throws SQLException{
        this.access.edit(new TypeUtilisateur(id, nom));
    }
}
