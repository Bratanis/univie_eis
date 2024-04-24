package org.omilab.geocoder.model;

import java.util.Random;
import java.util.UUID;

import com.oblac.nomen.Nomen;

public abstract class Actor {
    String guid;
    String name;
    String type;

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getGuid() {
        return guid;
    }


    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Point getPosition() {
        return position;
    }


    public void setPosition(Point position) {
        this.position = position;
    }


    Point position;

    public Actor() {
        this.guid = UUID.randomUUID().toString();
        this.name = Nomen.randomName();
        this.position = getLocation(16.3738, 48.2082, 10000);
    }

    public Actor (double x, double y, int radius) {
        this.guid = UUID.randomUUID().toString();
        this.name = Nomen.randomName();
        this.position = getLocation(x, y, radius);
    }


    public Point getLocation(double x0, double y0, int radius) {
        Random random = new Random();
    
        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;
    
        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);
    
        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(y0);
    
        double foundLongitude = new_x + x0;
        double foundLatitude = y + y0;
        System.out.println("Longitude: " + foundLongitude + "  Latitude: " + foundLatitude );
        return new Point(foundLongitude, foundLatitude);
    }


    public void updatePosition() {
        if (type.equals(Car.TYPE)) {
            this.position = getLocation(position.longitude, position.latitude, 3000);
        }
        else if (type.equals(Pedestrian.TYPE)) {
            this.position = getLocation(position.longitude, position.latitude, 200);
        }   
    }
}
