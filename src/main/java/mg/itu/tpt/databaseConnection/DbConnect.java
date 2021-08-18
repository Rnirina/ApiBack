/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class DbConnect {
    private Connection connex;

    public DbConnect() throws ClassNotFoundException {
        this.getConn();
    }

    public Connection getConnex() {
        return connex;
    }

    public void setConnex(Connection connex) {
        this.connex = connex;
    }
    
    private Connection getConn() throws ClassNotFoundException{
        String lien="jdbc:oracle:thin:@//144.21.67.201:1521/PDBITU.631174089.oraclecloud.internal";
        String user = "RandriamananaMI2021";
        String pass ="RandriamananaMI202101";
        Connection connexion = null;
         Class.forName("oracle.jdbc.driver.OracleDriver");
           try  {
                  connexion = DriverManager.getConnection(lien, user, pass);
            if (connexion != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
           this.connex = connexion;
           return connexion;
    }
    
}
