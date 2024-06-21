package org.omilab.portal_service.model;

public class Attraction {
    private int id;
    private String name;
    private String address;
    private int districtNr;

    public Attraction(int id, String name, String address, int districtNr) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.districtNr = districtNr;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictNr() {
        return districtNr;
    }

    public void setDistrictNr(int districtNr) {
        this.districtNr = districtNr;
    }
}
