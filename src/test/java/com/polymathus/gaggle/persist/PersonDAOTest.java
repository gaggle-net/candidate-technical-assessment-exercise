package com.polymathus.gaggle.persist;

import com.polymathus.gaggle.domain.Person;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    /*
        Things I want to test and/or build for:

        --- from the requirements specifically:
            - search for "bruce" returns "Bruce Wayne" (and all bruces for that matter)
            - search for "wayne" returns "Bruce Wayne" (and all waynes for that matter)
            - search for "bru" returns "Bruce Wayne" (and all <...bru...>s for that matter)

            - entering a number that corresponds to Bruce Wayne's PK returns said Bruce

        --- additional thoughts:
            - search for "way" returns "Bruce Wayne" (and all <...way...>s for that matter)
            - ambiguous search string matching multiple people returns all (?)
            - searching for a non-existent PK fails elegantly (with feedback to user? what form does that take?)

        --- additional thoughts not in requirements but worth planning for if time permits:
            - search string with special characters (in names) is not rejected (Leonardo D'Vinci)
            - and a Person with corresponding name above is returned in searches {leonardo, leo, D', etc.}
     */

    @Test
    public void testSQLiteDatabaseConnectionAlive() {
        Database database = new SQLiteDatabase();
        Connection connection = database.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testDataExistsInPersonTable() {
        List<Person> personResults = PersonDAO.findAll();
        assertNotEquals(0, personResults.size());
    }

    @Test
    public void testFindByPrimaryKey() {
        Map<Integer, Person> personResults = PersonDAO.findByPrimaryKey("700");
        Person personActual = personResults.get(new Integer("700"));
        assertEquals("Bruce Wayne", personActual.getFullName());
    }

    @Test
    public void testFindByNameWithFullName() {
        Map<Integer, Person> personResults = PersonDAO.findByName("Bruce Wayne");
        Person personActual = personResults.get(new Integer("700"));
        assertEquals("Bruce Wayne", personActual.getFullName());
    }

    @Test
    public void testFindByNameWithNameFragment() {
        Map<Integer, Person> personResults = PersonDAO.findByName("Bru");
        Person personActual = personResults.get(new Integer("700"));
        assertEquals("Bruce Wayne", personActual.getFullName());
    }

    @Test
    public void testFindByNameWithNameContainingAccents() {
        Map<Integer, Person> personResults = PersonDAO.findByName("Jòsé Cuervo");
        Person personActual = personResults.get(new Integer("1100"));
        assertEquals("Jòsé Cuervo", personActual.getFullName());        //@todo: interesting, this condition fails at runtime
    }

    @Test
    public void testFindByNameWithNameContainingApostrophes() {
//        Map<Integer, Person> personResults = PersonDAO.findByName("Leonardo D'Vinci");
//        Person personActual = personResults.get(new Integer("1000"));
//        assertEquals("Leonardo D'Vinci", personActual.getFullName());
    }
}