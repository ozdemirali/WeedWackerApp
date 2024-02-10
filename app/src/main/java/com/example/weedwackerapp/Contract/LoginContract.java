package com.example.weedwackerapp.Contract;

import com.example.weedwackerapp.Model.User;

public interface LoginContract {
    //interface fro the View
    interface View{
        void showLoginUnSuccessMessage();
        void showLoginSuccessMessage();
    }

    //interface fro the Presenter
    interface Presenter{
        void onLogin(User user);
    }

    //interface fro the Model
    interface Model{
        boolean login(User user);
    }
}
