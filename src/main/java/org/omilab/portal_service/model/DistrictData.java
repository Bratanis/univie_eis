package org.omilab.portal_service.model;

public class DistrictData {
    private int districtNr;
    private String description;

    public DistrictData(int districtNr, String description) {
        this.districtNr = districtNr;
        this.description = description;
    }

    public int getDistrictNr() {
        return districtNr;
    }

    public void setDistrictNr(int districtNr) {
        this.districtNr = districtNr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
