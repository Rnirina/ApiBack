/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessOpposant;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Opposant;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceOpposant {
    private DbConnect db;
    private AccessOpposant access;
    private PoolConnection pool;
    public ServiceOpposant(PoolConnection pool) throws ClassNotFoundException {
        //this.db= new DbConnect();
        this.pool = pool;
        this.access= new AccessOpposant(this.pool);
    }
    
    
    public Opposant getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<Opposant> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int match, int adv1, int adv2) throws ClassNotFoundException, SQLException{
        Opposant nouveau = new Opposant(match, adv1, adv2);
        this.access.addNew(nouveau);
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id, int match, int adv1, int adv2) throws SQLException{
        Opposant update = new Opposant(id, match, adv1, adv2);
        this.access.edit(update);
    }
}
