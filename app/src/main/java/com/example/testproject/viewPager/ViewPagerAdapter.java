package com.example.testproject.viewPager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.testproject.Fragments.Login;
import com.example.testproject.Fragments.Registration;

public class ViewPagerAdapter extends FragmentPagerAdapter {
   private Context context;
   int totalCount;
    public ViewPagerAdapter(@NonNull FragmentManager fm,Context context, int totalCount) {
        super(fm);
        this.context = context;
       this.totalCount = totalCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Login login = new Login();
                return login;
            case 1:
                Registration registration = new Registration();
                return registration;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalCount;
    }
}
