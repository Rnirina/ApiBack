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
import mg.itu.tpt.modeles.TypeMatch;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessTypeMatch {

    private AccessCategorie access;
    private PoolConnection pool;
    public AccessTypeMatch() {
    }

    public AccessTypeMatch(PoolConnection pool) {
        //this.connexion = connexion;
        this.pool = pool;
        this.access = new AccessCategorie(this.pool);
    }

    private DbConnect connexion;

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public TypeMatch getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from TYPEMATCH where IDTYPEMATCH=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        TypeMatch type = new TypeMatch();
        while (rset.next()) {
            type.setIdTypeMatch(rset.getInt("IDTYPEMATCH"));
            type.setNom(rset.getString("NOM_MATCH"));
            String img = rset.getString("IMAGE");
            if (rset.wasNull()) {
                img = "";
            }
            type.setImage(img);
            Categorie interm = new Categorie();
            interm = access.getById(rset.getInt("IDCATEGORIE"));
            type.setCategorie(interm);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return type;
    }

    public ArrayList<TypeMatch> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from TYPEMATCH";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<TypeMatch> liste = new ArrayList();
        while (rset.next()) {
            Categorie categ = new Categorie();
            categ = access.getById(rset.getInt("IDCATEGORIE"));
            String img = rset.getString("IMAGE");
            if (rset.wasNull()) {
                img = "";
            }
            TypeMatch interm = new TypeMatch(rset.getInt("IDTYPEMATCH"), categ, rset.getString("NOM_MATCH"), img);
            liste.add(interm);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(TypeMatch nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into TYPEMATCH(IDCATEGORIE, NOM_MATCH, IMAGE) values(?,?,?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getCategorie().getIdCategorie());
        stmt.setString(2, nouveau.getNom());
        stmt.setString(3, nouveau.getImage());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from TYPEMATCH where IDTYPEMATCH = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(TypeMatch maj) throws SQLException {
        String sql = "update TYPEMATCH set IDCATEGORIE=?, NOM_MATCH=?, IMAGE=? where IDTYPEMATCH=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, maj.getCategorie().getIdCategorie());
        stmt.setString(2, maj.getNom());
        stmt.setString(3, maj.getImage());
        stmt.setInt(4, maj.getIdTypeMatch());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
