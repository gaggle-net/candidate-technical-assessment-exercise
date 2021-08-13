package com.polymathus.gaggle.persist;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLiteDatabase implements Database {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(SQLiteDatabase.class);

    Connection connection = null;
    private static final String TABLE_NAME = "person";
    private static final String PRIMARY_KEY_FIELDNAME = "person_id";
    private static final String FULL_NAME_FIELDNAME = "full_name";          //replace all above with enum?


    SQLiteDatabase() {

        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            LOGGER.log(Level.ERROR, exception.getMessage());
        }

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:gaggle-net.db");

            LOGGER.log(Level.TRACE, "creating a SQLiteDatabase");
            LOGGER.log(Level.TRACE, connection.toString());

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            //always refresh data...this can be refactored to only execute if no table, worth it? also, this shouldn't really be happening here
            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (person_id integer, first_name string, last_name string, full_name string)");
            statement.executeUpdate("insert into person values(200, 'Tommy', 'Schaeffer', 'Tommy Schaeffer')");
            statement.executeUpdate("insert into person values(400, 'Thomas', 'Schaeffer', 'Thomas Schaeffer, Sr.')");
            statement.executeUpdate("insert into person values(700, 'Bruce', 'Wayne', 'Bruce Wayne')");
            statement.executeUpdate("insert into person values(800, 'Thomas', 'Newman', 'Thomas Newman')");
            statement.executeUpdate("insert into person values(900, 'Isambard', 'Brunel', 'Isambard Brunel')");
            statement.executeUpdate("insert into person values(1000, 'Leonardo', 'D'Vinci', 'Leonardo D'Vinci')");
            statement.executeUpdate("insert into person values(1100, 'Jòsé', 'Cuervo', 'Jòsé Cuervo')");


//            ResultSet rs = statement.executeQuery("select * from person");
//            while(rs.next()) {
//                // read the result set
//                System.out.println("name = " + rs.getString("full_name"));
//                System.out.println("id = " + rs.getInt("person_id"));
//            }


        } catch (SQLException exception) {
            // if the error message is "out of memory", it probably means no database file is found
            LOGGER.log(Level.ERROR, exception.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}