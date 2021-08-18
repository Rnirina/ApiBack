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
import mg.itu.tpt.dao.AccessUtilisateur;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Utilisateur;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class ServiceUtilisateur {
    private PoolConnection pool;
    private DbConnect db;
    private AccessUtilisateur access;

    public ServiceUtilisateur(PoolConnection pool) throws ClassNotFoundException {
        this.pool = pool;
        //this.db = base;
        this.access =new AccessUtilisateur(this.pool);
    }
    
    
    public Utilisateur getUn(int id) throws ClassNotFoundException, SQLException{
        return this.access.getById(id);
    }
    public ArrayList<Utilisateur> getTous()  throws ClassNotFoundException, SQLException{
        return this.access.getAll();
    }
    public void ajout(int typeUtilisateur, String nom, String username, String naissance, String mail, String pass, String contact, Double montantBet) throws ClassNotFoundException, SQLException, ParseException{
        Date userDate = SuperService.dateConversion(naissance);
        Utilisateur nouveau = new Utilisateur(typeUtilisateur,nom,username,userDate,mail,pass,contact,montantBet);
        this.access.addNew(nouveau);
    }
    public void suppression(int id)  throws SQLException{
        this.access.delete(id);
    }
    public void maj(int id, int typeUtilisateur, String nom, String username, String naissance, String mail, String pass, String contact, Double montantBet) throws SQLException, ParseException{
        Date userDate = SuperService.dateConversion(naissance);
        Utilisateur update = new Utilisateur(id,typeUtilisateur,nom,username,userDate,mail,pass,contact,montantBet);
        this.access.edit(update);
    }
    
    public Utilisateur login(String username, String password) throws SQLException, ClassNotFoundException{
        return this.access.login(username, password);
    }
}
