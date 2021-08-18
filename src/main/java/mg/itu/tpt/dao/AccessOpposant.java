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
import mg.itu.tpt.modeles.Match;
import mg.itu.tpt.modeles.Opposant;
import mg.itu.tpt.modeles.Participant;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessOpposant {

    private DbConnect connexion;
    private AccessParticipant participant;
    private AccessMatch match;
    private PoolConnection pool;
    public AccessOpposant() {
    }

    public AccessOpposant(PoolConnection pool) {
        this.pool = pool;
        //this.connexion = connexion;
        this.participant = new AccessParticipant(this.pool);
        this.match = new AccessMatch(this.pool);
    }

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Opposant getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select  * from OPPOSANT where IDOPPOSANT=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Opposant opposant = new Opposant();
        Participant adv1 = new Participant();
        Participant adv2 = new Participant();
        Match game = new Match();
        while (rset.next()) {
            adv1 = this.participant.getById(rset.getInt("IDPARTICIPANT"));
            adv2 = this.participant.getById(rset.getInt("ADV"));
            game = this.match.getById(rset.getInt("IDMATCH"));
            opposant.setAdv1(adv1);
            opposant.setAdv2(adv2);
            opposant.setMatch(game);
            opposant.setIdOpposant(rset.getInt("IDOPPOSANT"));
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return opposant;
    }

    public ArrayList<Opposant> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select  * from OPPOSANT";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Opposant> liste = new ArrayList();
        while (rset.next()) {
            Opposant opposant = new Opposant();
            Participant adv1 = new Participant();
            Participant adv2 = new Participant();
            Match game = new Match();
            adv1 = this.participant.getById(rset.getInt("IDPARTICIPANT"));
            adv2 = this.participant.getById(rset.getInt("ADV"));
            game = this.match.getById(rset.getInt("IDMATCH"));
            opposant.setAdv1(adv1);
            opposant.setAdv2(adv2);
            opposant.setMatch(game);
            opposant.setIdOpposant(rset.getInt("IDOPPOSANT"));
            liste.add(opposant);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Opposant nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into OPPOSANT(IDPARTICIPANT, ADV, IDMATCH) values(?,?,?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getAdv1().getIdParticipant());
        stmt.setInt(2, nouveau.getAdv2().getIdParticipant());
        stmt.setInt(3, nouveau.getMatch().getIdMatch());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from OPPOSANT where IDOPPOSANT = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(Opposant maj) throws SQLException {
        String sql = "update OPPOSANT set IDPARTICIPANT=?, ADV=?, IDMATCH=?, where IDOPPOSANT=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, maj.getAdv1().getIdParticipant());
        stmt.setInt(2, maj.getAdv2().getIdParticipant());
        stmt.setInt(3, maj.getMatch().getIdMatch());
        stmt.setInt(4, maj.getIdOpposant());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
