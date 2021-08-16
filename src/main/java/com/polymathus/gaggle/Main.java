package com.polymathus.gaggle;

import com.polymathus.gaggle.service.SearchContainer;
import org.apache.log4j.Level;
import org.json.simple.JSONObject;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        /*
        System.out.println("Welcome to Your Search !");
        System.out.println("Please enter a name, part of a name or the primary key of the record you seek.");
        System.out.print("What say you? ---> ");

        Scanner scanner = new Scanner (System.in);
        String userEntry = scanner.next().trim();

        JSONObject formInputJson = new JSONObject();
        formInputJson.put("personSearch", userEntry);
         */

        /*
        JSONObject input = new JSONObject();
        input.put("personSearch", "d'vinci");                           // all this is temporary till I craft the service endpoints --DONE!

        JSONObject output = SearchContainer.searchForPerson(input);
        LOGGER.log(Level.TRACE, "the final out output is:  "+output.toString());
        */
    }
}
