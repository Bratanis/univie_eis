package org.omilab.portal_service.model;

public class DemographicData {
    private Integer age;
    private String gender;

    public DemographicData(Integer age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
