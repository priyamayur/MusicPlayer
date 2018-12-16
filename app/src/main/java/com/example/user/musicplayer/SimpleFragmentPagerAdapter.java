package com.example.user.musicplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"ENGLISH", "HINDI", "BENGALI"};

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new EnglishFragment();
        } else if (position == 1) {
            return new HindiFragment();
        } else {
            return new BengaliFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}