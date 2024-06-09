package org.omilab.portal_service.model;


public class SpendingData {
    private String gender;
    private int age;
    private int districtNr;
    private String attractionName;
    private double moneySpent;

    // Constructor
    public SpendingData( int age, String attractionName, String gender, int districtNr, double moneySpent) {
        this.age = age;
        this.attractionName = attractionName;
        this.gender = gender;
        this.districtNr = districtNr;
        this.moneySpent = moneySpent;
    }

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

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

}
