package org.omilab.portal_service.model;

public class ReviewAnalytics {
    private int rating;
    private java.sql.Timestamp visitTime;
    private String gender;
    private int age;

    // Constructor
    public ReviewAnalytics(int rating, java.sql.Timestamp visitTime, String gender, int age) {
        this.rating = rating;
        this.visitTime = visitTime;
        this.gender = gender;
        this.age = age;
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

    // Optionally, toString method for debugging
    @Override
    public String toString() {
        return "ReviewAnalytics{" +
               "rating=" + rating +
               ", visitTime=" + visitTime +
               ", gender='" + gender + '\'' +
               ", age=" + age +
               '}';
    }
}
