/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.services;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import mg.itu.tpt.dao.AccessMatch;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Match;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceMatch {
    private DbConnect db;
    private AccessMatch access;
private PoolConnection pool;
    public ServiceMatch(PoolConnection pool) throws ClassNotFoundException {
        //this.db = new DbConnect();
        this.pool = pool;
        this.access = new AccessMatch(this.pool);
    }
    
    public Match getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<Match> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int type, int etat, String date, String image) throws ClassNotFoundException, SQLException, ParseException{
        Date dateMatch = SuperService.dateConversion(date);
        Match nouveau = new Match(type, etat, dateMatch, image);
        this.access.addNew(nouveau);
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id,int type, int etat, String date, String image) throws SQLException, ParseException{
        Date dateMatch = SuperService.dateConversion(date);
        Match update = new Match(id, type, etat, dateMatch, image);
        this.access.edit(update);
    }
}
