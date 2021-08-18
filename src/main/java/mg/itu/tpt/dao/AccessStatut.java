/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mg.itu.tpt.databaseConnection.DbConnect;
import mg.itu.tpt.databaseConnection.PoolConnection;
import mg.itu.tpt.modeles.Statut;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessStatut {
    private DbConnect connexion;
    private PoolConnection pool;
    public AccessStatut() {
    }

    public AccessStatut(PoolConnection pool) {
        //this.connexion = connexion;
        this.pool = pool;
    }
    
    
    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }
    
    public Statut getById(int id) throws ClassNotFoundException, SQLException{
        String sql = "select * from STATUT where IDSTATUT=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Statut statut = new Statut();
        while(rset.next()){
            statut.setIdStatut(rset.getInt("IDSTATUT"));
            statut.setDesignation(rset.getString("DESIGNATION"));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return statut;
    }
    public ArrayList<Statut> getAll()  throws ClassNotFoundException, SQLException{
        String sql = "select * from STATUT";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Statut> liste = new ArrayList();
        while(rset.next()){
            liste.add(new Statut(rset.getInt("IDSTATUT"), rset.getString("DESIGNATION")));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }
    public void addNew(Statut nouveau) throws ClassNotFoundException, SQLException{
        String sql = "insert into STATUT(DESIGNATION) values(?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setString(1, nouveau.getDesignation());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
        
    }
    public void delete(int id)  throws SQLException{
        String sql = "delete from STATUT where IDSTATUT = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }
    public void edit(Statut maj) throws SQLException{
        String sql = "update STATUT set DESIGNATION=? where IDSTATUT=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setString(1, maj.getDesignation());
        stmt.setInt(2, maj.getIdStatut());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
