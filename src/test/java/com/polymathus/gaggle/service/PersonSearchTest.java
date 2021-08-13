package com.polymathus.gaggle.service;

import com.polymathus.gaggle.persist.Database;
import com.polymathus.gaggle.persist.PersonDAO;
import com.polymathus.gaggle.persist.SQLiteDatabase;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSearchTest {

    /*
        Things I want to test and/or build for:

        --- from the requirements specifically:
            - accepts a number; subsequently searches on Primary Key
            - accepts a string; subsequently searches for person with all or part of that input
            - input and output is well-formed JSON

        --- additional thoughts and 'of course ya should' tests:
            - doesn't fail (fatally) on empty string search
            - doesn't fail (fatally) on all symbols (sorry, Prince--next release!)

        --- more thoughts--and questions at the moment--to consider:
            - what is returned when no records found?       @todo:  still need to handle this
            - what is returned on invalid inputs? or just no execute for now/first pass...
            - @todo: what does user input JSON look like? ie: what is key?
     */
//    private static Database mySqlDatabase = new MySQLDatabase();
    private static Database database = new SQLiteDatabase();

    @Test
    public void testOutputIsWellFormedJsonForFindByPrimaryKey() {
        PersonDAO.setDatabase(database);

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("personSearch", "700");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("700","Bruce Wayne");

        Search personSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personSearch.search(userSearchInput)).toJSONString());
    }

    @Test
    public void testOutputIsWellFormedJsonForFindByName() {
        PersonDAO.setDatabase(database);

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("personSearch", "Bruce Wayne");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("700","Bruce Wayne");

        Search personSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personSearch.search(userSearchInput)).toJSONString());
    }

    @Test
    public void testSearchHandlesEmptyStringInput() {

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("personSearch", "");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("personSearch","Invalid Input Received");

        PersonSearch personeSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personeSearch.search(userSearchInput)).toJSONString());
    }

    @Test
    public void testSearchHandlesInvalidCharacters() {

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("personSearch", "!@%$#");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("personSearch","Invalid Input Received");

        PersonSearch personeSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personeSearch.search(userSearchInput)).toJSONString());
    }
}