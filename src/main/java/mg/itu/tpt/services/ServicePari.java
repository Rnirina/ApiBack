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
import mg.itu.tpt.dao.AccessPari;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Pari;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServicePari {
    private DbConnect db;
    private AccessPari access;
    private PoolConnection pool;
    public ServicePari(PoolConnection pool) throws ClassNotFoundException {
        //this.db= new DbConnect();
        this.pool = pool;
        this.access = new AccessPari(this.pool);
    }
    public Pari getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<Pari> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int status, int match, int utilisateur, Double montant, float score, String date, int participant) throws ClassNotFoundException, SQLException, ParseException{
        Date datePari = SuperService.dateConversion(date);
        Pari pari = new Pari(status, match, utilisateur, montant, score,datePari, participant);
        this.access.addNew(pari);
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int idPari, int status, int match, int utilisateur, Double montant, float score, String date, int participant) throws SQLException, ParseException{
        Date datePari = SuperService.dateConversion(date);
        Pari maj =new Pari(idPari,status, match, utilisateur, montant, score,datePari, participant);
        this.access.edit(maj);
    }
}
