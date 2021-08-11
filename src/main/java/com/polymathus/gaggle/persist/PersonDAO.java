package com.polymathus.gaggle.persist;

import com.polymathus.gaggle.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAO {

    /*
    This is horrible but temporary...
     */
    public static final String DB_URL = "jdbc:mysql://localhost:3306/gaggledev?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String DB_USER_NAME = "gaggle-dev";
    public static final String DB_PASSWORD = "Gaggle2021!";

    /*
    this isn't much better...but
     */
    private static final String TABLE_NAME = "test_gaggle";
    private static final String PRIMARY_KEY_FIELDNAME = "person_id";
    private static final String FULL_NAME_FIELDNAME = "full_name";          //replace all above with enum?




    public static List<Person> findAll(){

        final List<Person> persons = new ArrayList<Person>();       //make this typsafe with <Person> DONE!

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
             Statement statement = con.createStatement();
             ResultSet resultset = statement.executeQuery(getSelectAllSQL().toString())) {

            while (resultset.next()){
                final Person person = new Person();
                person.setFirstName(resultset.getString("first_name"));
                person.setLastName(resultset.getString("last_name"));
                person.setFullName(resultset.getString("full_name"));

                persons.add(person);
            }

            System.out.println("loaded up "+persons.size()+ " people" );

        } catch (SQLException ex) {                                                     //make this better--specific Exception

            Logger logger = Logger.getLogger(PersonDAO.class.getName());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return persons;
    }






    /**
     * Builds the base SQL query without a WHERE clause.
     * @return
     */
    private static StringBuilder getSelectAllSQL(){

        System.out.println("preparing base sQL");

        StringBuilder statement = new StringBuilder();
        statement.append("SELECT * ");
        statement.append("FROM " + TABLE_NAME + " ");

        return statement;
    }

}
