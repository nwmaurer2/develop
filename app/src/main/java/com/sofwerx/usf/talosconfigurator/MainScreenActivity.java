package com.sofwerx.usf.talosconfigurator;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainScreenActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static final String SUIT_MODES = "suit_modes";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private HashMap<Integer, CharSequence> mModes;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mModes = (HashMap<Integer, CharSequence>) savedInstanceState.getSerializable(SUIT_MODES);
        }
        else {
            mModes = new HashMap<Integer, CharSequence>();
        }
        mModes.put(0, "FILE");
        mModes.put(1, "ASSAULT");
        mModes.put(2, "INFIL");
        mModes.put(3, "RECON");

        setContentView(R.layout.activity_main_screen);

        // Create the adapter that will return a fragment for each of the seven
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModes.put(mModes.size(), "New Mode");
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(SUIT_MODES, mModes);
        super.onSaveInstanceState(savedInstanceState);
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0)
                return FileTabFragment.newInstance(position + 1);
            else if(position == 1)
                return ButtonsTabFragment.newInstance(position + 1);
            else
                return ModesTabFragment.newInstance(position - 1);
        }

        @Override
        public int getCount() {
            //return size of map
            return mModes.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //make a map<int, CharSequence>
            return mModes.get(position);
        }
    }
}
