package org.omilab.portal_service.model;

public class AttractionOverview {
    private int districtNr;
    private int id;
    private String name;
    private Integer totalVisits; // Using Integer to allow null for top-rated query
    private Double averageRating; // Using Double to allow null for top-visited query

    public AttractionOverview(int districtNr, int id, String name, Integer totalVisits, Double averageRating) {
        this.districtNr = districtNr;
        this.id = id;
        this.name = name;
        this.totalVisits = totalVisits;
        this.averageRating = averageRating;
    }

    // Getters and setters
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

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public int getDistrictNr() {
        return districtNr;
    }

    public void setDistrictNr(int districtNr) {
        this.districtNr = districtNr;
    }
}
