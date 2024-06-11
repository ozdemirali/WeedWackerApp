package com.example.weedwackerapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.fragments.AddWorkFragment;
import com.example.weedwackerapp.fragments.HomeFragment;
import com.example.weedwackerapp.fragments.SettingFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    int totalTabs;
    Register _register;
    Context _context;

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int totalTabs, Register register,Context context) {
        super(fragmentActivity);
        this.totalTabs=totalTabs;
        this._register=register;
        this._context=context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new HomeFragment(_register,_context);
            case  1:
                return  new AddWorkFragment(_context);
            case  2:
                return  new SettingFragment();
            default:
                return new HomeFragment(_register,_context);
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}