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
import mg.itu.tpt.modeles.Etat;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessEtat {
    private PoolConnection pool;
    private DbConnect connexion;

    public AccessEtat() {
    }

    public AccessEtat(PoolConnection pool) {
     //   this.connexion = connexion;
        this.pool = pool;
    }

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Etat getById(int id) throws ClassNotFoundException, SQLException {
        String sql = " select * from ETAT where IDETAT= " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Etat state = new Etat();
        while (rset.next()) {
            state.setIdEtat(rset.getInt("IDETAT"));
            state.setEtat(rset.getInt("ETAT"));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return state;
    }

    public ArrayList<Etat> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from ETAT";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Etat> liste = new ArrayList();
        while (rset.next()) {
            liste.add(new Etat(rset.getInt("IDETAT"), rset.getInt("ETAT")));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Etat nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into ETAT(ETAT) values(?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getEtat());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from ETAT where IDETAT = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(Etat maj) throws SQLException {
        String sql = "update CATEGORIE set NOM_CATEGORIE=?, IMAGE=? where IDCATEGORIE=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
