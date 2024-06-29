package com.example.weedwackerapp.Model;

public class Work {
    private int plateCode;
    private int districtId;
    private String  description;
    private String image;
    private String userId;

    public int getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(int plateCode) {
        this.plateCode = plateCode;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
