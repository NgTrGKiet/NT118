package com.example.lab6_4;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
public class VPAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmentTitle = new ArrayList<>();


    public VPAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("position" + position);
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        System.out.println("size" + fragmentArrayList.size());
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title){
        System.out.println("them fragment");
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        System.out.println("title" + fragmentTitle);
        return fragmentTitle.get(position);

    }
}