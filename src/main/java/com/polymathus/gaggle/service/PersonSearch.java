package com.polymathus.gaggle.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Map;

import com.polymathus.gaggle.domain.Person;
import com.polymathus.gaggle.persist.PersonDAO;

/**
 * The concrete implementation of the Search for Person data.
 */
public class PersonSearch implements Search {

    private static final Logger LOGGER = Logger.getLogger(PersonSearch.class);

    private static final String REGEX_PATTERN_IS_DIGITS = "^\\d+$";
    private static final String REGEX_PATTERN_IS_LETTERS_AND_SPACES = "^[a-zA-Z\\s]*$";                                 //letters or spaces
    private static final String REGEX_PATTERN_IS_VALID_NAME = "^[a-zA-Z\\s'-]+$";                                       //letters, spaces, dashes, or single quotes
//    private static final String REGEX_PATTERN_IS_VALID_NAME = "^[^\\s][a-zA-Z'-]+([\\s][a-zA-Z'-]+)?$";                 //letters, dashes, or single quotes separated by single spaces only (this is getting too complicated to do here(?)


    //    private JSONObject searchCriteria = new JSONObject();
    private JSONObject searchResults = new JSONObject();


    /**
     * The specific implementation to search for a Person.
     *
     * @param searchCriteria a JSONObject containing the search criterion.
     * @return a JSONObject holding the results of the search.
     */
    @Override
    public JSONObject search(JSONObject searchCriteria) {

        String userInput = searchCriteria.get("personSearch").toString();
        LOGGER.log(Level.TRACE, "performing search! Going to look for ----> " + userInput);

        if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_DIGITS)) {
            Map<Integer, Person> searchResults = PersonDAO.findByPrimaryKey(userInput);
            setSearchResults(convertResultsToJson(searchResults));

        } else if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_VALID_NAME)) {        //can still make this better (ie: submitting " " still executes searchByName() and returns no results.  can use ...VALID_NAME = "^[^\\s][a-zA-Z'-]+([\\s][a-zA-Z'-]+)?$"; but...
            Map<Integer, Person> searchResults = PersonDAO.findByName(userInput);
            setSearchResults(convertResultsToJson(searchResults));

        } else {
            getSearchResults().put("personSearch", "Invalid Input Received");

        }

        return getSearchResults();
    }


    /**
     * Helper method to bundle up the search results map into JSON output.
     *
     * @param searchResults a Map<Integer, Person> of records returned from the Person table, keyed on Primary Key.
     * @return the search results in JSON format
     */
    private JSONObject convertResultsToJson(Map<Integer, Person> searchResults) {

        JSONObject jsonOutput = new JSONObject();

        if (!searchResults.isEmpty()) {
            for (Map.Entry<Integer, Person> entry : searchResults.entrySet()) {
                jsonOutput.put(entry.getKey(), entry.getValue().getFullName());
            }
        } else {
            jsonOutput.put("personSearch", "No Results Found");
        }

        return jsonOutput;
    }


    public JSONObject getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(JSONObject searchResults) {
        this.searchResults = searchResults;
    }

//    public JSONObject getSearchCriteria() {
//        return searchCriteria;
//    }

//    public void setSearchCriteria(JSONObject searchCriteria) {
//        this.searchCriteria = searchCriteria;
//    }

}
