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
import mg.itu.tpt.modeles.Match;
import mg.itu.tpt.modeles.TypeMatch;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessMatch {

    private DbConnect connexion;
    private AccessTypeMatch typematch;
    private AccessEtat etat;
    private PoolConnection pool;
    public AccessMatch() {
    }

    public AccessMatch(PoolConnection pool) {
        this.pool = pool;
        //this.connexion = connexion;
        this.typematch = new AccessTypeMatch(this.pool);
        this.etat = new AccessEtat(this.pool);
    }

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Match getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from MATCH where IDMATCH=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Match match = new Match();
        TypeMatch type = new TypeMatch();
        Etat state = new Etat();
        while (rset.next()) {
            type = this.typematch.getById(rset.getInt("IDTYPEMATCH"));
            state = this.etat.getById(rset.getInt("IDETAT"));
            match.setIdMatch(rset.getInt("IDMATCH"));
            match.setTypeMatch(type);
            match.setEtat(state);
            match.setDate(rset.getDate("DATE_MATCH"));
            String img = rset.getString("IMAGE");
            if (rset.wasNull()) {
                img = "";
            }
            match.setImage(img);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return match;
    }

    public ArrayList<Match> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from MATCH";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Match> liste = new ArrayList();
        while (rset.next()) {
            Match match = new Match();
            TypeMatch type = new TypeMatch();
            Etat state = new Etat();
            type = this.typematch.getById(rset.getInt("IDTYPEMATCH"));
            state = this.etat.getById(rset.getInt("IDETAT"));
            match.setIdMatch(rset.getInt("IDMATCH"));
            match.setTypeMatch(type);
            match.setEtat(state);
            match.setDate(rset.getDate("DATE_MATCH"));
            String img = rset.getString("IMAGE");
            if (rset.wasNull()) {
                img = "";
            }
            match.setImage(img);
            liste.add(match);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Match nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into MATCH(IDTYPEMATCH, IDETAT, DATE_MATCH, IMAGE) values(?,?,?,?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getTypeMatch().getIdTypeMatch());
        stmt.setInt(2, nouveau.getEtat().getIdEtat());
        stmt.setDate(3, nouveau.getDate());
        stmt.setString(4, nouveau.getImage());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from MATCH where IDMATCH = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(Match match) throws SQLException {
        String sql = "update MATCH set IDTYPEMATCH =?, IDETAT = ?, DATE_MATCH=?, IMAGE=? where IDMATCH=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, match.getTypeMatch().getIdTypeMatch());
        stmt.setInt(2, match.getEtat().getIdEtat());
        stmt.setDate(3, match.getDate());
        stmt.setString(4, match.getImage());
        stmt.setInt(5, match.getIdMatch());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
