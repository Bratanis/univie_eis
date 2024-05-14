package org.omilab.geocoder.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/events")
public class EventService {

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEventByName(@PathParam("name") String name) {
        return "Returns Event with name: " + name;
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEventById(@PathParam("id") String id) {
        return "Returns Event with id: " + id;
    }

    //returns all attractions in the database (as json)
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEvents() {
        return "Returns all events";
    }

    //returns all attractions with specified district number(s) - parameter is a list of 0 to n district numbers
    @GET
    @Path("/districts/{districts}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getEventsByDistricts(@PathParam("districts") List<Integer> districts) {
        return List.of("Events in districts " + districts, "More events in districts " + districts);
    }

}