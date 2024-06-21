package org.omilab.portal_service.model;

import java.sql.Timestamp;

public class VisitTimeData {
    private String gender;
    private int age;
    private int districtNr;
    private String attractionName;
    private Timestamp visitTime;

    // Constructor
    public VisitTimeData(int age, String attractionName, String gender, int districtNr, Timestamp visitTime) {
        this.age = age;
        this.attractionName = attractionName;
        this.gender = gender;
        this.districtNr = districtNr;
        this.visitTime = visitTime;
    }

    // Getters and setters
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDistrictNr() {
        return districtNr;
    }

    public void setDistrictNr(int districtNr) {
        this.districtNr = districtNr;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }
}
