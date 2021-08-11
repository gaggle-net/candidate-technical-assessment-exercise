package com.polymathus.gaggle.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.polymathus.gaggle.domain.Person;

/**
 * Interacts with the database for all data in the Person domain.
 */
public class PersonDAO {

    private static final String TABLE_NAME = "test_gaggle";
    private static final String PRIMARY_KEY_FIELDNAME = "person_id";
    private static final String FULL_NAME_FIELDNAME = "full_name";          //replace all above with enum?


    /**
     * Find a Person record by Primary Key.
     *
     * @param primaryKey the Primary Key of the person record you seek.
     * @return a Person loaded up with the associated data.
     */
    public static Person findByPrimaryKey(Integer primaryKey) {

        final Person person = new Person();
        String query = prepareSearchByPrimaryKey(primaryKey);

        Connection connection = Database.getConnection();               //@todo: nooooo...don't dothis every time

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(query);

                while (resultset.next()) {
                    person.setFirstName(resultset.getString("first_name"));
                    person.setLastName(resultset.getString("last_name"));
                    person.setFullName(resultset.getString("full_name"));
                }
            } catch (SQLException exception){
                Logger logger = Logger.getLogger(PersonDAO.class.getName());
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }
        }


        return person;
    }

    /**
     * Find a Person record by name or name fragment.
     * Note that more results can be returned as the input name fragment gets less specific.
     *
     * @param name the name--or portion of the name--of the person you seek.
     * @return a Map where values hold a Person and the key contains its corresponding primary key (as an Integer).
     */
    public static Map<Integer, Person> findByName(String name){

        final Map<Integer, Person> persons = new HashMap<Integer, Person>();
        String query = prepareSearchByName(name);

        Connection connection = Database.getConnection();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(query);

                while (resultset.next()) {
                    final Person person = new Person();
                    person.setFirstName(resultset.getString("first_name"));
                    person.setLastName(resultset.getString("last_name"));
                    person.setFullName(resultset.getString("full_name"));

                    persons.put(new Integer(resultset.getString("person_id")), person);
                }
            } catch (SQLException exception){
                Logger logger = Logger.getLogger(PersonDAO.class.getName());
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }
        }

        System.out.println("loaded up "+persons.size()+ " people" );

        return persons;
    }

    /**
     * Select all of the Person records available.
     *
     * As of version 1.0, no business logic layer uses this, but it is used in unit test case(s).
     *
     * @return a List of Person objects.
     */
    public static List<Person> findAll(){

        final List<Person> persons = new ArrayList<Person>();
        Connection connection = Database.getConnection();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(getSelectAllSQL().toString());

                while (resultset.next()){
                    final Person person = new Person();
                    person.setFirstName(resultset.getString("first_name"));
                    person.setLastName(resultset.getString("last_name"));
                    person.setFullName(resultset.getString("full_name"));

                    persons.add(person);
                }

                System.out.println("loaded up "+persons.size()+ " people" );

            } catch (SQLException exception){
                Logger logger = Logger.getLogger(PersonDAO.class.getName());
                logger.log(Level.SEVERE, exception.getMessage(), exception);
            }
        }


        return persons;
    }


    /**
     * Builds the base SQL query without a WHERE clause.
     *
     * @return a StringBuilder with a "SELECT * FROM 'table_name' " format value.
     */
    private static StringBuilder getSelectAllSQL(){

        System.out.println("preparing base sQL");

        StringBuilder statement = new StringBuilder();
        statement.append("SELECT * ");
        statement.append("FROM " + TABLE_NAME + " ");

        return statement;
    }


    /**
     * Prepare the WHERE clause for a Search by Primary Key.
     *
     * @param primaryKey the Primary Key of the person record you seek.
     * @return a StringBuilder with "WHERE 'primary_key' = 'primaryKey'" format value.
     */
    private static String prepareSearchByPrimaryKey(Integer primaryKey){

        System.out.println("preparing search by PK in the DAO");
        StringBuilder statement = getSelectAllSQL();
        statement.append("WHERE " + PRIMARY_KEY_FIELDNAME + " = "+primaryKey.toString());

        return statement.toString();
    }

    /**
     * Prepare the WHERE clause for a Search by Name.
     * The REGEXP operator is used so that records can still be found evenif only a portion of the name is entered.
     *
     * @param name the name--or portion of the name--of the person you seek.
     * @return a StringBuilder with "WHERE 'full_name' REGEXP 'name'" format value.
     */
    private static String prepareSearchByName(String name){

        StringBuilder statement = getSelectAllSQL();
        statement.append("WHERE "+ FULL_NAME_FIELDNAME + " ");
        statement.append("REGEXP " + "'" + name + "'");

        return statement.toString();
    }

}
