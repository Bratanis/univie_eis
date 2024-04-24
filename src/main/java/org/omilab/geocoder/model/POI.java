package org.omilab.geocoder.model;

import java.util.Date;

public class POI {
    String id;
    Double _long;
    Double lat;
    String name;
    String information;
    String readableAddress;
    Date startVisit;
    Date endVisit;

    public POI(String id, Double _long, Double lat, String name, String information, String readableAddress,
            Date startVisit, Date endVisit) {
        this.id = id;
        this._long = _long;
        this.lat = lat;
        this.name = name;
        this.information = information;
        this.readableAddress = readableAddress;
        this.startVisit = startVisit;
        this.endVisit = endVisit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLong() {
        return _long;
    }

    public void set_long(Double _long) {
        this._long = _long;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getReadableAddress() {
        return readableAddress;
    }

    public void setReadableAddress(String readableAddress) {
        this.readableAddress = readableAddress;
    }

    public Date getStartVisit() {
        return startVisit;
    }

    public void setStartVisit(Date startVisit) {
        this.startVisit = startVisit;
    }

    public Date getEndVisit() {
        return endVisit;
    }

    public void setEndVisit(Date endVisit) {
        this.endVisit = endVisit;
    }

}