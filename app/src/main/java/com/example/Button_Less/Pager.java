package com.example.Button_Less;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    // numero di tabs

    int TabCount;

    public Pager(FragmentManager fm, int TabCount) {

        super(fm);

        this.TabCount = TabCount;
    }
    public Fragment getItem(int position){

        switch (position){
            case 0:
                Tab2 tab1 = new Tab2();
                return tab1;
            case 1:
                Tab1 tab2 = new Tab1();
                return tab2;

            default:
                return null;


        }

    }

    public int getCount(){
        return TabCount;
    }
}
