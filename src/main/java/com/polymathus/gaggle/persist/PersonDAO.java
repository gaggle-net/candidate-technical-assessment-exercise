package com.polymathus.gaggle.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.polymathus.gaggle.domain.Person;
import org.apache.log4j.Level;

/**
 * Interacts with the database for all data in the Person domain.
 */
public class PersonDAO {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PersonDAO.class);

    private static final String TABLE_NAME = "person";
    private static final String PRIMARY_KEY_FIELDNAME = "person_id";
    private static final String FULL_NAME_FIELDNAME = "full_name";

    private static Database database = null;
    private static Connection connection = null;

    /**
     * Find a Person record by Primary Key.
     *
     * @param primaryKey the Primary Key of the person record you seek.
     * @return a Person loaded up with the associated data.
     */
    public static Map<Integer, Person> findByPrimaryKey(String primaryKey) {             //refactor to return a Map so PersonSearch can handle same return types.  better? or worse? PersonDataTransport would fix...

        final Map<Integer, Person> persons = new HashMap<Integer, Person>();
        String query = prepareSearchByPrimaryKey(primaryKey);
        LOGGER.log(Level.TRACE, "executing:    " + query);

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
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception.getMessage(), exception);
            }
        }

        return persons;
    }

    /**
     * Find a Person record by name or name fragment.
     * <p>
     * Note that more results can be returned as the input name fragment gets less specific.
     *
     * @param name the name--or portion of the name--of the person you seek.
     * @return a Map where values hold a Person and the key contains its corresponding primary key (as an Integer).
     */
    public static Map<Integer, Person> findByName(String name) {

        final Map<Integer, Person> persons = new HashMap<Integer, Person>();
        String query = prepareSearchByName(name);
        LOGGER.log(Level.TRACE, "executing:    " + query);

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
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception.getMessage(), exception);
            }
        }

        LOGGER.log(Level.TRACE, "loaded up " + persons.size() + " people");

        return persons;
    }

    /**
     * Select all of the Person records available.
     * <p>
     * As of version 1.0, no business logic layer uses this, but it is used in unit test case(s).
     *
     * @return a List of Person objects.
     */
    public static List<Person> findAll() {

        final List<Person> persons = new ArrayList<Person>();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(getSelectAllSQL().toString());

                while (resultset.next()) {
                    final Person person = new Person();
                    person.setFirstName(resultset.getString("first_name"));
                    person.setLastName(resultset.getString("last_name"));
                    person.setFullName(resultset.getString("full_name"));

                    persons.add(person);
                }

            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception.getMessage(), exception);
            }
        }


        return persons;
    }


    /**
     * Builds the base SQL query without a WHERE clause.
     *
     * @return a StringBuilder with a "SELECT * FROM 'table_name' " format value.
     */
    private static StringBuilder getSelectAllSQL() {

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
    private static String prepareSearchByPrimaryKey(String primaryKey) {

        StringBuilder statement = getSelectAllSQL();
        statement.append("WHERE " + PRIMARY_KEY_FIELDNAME + " = " + primaryKey);

        return statement.toString();
    }

    /**
     * Prepare the WHERE clause for a Search by Name.
     * The REGEXP operator is used so that records can still be found evenif only a portion of the name is entered.
     *
     * @param name the name--or portion of the name--of the person you seek.
     * @return a StringBuilder with "WHERE 'full_name' REGEXP 'name'" format value.
     */
    private static String prepareSearchByName(String name) {

        StringBuilder statement = getSelectAllSQL();
        statement.append("WHERE " + FULL_NAME_FIELDNAME + " ");
        statement.append("LIKE " + "'%" + name + "%'");
//        statement.append("REGEXP " + "'" + name + "'");       //@todo: drats, REGEXP isn't supported in SQLite...hmmm, how to refactor then for different statements..ideally stored procedures; out of scope for this exercise?....

        return statement.toString();
    }



    /**
     * Sets the database implementation into PersonDAO and then sets the Connection.
     *
     * @param database the database in which the data resides for searches of type Person.
     */
    public static void setDatabase(Database database) {
        PersonDAO.database = database;

        setConnection(database.getConnection());
    }

    /**
     * Sets the Connection on the database that this PersonDAO has.
     *
     * @param connection the Connection to set.
     */
    private static void setConnection(Connection connection) {
        PersonDAO.connection = connection;
    }

}
