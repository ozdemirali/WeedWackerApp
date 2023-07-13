package com.example.weedwackerapp;

import static com.google.android.material.tabs.TabLayout.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import Adapter.MyViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout= findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        myViewPagerAdapter=new MyViewPagerAdapter(this,tabLayout.getTabCount());
        viewPager.setAdapter(myViewPagerAdapter);




        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());;
            }

            @Override
            public void onTabReselected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());;
            }
        });














    }
}