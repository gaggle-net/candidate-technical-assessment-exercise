package com.polymathus.gaggle.service;

import com.polymathus.gaggle.persist.Database;
import com.polymathus.gaggle.persist.MySQLDatabase;
import com.polymathus.gaggle.persist.PersonDAO;
import com.polymathus.gaggle.persist.SQLiteDatabase;
import org.json.simple.JSONObject;

public class SearchContainer {

    public static JSONObject searchForPerson(JSONObject searchCriteria){

        //choose and inject database
        Database database = new MySQLDatabase();
//        Database database = new SQLiteDatabase();
        PersonDAO.setDatabase(database);

        //search for person                                             //refactor this for DI too
        Search personSearch = new PersonSearch();
        JSONObject results = personSearch.search(searchCriteria);

        return results;
    }
}
