package com.polymathus.gaggle.service;

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

    @Test
    public void testOutputIsWellFormedJsonForFindByPrimaryKey() {

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("searchInput", "700");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("700","Bruce Wayne");

        PersonSearch personeSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personeSearch.search(userSearchInput)).toJSONString());
    }

    @Test
    public void testOutputIsWellFormedJsonForFindByName() {

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("searchInput", "Bruce Wayne");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("700","Bruce Wayne");

        PersonSearch personeSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personeSearch.search(userSearchInput)).toJSONString());
    }

    @Test
    public void testSearchHandlesEmptyStringInput() {

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("searchInput", "");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("000","Invalid Input Received");

        PersonSearch personeSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personeSearch.search(userSearchInput)).toJSONString());
    }

    @Test
    public void testSearchHandlesInvalidCharacters() {

        JSONObject userSearchInput = new JSONObject();
        userSearchInput.put("searchInput", "!@%$#");

        JSONObject expectedOutput = new JSONObject();
        expectedOutput.put("000","Invalid Input Received");

        PersonSearch personeSearch = new PersonSearch();
        assertEquals(expectedOutput.toJSONString(), (personeSearch.search(userSearchInput)).toJSONString());
    }
}