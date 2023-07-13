package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weedwackerapp.fragments.AddWorkFragment;
import com.example.weedwackerapp.fragments.HomeFragment;
import com.example.weedwackerapp.fragments.SettingFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    int totalTabs;

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,int totalTabs) {
        super(fragmentActivity);
        this.totalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new HomeFragment();
            case  1:
                return  new AddWorkFragment();
            case  2:
                return  new SettingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
