package org.omilab.geocoder.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

import org.omilab.geocoder.model.Actor;
import org.omilab.geocoder.model.Car;
import org.omilab.geocoder.model.Pedestrian;

public class SimulationController {

    private static SimulationController simulationController;
    Collection<Actor> actors = new ArrayList<Actor>();

    private SimulationController() {
    }

    public static SimulationController getInstance() {
        if (simulationController == null) {
            simulationController = new SimulationController();
            // update position every 5 seconds
            Timer timer = new Timer();
            timer.schedule(new SimulationTimer(), 0, 5000);
        }

        return simulationController;
    }

    public Collection<Actor> addActors(int numberOfCars, int numberOfPedestrians) {
        for (int i = 0; i < numberOfCars; i++) {
            actors.add(new Car());
        }
        for (int i = 0; i < numberOfPedestrians; i++) {
            actors.add(new Pedestrian());
        }
        return actors;
    }

    public Collection<Actor> addActors(int numberOfCars, int numberOfPedestrians, double longitude, double latitude, int radius) {
        for (int i = 0; i < numberOfCars; i++) {
            actors.add(new Car(longitude, latitude, radius));
        }
        for (int i = 0; i < numberOfPedestrians; i++) {
            actors.add(new Pedestrian(longitude, latitude, radius));
        }
        return actors;
    }

    public Collection<Actor> getActors() {
        return actors;
    }

    public Collection<Actor> emptyActors() {
        actors = new ArrayList<Actor>();
        return actors;
    }

}
