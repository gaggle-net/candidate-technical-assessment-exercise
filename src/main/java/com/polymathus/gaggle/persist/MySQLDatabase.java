package com.polymathus.gaggle.persist;

import org.apache.log4j.Level;

import java.sql.*;

public class MySQLDatabase implements Database {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MySQLDatabase.class);

    private static Connection connection = null;            //connection pool better?

    private static final String DB_URL = "jdbc:mysql://localhost:3306/gaggledev?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER_NAME = "gaggle-dev";
    private static final String DB_PASSWORD = "Gaggle2021!";

    /**
     * Public Constructor
     */
    public MySQLDatabase() {

    }

    /**
     * Get a connection to the data source.
     *
     * @return a Connection for the data source.
     */
    public Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);

            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception.getMessage(), exception);
            }
        }

        return connection;
    }
}
