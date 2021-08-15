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

        JSONObject input = new JSONObject();
        input.put("personSearch", searchCriteria);

        JSONObject output = SearchContainer.searchForPerson(input);

        return Response.status(Response.Status.OK).entity("Your results are: "+output.toString()).build();  //add error handling

    }
}
