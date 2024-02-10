package com.example.weedwackerapp;

import static com.google.android.material.tabs.TabLayout.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weedwackerapp.Contract.CheckFormContract;
import com.example.weedwackerapp.Presenter.CheckFormPresenter;
import com.google.android.material.tabs.TabLayout;

import com.example.weedwackerapp.adapter.MyViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements CheckFormContract.View {

    private EditText email,password;
    private Button login;
    private CheckFormPresenter checkFormPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.buttonLogin);
        checkFormPresenter =new CheckFormPresenter(this);

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFormPresenter.checkForm(email,password);
            }
        });

    }

    @Override
    public void showFormOk() {
        Toast.makeText(this, "Form dolduruldu", Toast.LENGTH_SHORT).show();
    }
}