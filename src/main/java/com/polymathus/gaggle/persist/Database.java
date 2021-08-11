package com.polymathus.gaggle.persist;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static Connection connection = null;            //connection pool better?

    /*
    now...these are better...but this whole design is still specific to my local MySQL; refactor with better abstraction
     */
    public static final String DB_URL = "jdbc:mysql://localhost:3306/gaggledev?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String DB_USER_NAME = "gaggle-dev";
    public static final String DB_PASSWORD = "Gaggle2021!";

    /**
     * Get a connection to the data source.
     * @return a Connection for the data source.
     */
    public static Connection getConnection(){

        if (connection == null){
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);

            } catch (SQLException exception){
                Logger logger = Logger.getLogger(Database.class.getName());
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }
        }

        return connection;
    }
}
