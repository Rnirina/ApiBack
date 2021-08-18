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
import mg.itu.tpt.modeles.Participant;
import mg.itu.tpt.modeles.Statut;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessParticipant {

    private DbConnect connexion;
    private AccessStatut statut;
    private AccessMatch match;
    private PoolConnection pool;
    public AccessParticipant() {
    }

    public AccessParticipant(PoolConnection pool) {
        //this.connexion = connexion;
        this.pool = pool;
        statut = new AccessStatut(this.pool);
        match = new AccessMatch(this.pool);
    }

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Participant getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from PARTICIPANT where IDPARTICIPANT=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Statut status = new Statut();
        Match m = new Match();
        Participant participant = new Participant();
        while (rset.next()) {
            status = this.statut.getById(rset.getInt("IDSTATUT"));
            m = this.match.getById(rset.getInt("IDMATCH"));
            participant.setIdParticipant(rset.getInt("IDPARTICIPANT"));
            participant.setNom(rset.getString("NOM_PARTICIPANT"));
            participant.setScore(rset.getFloat("SCORE"));
            String img = rset.getString("IMAGE");
            if (rset.wasNull()) {
                img = "";
            }
            participant.setImage(img);
            participant.setMatch(m);
            participant.setStatut(status);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return participant;
    }

    public ArrayList<Participant> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from PARTICIPANT";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Participant> liste = new ArrayList();
        while (rset.next()) {
            Participant participant = new Participant();
            Statut status = new Statut();
            Match m = new Match();
            status = this.statut.getById(rset.getInt("IDSTATUT"));
            m = this.match.getById(rset.getInt("IDMATCH"));
            participant.setIdParticipant(rset.getInt("IDPARTICIPANT"));
            participant.setNom(rset.getString("NOM_PARTICIPANT"));
            participant.setScore(rset.getFloat("SCORE"));
            String img = rset.getString("IMAGE");
            if (rset.wasNull()) {
                img = "";
            }
            participant.setImage(img);
            participant.setMatch(m);
            participant.setStatut(status);
            liste.add(participant);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Participant nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into PARTICIPANT(IDSTATUT, IDMATCH, NOM_PARTICIPANT, IMAGE, SCORE) values(?,?,?,?,?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getStatut().getIdStatut());
        stmt.setInt(2, nouveau.getMatch().getIdMatch());
        stmt.setString(3, nouveau.getNom());
        stmt.setString(4, nouveau.getImage());
        stmt.setFloat(5, nouveau.getScore());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from PARTICIPANT where IDPARTICIPANT = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(Participant maj) throws SQLException {
        String sql = "update PARTICIPANT set IDSTATUT=?, IDMATCH=?, NOM_PARTICIPANT=?, IMAGE=?, SCORE=? where IDPARTICIPANT=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, maj.getStatut().getIdStatut());
        stmt.setInt(2, maj.getMatch().getIdMatch());
        stmt.setString(3, maj.getNom());
        stmt.setString(4, maj.getImage());
        stmt.setFloat(5, maj.getScore());
        stmt.setInt(6, maj.getIdParticipant());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
