package com.polymathus.gaggle;

import com.polymathus.gaggle.service.PersonSearch;
import org.json.simple.JSONObject;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        //JSONObject formInputAsJson = getSampleUserInputAsJson("Bru");     //use a Scanner and get this input dynamically at runtime

        System.out.println("Welcome to Your Search !");
        System.out.println("Please enter a name, part of a name or primary key of the record you seek.");
        System.out.print("What say you? ---> ");

        Scanner scanner = new Scanner (System.in);
        String userEntry = scanner.next().trim();

        JSONObject formInputJson = new JSONObject();
        formInputJson.put("searchInput", userEntry);

        PersonSearch personSearchService = new PersonSearch();
        JSONObject output = personSearchService.search(formInputJson);

        System.out.println(output.toString());
    }
}
