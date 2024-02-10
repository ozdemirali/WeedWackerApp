package com.example.weedwackerapp.Presenter;

import com.example.weedwackerapp.Contract.LoginContract;
import com.example.weedwackerapp.Model.User;
import com.example.weedwackerapp.ModelService.LoginModel;


public class LoginPresenter implements LoginContract.Presenter {
    private  LoginContract.View _view;
    private LoginContract.Model _model;

    public LoginPresenter(LoginContract.View view){
        _view=view;
        _model=new LoginModel();
    }

    @Override
    public void onLogin(User user) {
         if(_model.login(user)){
             _view.showLoginSuccessMessage();
         }else{
             _view.showLoginUnSuccessMessage();
         }
    }
}
