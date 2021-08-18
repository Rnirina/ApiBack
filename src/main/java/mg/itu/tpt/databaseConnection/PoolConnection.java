/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.itu.tpt.databaseConnection;

import java.sql.SQLException;
import javax.activation.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 *
 * @author RADO RANDRIAMANANA
 */
public class PoolConnection {

    private static final String URL = "jdbc:oracle:thin:@//144.21.67.201:1521/PDBITU.631174089.oraclecloud.internal";
    private static final String USERNAME = "RandriamananaMI2021";
    private static final String PASSWORD = "RandriamananaMI202101";

    private BasicDataSource dataSource = null;

    public PoolConnection() throws SQLException {
        this.setUp();
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUp() throws SQLException {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(PoolConnection.URL);
        dataSource.setUsername(PoolConnection.USERNAME);
        dataSource.setPassword(PoolConnection.PASSWORD);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
        System.out.println("la connexion" + dataSource.getConnection());
    }

}
