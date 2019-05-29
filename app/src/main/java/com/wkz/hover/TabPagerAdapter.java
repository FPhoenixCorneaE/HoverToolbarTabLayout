package com.wkz.hover;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;

    public TabPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return null != fragments ? fragments.size() : 0;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null != titles ? titles.get(position) : super.getPageTitle(position);
    }
}
