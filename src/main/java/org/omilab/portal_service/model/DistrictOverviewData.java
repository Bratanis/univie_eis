package org.omilab.portal_service.model;

public class DistrictOverviewData {
    private int districtNr;
    private int totalVisits;
    private int totalMoneySpent;
    private double averageRating;

    // Constructor, getters, and setters
    public DistrictOverviewData(int districtNr, int totalVisits, int totalMoneySpent, double averageRating) {
        this.districtNr = districtNr;
        this.totalVisits = totalVisits;
        this.totalMoneySpent = totalMoneySpent;
        this.averageRating = averageRating;
    }

    public int getDistrictNr() {
        return districtNr;
    }

    public void setDistrictNr(int districtNr) {
        this.districtNr = districtNr;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(int totalVisits) {
        this.totalVisits = totalVisits;
    }

    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public void setTotalMoneySpent(int totalMoneySpent) {
        this.totalMoneySpent = totalMoneySpent;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
