package com.app.fitness.Vista;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterCalendario extends FragmentPagerAdapter {

    public final List<Fragment> listFragments = new ArrayList<>();
    public final List<String> listFragmentsTitle = new ArrayList<>();

    public PagerAdapterCalendario(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        listFragments.add(fragment);
        listFragmentsTitle.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return listFragments.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listFragmentsTitle.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
