package com.polymathus.gaggle;

import com.polymathus.gaggle.service.PersonSearch;
import org.json.simple.JSONObject;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

        JSONObject formInputAsJson = getSampleUserInputAsJson("Bruce Wagnere");

        PersonSearch personSearchService = new PersonSearch();
        JSONObject output = personSearchService.search(formInputAsJson);

        System.out.println(output.toString());
    }


    //assuming the user is entering freeform in a single field
    private static JSONObject getSampleUserInputAsJson(String userInput) {

        JSONObject formInputJson = new JSONObject();
        formInputJson.put("searchInput", userInput);

        return formInputJson;
    }
}
