package com.example.weedwackerapp.ModelService;

import android.widget.EditText;

import com.example.weedwackerapp.Contract.CheckFormContract;

public class CheckFormModel implements CheckFormContract.Model {

    @Override
    public boolean checkModel(EditText email, EditText password) {
        if(email.getText().toString().isEmpty()){
            email.setError("Email Alanı Boş Geçilemez");
            return false;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Password Alanı Boş Geçilemez");
            return false;
        }
        return true;
    }
}
