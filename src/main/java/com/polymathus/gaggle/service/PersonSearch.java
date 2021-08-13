package com.polymathus.gaggle.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.polymathus.gaggle.domain.Person;
import com.polymathus.gaggle.persist.PersonDAO;

public class PersonSearch implements Search {

    private static final Logger LOGGER = Logger.getLogger(PersonSearch.class);

    private static final String REGEX_PATTERN_IS_DIGITS = "^\\d+$";
    private static final String REGEX_PATTERN_IS_LETTERS_AND_SPACES = "^[a-zA-Z\\s]*$";     //need better regex to support our pals Jòsé (Cuervo) and Leo (D'Vinci) ; starting to look like this wants to be an enum :)

    private JSONObject searchResults = new JSONObject();


    /**
     * The specific implementation to search for a Person.
     *
     * @param searchCriteria a JSONObject containing the search criterion.
     * @return a JSONObject holding the results of the search.
     */
    @Override
    public JSONObject search(JSONObject searchCriteria) {

        /*
        @todo: this core search() takes a JSONObject and returns a JSONObject...
          better to return String of JSON instead? or handle conversion at client interface?
         */

        JSONObject output = new JSONObject();
        String userInput = searchCriteria.get("personSearch").toString();           //gawd this is awful...make it better

        LOGGER.log(Level.TRACE, "yo! performing search! Going to  look for ----> " + userInput);


        if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_DIGITS)) {
            LOGGER.log(Level.TRACE, "found a number!");


            Integer primaryKey = new Integer(userInput);
            Person person = PersonDAO.findByPrimaryKey(primaryKey);

            LOGGER.log(Level.TRACE, "We just found " + person.getFullName() + " by Primary Key!!!");

            output.put(primaryKey, person.getFullName());

        } else if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_LETTERS_AND_SPACES)) {                      //fancy this up with a better regEx for valid name strings (including apostrophes and all that with final else that stops because of malicious input
            LOGGER.log(Level.TRACE, "Found a name or name fragment");

            Map<Integer, Person> searchResults = PersonDAO.findByName(userInput);
            LOGGER.log(Level.TRACE, "I found " + searchResults.size() + " people with that search");

            Map<Integer, String> returnResults = new HashMap<Integer, String>();

            for (Map.Entry<Integer, Person> entry : searchResults.entrySet()) {                 //oof I gotta learn this one...just copied it for now, which i haven't done much at all so this is promising
                LOGGER.log(Level.TRACE, "key:  " + entry.getKey() + ",   fullName:  " + entry.getValue().getFullName());
                returnResults.put(entry.getKey(), entry.getValue().getFullName());
            }

            output.putAll(returnResults);

        } else {

            output.put("000", "Invalid Input Received");
        }

        return output;

    }
}
