package com.example.weedwackerapp.Contract;

import android.widget.EditText;

public interface CheckFormContract {

    //interface fro the View
    interface View{
        void showFormOk();
    }

    //interface fro the Presenter
    interface Presenter{
        void checkForm(EditText email,EditText password);
    }

    //interface fro the model
    interface Model{
        boolean checkModel(EditText email,EditText password);
    }
}
