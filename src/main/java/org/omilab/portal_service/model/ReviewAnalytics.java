package org.omilab.portal_service.model;

public class ReviewAnalytics {
    private int rating;
    private java.sql.Timestamp visitTime;
    private String gender;
    private int age;
    private int districtNr;
    private String attractionName;

    // Constructor
    public ReviewAnalytics(int rating, java.sql.Timestamp visitTime, String gender, int age, int districtNr, String attractionName) {
        this.rating = rating;
        this.visitTime = visitTime;
        this.gender = gender;
        this.age = age;
        this.districtNr = districtNr;
        this.attractionName = attractionName;
    }

    // Getters and Setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public java.sql.Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(java.sql.Timestamp visitTime) {
        this.visitTime = visitTime;
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

    // Optionally, toString method for debugging
    @Override
    public String toString() {
        return "ReviewAnalytics{" +
               "rating=" + rating +
               ", visitTime=" + visitTime +
               ", gender='" + gender + '\'' +
               ", age=" + age +
                ", districtNr=" + districtNr +
                ", attractionName='" + attractionName + '\'' +
               '}';
    }
}
