package com.example.weedwackerapp;

import static com.google.android.material.tabs.TabLayout.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weedwackerapp.Contract.CheckFormContract;
import com.example.weedwackerapp.Controller.MyBean;
import com.example.weedwackerapp.Controller.ServiceLogin;
import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.Model.User;
import com.example.weedwackerapp.Presenter.CheckFormPresenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements CheckFormContract.View {

    private EditText email,password;
    private Button login;
    private CheckFormPresenter checkFormPresenter;
    private MyBean bean;
    private ServiceLogin serviceLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.buttonLogin);
        checkFormPresenter =new CheckFormPresenter(this);




        //------------------
        login.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                checkFormPresenter.checkForm(email,password);
            }
        });

    }

    @Override
    public void showFormOk() {
        //Toast.makeText(this, "Form dolduruldu", Toast.LENGTH_SHORT).show();
        //Bean Listener Token
        bean=new MyBean();
        bean.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // System.out.println("--- Bean listener----");
                //System.out.println("Property changed: " + evt.getPropertyName());
                //System.out.println("Old Value: " + evt.getOldValue());
                //System.out.println("New Value: " + evt.getNewValue());
                if(evt.getNewValue().toString().isEmpty()){
                   // System.out.println("Boş geldi");
                    Toast.makeText(MainActivity.this, "Kullanıcı adı veya Şiferniz yanlış", Toast.LENGTH_SHORT).show();
                }else{
                 Intent register=new Intent(MainActivity.this,RegisterActivity.class);
                    Register data=new Register();
                    data.setId(email.getText().toString());
                    data.setToken(evt.getNewValue().toString());
                    register.putExtra("data",data);
                    startActivity(register);

                }
            }
        });



        serviceLogin =new ServiceLogin(MainActivity.this);
        User user=new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        serviceLogin.PostLogin(user,bean);


    }

}