package com.example.weedwackerapp.ModelService;

import com.example.weedwackerapp.Contract.LoginContract;
import com.example.weedwackerapp.Model.User;

public class LoginModel implements LoginContract.Model {
    @Override
    public boolean login(User user) {
        return false;
    }
}
