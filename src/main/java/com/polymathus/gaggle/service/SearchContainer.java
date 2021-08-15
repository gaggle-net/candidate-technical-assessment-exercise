package com.polymathus.gaggle.service;

import com.polymathus.gaggle.persist.Database;
import com.polymathus.gaggle.persist.MySQLDatabase;
import com.polymathus.gaggle.persist.PersonDAO;
import com.polymathus.gaggle.persist.SQLiteDatabase;
import org.json.simple.JSONObject;

/**
 * The SearchContainer takes care of setting up the search.
 * <p>
 * The injector method, searchFor<Thing>() creates a Database for a concrete implementation of the specific datasource
 * it is using and injects it into the DAO.  Then the concrete search implementation is created and performs the search
 * as a Search.
 */
public class SearchContainer {

    /**
     * This method is responsible for setting up a Person Search.
     *
     * @param searchCriteria a JSONObject representing the search criteria.
     * @return a JSONObject containing the results of the search.
     */
    public static JSONObject searchForPerson(JSONObject searchCriteria) {

        //choose and inject database
//        Database database = new MySQLDatabase();
        Database database = new SQLiteDatabase();
        PersonDAO.setDatabase(database);

        //search for person
        Search personSearch = new PersonSearch();
        JSONObject results = personSearch.search(searchCriteria);

        return results;
    }


//    and as an example of extensibility:
//
//    /**
//     * This method is responsible for setting up a Book Search.
//     *
//     * @param searchCriteria a JSONObject representing the search criteria.
//     * @return a JSONObject containing the results of the search.
//     */
//    public static JSONObject searchForBook(JSONObject searchCriteria) {
//
//        //choose and inject database
////        Database database = new MySQLDatabase();
//        Database database = new SQLiteDatabase();
//        PersonDAO.setDatabase(database);
//
//        //search for book
//        Search bookSearch = new BookSearch();
//        JSONObject results = bookSearch.search(searchCriteria);
//
//        return results;
//    }


}
