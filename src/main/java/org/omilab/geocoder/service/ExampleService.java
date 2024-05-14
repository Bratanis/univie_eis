package org.omilab.geocoder.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class ExampleService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getExample() {
        return "This is a test endpoint.";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting(@PathParam("name") String name) {
        return "Hello " + name;
    }

}