package com.example.weedwackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class RegisterActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent i=getIntent();
        if(i.getExtras()!=null){
            Register register=(Register)i.getSerializableExtra("data");
            System.out.println("Register Activity");
            System.out.println(register.getId());
            System.out.println(register.getToken());
        }

        tabLayout= findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        myViewPagerAdapter=new MyViewPagerAdapter(this,tabLayout.getTabCount());
        viewPager.setAdapter(myViewPagerAdapter);




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());;
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());;
            }
        });
    }
}