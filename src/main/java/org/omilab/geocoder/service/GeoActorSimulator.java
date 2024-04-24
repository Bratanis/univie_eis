package org.omilab.geocoder.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omilab.geocoder.controller.SimulationController;
import org.omilab.geocoder.model.Actor;
import org.omilab.geocoder.model.Car;
import org.omilab.geocoder.model.Pedestrian;

import io.swagger.annotations.Api;

@Path("/geocode_simulate")
// API is required, for the UI to recognize this class as annotated for swagger
@Api(tags = { "Geocoder Simulation Operation" })
public class GeoActorSimulator {
    private Logger log = LogManager.getLogger(GeoActorSimulator.class);
    SimulationController simulationController = SimulationController.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("initialize_simulation")
    public Collection<Actor> initSimulation(@QueryParam("numberOfCars") int numberOfCars,
            @QueryParam("numberOfPedestrians") int numberOfPedestrians) {
        log.info("constructing the actors");

        return simulationController.addActors(numberOfCars, numberOfPedestrians);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("initialize_simulation_with_position")
    public Collection<Actor> initialize_simulation_with_position(
        @QueryParam("numberOfCars") int numberOfCars,
        @QueryParam("numberOfPedestrians") int numberOfPedestrians,
        @QueryParam("longitude") double longitude,
        @QueryParam("latitude") double latitude,
        @QueryParam("radius") int radius)
        {
        log.info("constructing the actors");

        return simulationController.addActors(numberOfCars, numberOfPedestrians, longitude, latitude, radius);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get_actors")
    public Collection<Actor> getActors() {
        log.info("get the actors");
        return simulationController.getActors();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("empty_simulation")
    public Collection<Actor> emptySimulation() {
        log.info("empty the actors list");
        return simulationController.emptyActors();
    }

}
