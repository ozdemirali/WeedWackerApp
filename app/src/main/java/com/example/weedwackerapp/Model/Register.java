package com.example.weedwackerapp.Model;

import java.io.Serializable;

public class Register implements Serializable {
    private static String id;
    private static String token;

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
