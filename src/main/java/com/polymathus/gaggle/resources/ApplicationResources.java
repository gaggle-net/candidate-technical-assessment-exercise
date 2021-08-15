package com.polymathus.gaggle.resources;

import com.polymathus.gaggle.service.SearchContainer;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/personsearch")
public class ApplicationResources {

    @GET
    @Path("/searchForPerson")
    public Response searchForPerson(@QueryParam("searchCriteria") String searchCriteria) {

        JSONObject input = new JSONObject();
        input.put("personSearch", searchCriteria);

        JSONObject output = SearchContainer.searchForPerson(input);

        return Response.status(Response.Status.OK).entity("Oi!  Your results are: "+output.toString()).build();

    }
}
