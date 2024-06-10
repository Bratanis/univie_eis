package org.omilab.portal_service.model;

import java.util.Date;

public class Event {
    private String name;
    private Date date;
    private String address;
    private String type;
    private Integer districtNr;

    public Event(String name, Date date, String address, String type, Integer districtNr) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.type = type;
        this.districtNr = districtNr;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDistrictNr() {
        return districtNr;
    }

    public void setDistrictNr(Integer districtNr) {
        this.districtNr = districtNr;
    }
}
