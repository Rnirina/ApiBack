/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.SQLException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessParticipant;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Participant;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceParticipant {
    private DbConnect db;
    private AccessParticipant access;
    private PoolConnection pool;
    public ServiceParticipant(PoolConnection pool) throws ClassNotFoundException {
        //this.db= new DbConnect();
        this.pool = pool;
        this.access = new AccessParticipant(this.pool);
    }
    
    public Participant getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<Participant> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int statut, int match, String nom, float score, String image) throws ClassNotFoundException, SQLException{
        Participant nouveau =new Participant(statut,match,nom,score,image);
        this.access.addNew(nouveau);
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id, int statut, int match, String nom, float score, String image) throws SQLException{
        Participant update =new Participant(id,statut,match,nom,score,image);
        this.access.edit(update);
    }
}
