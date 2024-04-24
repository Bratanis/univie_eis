package org.omilab.geocoder.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.TimerTask;

import org.omilab.geocoder.model.Actor;

public class SimulationTimer extends TimerTask {

    @Override
    public void run() {
        Collection<Actor> actors = SimulationController.getInstance().getActors();
        Iterator<Actor> actorIterator = actors.iterator();
        while (actorIterator.hasNext()) {
            Actor actor = actorIterator.next();
            actor.updatePosition();
        }
        
    }
    
}
