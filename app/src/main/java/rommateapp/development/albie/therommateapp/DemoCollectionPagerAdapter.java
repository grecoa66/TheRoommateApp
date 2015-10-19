package rommateapp.development.albie.therommateapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Albert on 10/15/2015.
 */

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public DemoCollectionPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int i) {
        android.support.v4.app.Fragment  fragment = new DemoObjectFragment();
        Bundle args = new Bundle();
        args.putInt("whichTab", i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0://all current
                return "Current";
            case 1: //Me
                return "Me";
            case 2: //past
                return "Past";
            default:
                return "cheese";
        }
    }
}
