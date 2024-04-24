package org.omilab.geocoder.model;

public class Pedestrian extends Actor{
    public static final String TYPE = "Pedestrian";

    public Pedestrian() {
        super();
        this.type = TYPE;
    }

    public Pedestrian(double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.type = TYPE;
    }
}
