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
import mg.itu.tpt.modeles.Categorie;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessCategorie {
 
    private PoolConnection pool;
    public AccessCategorie() {
    }

    public AccessCategorie(PoolConnection pool) {
        //this.connexion = connexion;
        this.pool = pool;
    }

    private DbConnect connexion;
;
    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Categorie getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from CATEGORIE where IDCATEGORIE=" + id;
        Statement stmt = this.pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Categorie categorie = new Categorie();
        while (rset.next()) {
            categorie.setIdCategorie(rset.getInt("IDCATEGORIE"));
            categorie.setNom(rset.getString("NOM_CATEGORIE"));
            categorie.setImage(rset.getString("IMAGE"));
        }
        //Tools.close(stmt, rset, this.pool.getDataSource().getConnection());
        return categorie;
    }

    public ArrayList<Categorie> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from CATEGORIE";
        Statement stmt = this.pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Categorie> liste = new ArrayList();
        while (rset.next()) {
            liste.add(new Categorie(rset.getInt("IDCATEGORIE"), rset.getString("NOM_CATEGORIE"), rset.getString("IMAGE")));
        }
        Tools.close(stmt, rset, this.pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Categorie nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into CATEGORIE(NOM_CATEGORIE, IMAGE) values(?,?)";
        PreparedStatement stmt = this.pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setString(1, nouveau.getNom());
        stmt.setString(2, nouveau.getImage());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, this.pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from CATEGORIE where IDCATEGORIE = " + id;
        Statement stmt = this.pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, this.pool.getDataSource().getConnection());
    }

    public void edit(Categorie maj) throws SQLException {
        String sql = "update CATEGORIE set NOM_CATEGORIE=?, IMAGE=? where IDCATEGORIE=?";
        PreparedStatement stmt = this.pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setString(1, maj.getNom());
        stmt.setString(2, maj.getImage());
        stmt.setInt(3, maj.getIdCategorie());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, this.pool.getDataSource().getConnection());
    }
}
