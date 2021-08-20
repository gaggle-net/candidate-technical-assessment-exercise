package com.polymathus.gaggle.resources;

import com.polymathus.gaggle.service.SearchContainer;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Handles incoming requests for searches.
 *
 */
@Path("/search")
public class ApplicationResources {

    /**
     * Handles an incoming request during a Person Search.
     *
     * @param searchCriteria the URL Query param in the request
     * @return a Response containing the search results.
     */
    @GET
    @Path("/searchForPerson")
    public Response searchForPerson(@QueryParam("searchCriteria") String searchCriteria) {

        /* true implementation has JSON for both input and output, initializing would look like this:
        JSONObject input = new JSONObject(jsonInputString);
         */

        /* for the demo we will grab the name value pair from the query string and create the JSONObject */
        JSONObject input = new JSONObject();
        input.put("personSearch", searchCriteria);
        /* end: for demo */

        JSONObject output = SearchContainer.searchForPerson(input);

        return Response.status(Response.Status.OK)
                .entity("Your input was: "+input.toJSONString()+"<br> Your results are: "+output.toJSONString())
                .build();
    }
}
