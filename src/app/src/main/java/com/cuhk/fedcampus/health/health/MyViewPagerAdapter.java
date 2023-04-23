package com.cuhk.fedcampus.health.health;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cuhk.fedcampus.health.health.fragment.HomeFragment;
import com.cuhk.fedcampus.health.health.fragment.PostFragment;
import com.cuhk.fedcampus.health.health.fragment.ReportFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new ReportFragment();
            case 1:
                return new PostFragment();
            case 2:
                return new HomeFragment();
            default:
                return new ReportFragment();
        }
    }

    @Override
    public int getItemCount(){
        return 3;
    }
}
