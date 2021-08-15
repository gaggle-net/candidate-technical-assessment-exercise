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
    private static final String REGEX_PATTERN_IS_LETTERS_AND_SPACES = "^[a-zA-Z\\s]*$";     //need better regex to support our pals Jòsé (Cuervo) and Leo (D'Vinci) ; starting to look like this wants to be an enum :)

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

        String userInput = searchCriteria.get("personSearch").toString();           //gawd this is awful...make it better
        LOGGER.log(Level.TRACE, "yo! performing search! Going to look for ----> " + userInput);

        if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_DIGITS)) {
            Map<Integer, Person> searchResults = PersonDAO.findByPrimaryKey(userInput);
            setSearchResults(convertResultsToJson(searchResults));

        } else if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_LETTERS_AND_SPACES)) {
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
