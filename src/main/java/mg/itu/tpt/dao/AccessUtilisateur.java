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
import mg.itu.tpt.modeles.Utilisateur;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class AccessUtilisateur {
    private PoolConnection pool;
    private DbConnect connexion;
    private AccessTypeUtilisateur type;

    public AccessUtilisateur() {
    }

    public AccessUtilisateur(PoolConnection pool) {
        this.pool = pool;
        //this.connexion = connexion;
        this.type = new AccessTypeUtilisateur(this.pool);
    }
    

    public DbConnect getConnexion() {
        return connexion;
    }

    public void setConnexion(DbConnect connexion) {
        this.connexion = connexion;
    }

    public Utilisateur getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from UTILISATEUR where IDUTILISATEUR=" + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Utilisateur user =new Utilisateur();
        TypeUtilisateur typeUser = new TypeUtilisateur();
        while(rset.next()){
            typeUser = this.type.getById(rset.getInt("IDTYPEUTILISATEUR"));
            user.setIdUtilisateur(rset.getInt("IDUTILISATEUR"));
            user.setNom(rset.getString("NOM_UTILISATEUR"));
            user.setPass(rset.getString("PASS"));
            user.setMail(rset.getString("EMAIL"));
            user.setNaissance(rset.getDate("NAISSANCE"));
            user.setUsername(rset.getString("NOM_CONNEXION"));
            user.setContact(rset.getString("CONTACT"));
            user.setMontantBet(rset.getDouble("MONTANTBET"));
            user.setTypeUtilisateur(typeUser);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return user;
    }

    public ArrayList<Utilisateur> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from UTILISATEUR";
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        ArrayList<Utilisateur> liste =new ArrayList();
        while(rset.next()){
            Utilisateur user =new Utilisateur();
            TypeUtilisateur typeUser = new TypeUtilisateur();
            typeUser = this.type.getById(rset.getInt("IDTYPEUTILISATEUR"));
            user.setIdUtilisateur(rset.getInt("IDUTILISATEUR"));
            user.setNom(rset.getString("NOM_UTILISATEUR"));
            user.setPass(rset.getString("PASS"));
            user.setMail(rset.getString("EMAIL"));
            user.setNaissance(rset.getDate("NAISSANCE"));
            user.setUsername(rset.getString("NOM_CONNEXION"));
            user.setContact(rset.getString("CONTACT"));
            user.setMontantBet(rset.getDouble("MONTANTBET"));
            user.setTypeUtilisateur(typeUser);
            liste.add(user);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return liste;
    }

    public void addNew(Utilisateur nouveau) throws ClassNotFoundException, SQLException {
        String sql = "insert into UTILISATEUR(IDTYPEUTILISATEUR, NOM_UTILISATEUR, NOM_CONNEXION, NAISSANCE, EMAIL, PASS, CONTACT, MONTANTBET) values(?,?,?,TO_DATE(?, 'dd-mm-yyyy'),?,?,?,?,?)";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, nouveau.getTypeUtilisateur().getIdTypeUtilisateur());
        stmt.setString(2, nouveau.getNom());
        stmt.setString(3, nouveau.getUsername());
        stmt.setDate(4, nouveau.getNaissance());
        stmt.setString(5, nouveau.getMail());
        stmt.setString(6, nouveau.getPass());
        stmt.setString(7, nouveau.getContact());
        stmt.setDouble(8,nouveau.getMontantBet());
        stmt.executeUpdate();
        this.connexion.getConnex().commit();
        Tools.closePrepared(stmt, null, pool.getDataSource().getConnection());
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from UTILISATEUR where IDUTILISATEUR = " + id;
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        stmt.executeQuery(sql);
        this.connexion.getConnex().commit();
        Tools.close(stmt, null, pool.getDataSource().getConnection());
    }

    public void edit(Utilisateur maj) throws SQLException {
        String sql = "update UTILISATEUR set IDTYPEUTILISATEUR=?, NOM_UTILISATEUR=?, NOM_CONNEXION=?, NAISSANCE=?, EMAIL=?, PASS=?, CONTACT=?, MONTANTBET=? where IDUTILISATEUR=?";
        PreparedStatement stmt = pool.getDataSource().getConnection().prepareStatement(sql);
        stmt.setInt(1, maj.getTypeUtilisateur().getIdTypeUtilisateur());
        stmt.setString(2, maj.getNom());
        stmt.setString(3, maj.getUsername());
        stmt.setDate(4, maj.getNaissance());
        stmt.setString(5, maj.getMail());
        stmt.setString(6, maj.getPass());
        stmt.setString(7, maj.getContact());
        stmt.setDouble(8,maj.getMontantBet());
        stmt.setInt(9, maj.getIdUtilisateur());
        stmt.executeUpdate();
        this.connexion.getConnex().commit();
        Tools.closePrepared(stmt, null, connexion.getConnex());
    }
    
    public Utilisateur login(String username, String password) throws SQLException, ClassNotFoundException{
        String sql = "select * from UTILISATEUR where NOM_CONNEXION='"+username+"' AND PASS='"+password+"'";
        System.out.println(sql);
        Statement stmt = pool.getDataSource().getConnection().createStatement();
        ResultSet rset = stmt.executeQuery(sql);
        Utilisateur user =new Utilisateur();
        TypeUtilisateur typeUser = new TypeUtilisateur();
        while(rset.next()){
            typeUser = this.type.getById(rset.getInt("IDTYPEUTILISATEUR"));
            user.setIdUtilisateur(rset.getInt("IDUTILISATEUR"));
            user.setNom(rset.getString("NOM_UTILISATEUR"));
            //user.setPass(rset.getString("PASS"));
            user.setMail(rset.getString("EMAIL"));
            user.setNaissance(rset.getDate("NAISSANCE")); // changer le format de la date
            user.setUsername(rset.getString("NOM_CONNEXION"));
            user.setContact(rset.getString("CONTACT"));
            user.setMontantBet(rset.getDouble("MONTANTBET"));
            user.setTypeUtilisateur(typeUser);
        }
        Tools.close(stmt, rset, pool.getDataSource().getConnection());
        return user;
    }
}
