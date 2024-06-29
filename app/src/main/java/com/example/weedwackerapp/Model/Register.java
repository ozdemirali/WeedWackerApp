package com.example.weedwackerapp.Model;

import java.io.Serializable;

public class Register implements Serializable {
    private static String id;
    private static String token;
    private static String city;
    private static String district;
    private static String postCode;
    private static String addInfo;
    private static String phone;
    private static boolean apply=true;

    public static boolean getApply() {
        return apply;
    }

    public static void setApply(boolean apply) {
        Register.apply = apply;
    }

    public static String getPostCode() {
        return postCode;
    }

    public static void setPostCode(String postCode) {
        Register.postCode = postCode;
    }

    public static String getAddInfo() {
        return addInfo;
    }

    public static void setAddInfo(String addInfo) {
        Register.addInfo = addInfo;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Register.phone = phone;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Register.city = city;
    }

    public static String getDistrict() {
        return district;
    }

    public static void setDistrict(String district) {
        Register.district = district;
    }

    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getToken() {
        return token;
    }

    public  void setToken(String token) {
        this.token = token;
    }
}
