/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class Tools {

    public static void close(Statement stmt, ResultSet rset, Connection connexion) {
        try {
            if (rset != null) {
                rset.close();
            }
        } catch (Exception e) {
        };
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
        };
    }
    public static void closePrepared(PreparedStatement stmt, ResultSet rset, Connection connexion) {
        try {
            if (rset != null) {
                rset.close();
            }
        } catch (Exception e) {
        };
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
        };
        
    }
    
//    public static ResultSet query(String sql , Connection connexion) throws SQLException{
//        Statement stmt = connexion.createStatement();
//        ResultSet result = stmt.executeQuery(sql);
//        return result;
//    }
}
