package com.polymathus.gaggle.service;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.polymathus.gaggle.domain.Person;
import com.polymathus.gaggle.persist.PersonDAO;

/**
 *
 */
public class PersonSearch {

    private static final String REGEX_PATTERN_IS_DIGITS = "^\\d+$";
    private static final String REGEX_PATTERN_IS_LETTERS_AND_SPACES = "^[a-zA-Z\\s]*$";     //starting to look like this wants to be an enum :)

    private JSONObject searchResults = new JSONObject();


    public JSONObject search(JSONObject input){

        JSONObject output = new JSONObject();

        String userInput = input.get("searchInput").toString();           //gawd this feels awful...make it better

        System.out.println("yo! performing search! Going to  look for ----> "+userInput);



        //good ol' regEx to the rescue again, love it
        if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_DIGITS)){
            System.out.println("found a number!");

            Integer primaryKey = new Integer(userInput);
            Person person = PersonDAO.findByPrimaryKey(primaryKey);

            System.out.println("We just found "+person.getFullName()+" by Primary Key!!!");

            output.put(primaryKey, person.getFullName());

        } else if (!userInput.isEmpty() && userInput.matches(REGEX_PATTERN_IS_LETTERS_AND_SPACES)){                      //fancy this up with a better regEx for valid name strings (including apostrophes and all that with final else that stops because of malicious input
            System.out.println("Found a name or name fragment");

            Map<Integer, Person> searchResults = PersonDAO.findByName(userInput);
            System.out.println("I found "+searchResults.size()+" people with that search");

            Map<Integer, String> returnResults = new HashMap<Integer, String>();

            for (Map.Entry<Integer, Person> entry: searchResults.entrySet()) {                 //oof I gotta learn this one...just copied it for now, which i haven't done much at all so this is promising
                System.out.println("key:  "+entry.getKey()+",   fullName:  "+entry.getValue().getFullName());
                returnResults.put(entry.getKey(), entry.getValue().getFullName());
            }

            output.putAll(returnResults);

        } else {

            output.put("000","Invalid Input Received");
        }

        return output;

    }
}
