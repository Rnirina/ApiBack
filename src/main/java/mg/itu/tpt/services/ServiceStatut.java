/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessStatut;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Statut;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceStatut {

    private DbConnect db;
    private AccessStatut access;
    private PoolConnection pool;
    public ServiceStatut(PoolConnection pool) throws ClassNotFoundException {
        //this.db = new DbConnect();
        this.pool = pool;
        this.access = new AccessStatut(this.pool);
    }

    public Statut getUn(int id) throws ClassNotFoundException, SQLException {
        return this.access.getById(id);
    }

    public ArrayList<Statut> getTous() throws ClassNotFoundException, SQLException {
        return this.access.getAll();
    }

    public void ajout(String designation) throws ClassNotFoundException, SQLException {
        this.access.addNew(new Statut(designation));
    }

    public void suppression(int id) throws SQLException {
        this.access.delete(id);
    }

    public void maj(int id, String designation) throws SQLException {
        this.access.edit(new Statut(id, designation));
    }
}
