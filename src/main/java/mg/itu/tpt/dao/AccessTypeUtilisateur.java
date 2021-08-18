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
import mg.itu.tpt.modeles.TypeUtilisateur;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessTypeUtilisateur {

    private DbConnect connexion;
    private PoolConnection  pool;

    public AccessTypeUtilisateur() {
    }

    public AccessTypeUtilisateur(PoolConnection pool) {
        //this.connexion = connexion;
        this.pool = pool;
    }

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public TypeUtilisateur getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from TYPEUTILISATEUR where IDTYPEUTILISATEUR=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        TypeUtilisateur type = new TypeUtilisateur();
        while (rset.next()) {
            type.setIdTypeUtilisateur(rset.getInt("IDTYPEUTILISATEUR"));
            type.setNom(rset.getString("NOM_TYPE"));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return type;
    }

    public ArrayList<TypeUtilisateur> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from TYPEUTILISATEUR";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<TypeUtilisateur> liste = new ArrayList();
        while (rset.next()) {
            liste.add(new TypeUtilisateur(rset.getInt("IDTYPEUTILISATEUR"), rset.getString("NOM_TYPE")));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(TypeUtilisateur type) throws ClassNotFoundException, SQLException {
        String sql = "insert into TYPEUTILISATEUR(NOM_TYPE) values(?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setString(1, type.getNom());   
        stmt.executeUpdate();
        this.connexion.getConnex().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from TYPEUTILISATEUR where IDTYPEUTILISATEUR = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.connexion.getConnex().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(TypeUtilisateur maj) throws SQLException {
        String sql = "update TYPEUTILSATEUR set NOM_TYPE=? where IDTYPEUTILISATEUR=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setString(1, maj.getNom());
        stmt.setInt(2, maj.getIdTypeUtilisateur());
        stmt.executeUpdate();
        this.connexion.getConnex().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
