/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessEtat;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Etat;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceEtat {
    private DbConnect db;
    private AccessEtat access;
    private PoolConnection pool;
    public ServiceEtat(PoolConnection pool) throws ClassNotFoundException {
        //this.db=new DbConnect();
        this.pool = pool;
        this.access = new AccessEtat(this.pool);
    }
    
    public Etat getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<Etat> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int etat) throws ClassNotFoundException, SQLException{
        this.access.addNew(new Etat(etat));
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id, int etat) throws SQLException{
        this.access.edit(new Etat(id, etat));
    }
}
