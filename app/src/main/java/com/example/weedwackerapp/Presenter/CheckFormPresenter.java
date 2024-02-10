package com.example.weedwackerapp.Presenter;

import android.widget.EditText;

import com.example.weedwackerapp.Contract.CheckFormContract;
import com.example.weedwackerapp.ModelService.CheckFormModel;


public class CheckFormPresenter implements CheckFormContract.Presenter {
    private CheckFormContract.View _view;
    private CheckFormContract.Model _model;

    public CheckFormPresenter(CheckFormContract.View view){
        _view=view;
        _model=new CheckFormModel();
    }

    @Override
    public void checkForm(EditText email, EditText password) {
        if(_model.checkModel(email,password)){
            _view.showFormOk();
        }
    }
}
