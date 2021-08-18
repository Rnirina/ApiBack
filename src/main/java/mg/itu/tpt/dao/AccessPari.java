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
import mg.itu.tpt.modeles.Pari;
import mg.itu.tpt.modeles.Participant;
import mg.itu.tpt.modeles.Statut;
import mg.itu.tpt.modeles.Utilisateur;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessPari {
    private PoolConnection pool;
    private DbConnect connexion;
    private AccessStatut statut;
    private AccessMatch match;
    private AccessUtilisateur user;
    private AccessParticipant participant;

    public AccessPari() {
    }

    public AccessPari(PoolConnection pool) {
        this.pool = pool;
        //this.connexion = connexion;
        this.statut = new AccessStatut(this.pool);
        this.match = new AccessMatch(this.pool);
        this.user = new AccessUtilisateur(this.pool);
        this.participant = new AccessParticipant(this.pool);
    }

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Pari getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from PARI where IDPARI=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Pari pari = new Pari();
        Statut state = new Statut();
        Utilisateur utilisateur = new Utilisateur();
        Match game = new Match();
        Participant part = new Participant();
        while (rset.next()) {
            pari.setIdPari(rset.getInt("IDPARI"));
            pari.setDate(rset.getDate("DATE_PARI"));
            pari.setScore(rset.getFloat("SCORE"));
            pari.setMontant(rset.getDouble("MONTANT"));
            state = this.statut.getById(rset.getInt("IDSTATUT"));
            utilisateur = this.user.getById(rset.getInt("IDUTILISATEUR"));
            game = this.match.getById(rset.getInt("IDMATCH"));
            int idpart = rset.getInt("IDPARTICIPANT");
            if (!rset.wasNull()) {
                part = this.participant.getById(idpart);
            }
            pari.setMatch(game);
            pari.setStatut(state);
            pari.setParticipant(part);
            pari.setUtilisateur(utilisateur);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return pari;
    }

    public ArrayList<Pari> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from PARI";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Pari> liste = new ArrayList();
        while (rset.next()) {
            Pari pari = new Pari();
            Statut state = new Statut();
            Utilisateur utilisateur = new Utilisateur();
            Match game = new Match();
            Participant part = new Participant();
            pari.setIdPari(rset.getInt("IDPARI"));
            pari.setDate(rset.getDate("DATE_PARI"));
            pari.setScore(rset.getFloat("SCORE"));
            pari.setMontant(rset.getDouble("MONTANT"));
            state = this.statut.getById(rset.getInt("IDSTATUT"));
            utilisateur = this.user.getById(rset.getInt("IDUTILISATEUR"));
            game = this.match.getById(rset.getInt("IDMATCH"));
            int idpart = rset.getInt("IDPARTICIPANT");
            if (!rset.wasNull()) {
                part = this.participant.getById(idpart);
            }
            pari.setMatch(game);
            pari.setStatut(state);
            pari.setParticipant(part);
            pari.setUtilisateur(utilisateur);
            liste.add(pari);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Pari nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into PARI(IDSTATUT, IDMATCH, IDUTILISATEUR, MONTANT, SCORE, DATE_PARI, IDPARTICIPANT) values(?,?,?,?,?,?,?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getStatut().getIdStatut());
        stmt.setInt(2, nouveau.getMatch().getIdMatch());
        stmt.setInt(3, nouveau.getUtilisateur().getIdUtilisateur());
        stmt.setDouble(4, nouveau.getMontant());
        stmt.setFloat(5, nouveau.getScore());
        stmt.setDate(6, nouveau.getDate());
        stmt.setInt(7, nouveau.getParticipant().getIdParticipant());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from PARI where IDPARI = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.pool.getDataSource().getConnection().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(Pari maj) throws SQLException {
        String sql = "update PARI set IDSTATUT=?, IDMATCH=?, IDUTILISATEUR=?, MONTANT=?, SCORE=?, DATE_PARI=?, IDPARTICIPANT=? where IDPARI=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, maj.getStatut().getIdStatut());
        stmt.setInt(2, maj.getMatch().getIdMatch());
        stmt.setInt(3, maj.getUtilisateur().getIdUtilisateur());
        stmt.setDouble(4, maj.getMontant());
        stmt.setFloat(5, maj.getScore());
        stmt.setDate(6, maj.getDate());
        stmt.setInt(7, maj.getParticipant().getIdParticipant());
        stmt.setInt(8, maj.getIdPari());
        stmt.executeUpdate();
        this.pool.getDataSource().getConnection().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }
}
