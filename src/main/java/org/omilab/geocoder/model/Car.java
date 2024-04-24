package org.omilab.geocoder.model;

public class Car extends Actor{

    public static final String TYPE = "Car";

    public Car() {
        super();
        this.type = TYPE;
    }


    public Car(double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.type = TYPE;
    }


}
