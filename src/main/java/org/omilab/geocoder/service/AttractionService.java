package org.omilab.geocoder.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/attractions")
public class AttractionService {


    //returns all attractions in the database (as json)
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAttractions() {
        return "All attractions";
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAttractionByName(@PathParam("name") String name) {
        return "Hello Attraction " + name;
    }

    //returns attraction by id
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAttractionById(@PathParam("id") String id) {
        return "Attraction with id " + id;
    }

    //returns all attractions with specified district number(s) - parameter is a list of 0 to n district numbers
    @GET
    @Path("/districts/{districts}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAttractionsByDistricts(@PathParam("districts") String districtStr) {

                // Convert comma-separated String to List of Integers for districts
                List<Integer> districts = null;
                if (districtStr != null && !districtStr.isEmpty()) {
                    districts = Arrays.stream(districtStr.split(","))
                                      .map(Integer::parseInt)
                                      .collect(Collectors.toList());
                }

        return List.of("Attractions in district " + districts, "More attractions in district " + districts);


    }

}


