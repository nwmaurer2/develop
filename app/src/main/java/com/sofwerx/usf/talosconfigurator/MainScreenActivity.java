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
import android.widget.Button;

import java.util.ArrayList;
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
    private ArrayList<CharSequence> mModes;
    private ArrayList<Fragment> modeFragments;
    private TabLayout tabLayout;
    private Button deleteTabBtn, renameModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mModes = (ArrayList<CharSequence>) savedInstanceState.getSerializable(SUIT_MODES);
        }
        else {
            mModes = new ArrayList<CharSequence>();
            modeFragments = new ArrayList<Fragment>();
        }
        mModes.add("FILE");
        mModes.add("ASSAULT");
        mModes.add("INFIL");
        mModes.add("RECON");

        setContentView(R.layout.activity_main_screen);

        // Create the adapter that will return a fragment for each of the seven
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Hide the delete mode button and rename mode button when on the File Screen
                if (tab.getPosition() == 0) {
                    deleteTabBtn.setVisibility(View.INVISIBLE);
                    renameModeBtn.setVisibility(View.INVISIBLE);
                } else {
                    deleteTabBtn.setVisibility(View.VISIBLE);
                    renameModeBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // New Tab Button
        Button newTabBtn = (Button) findViewById(R.id.btnNewTab);
        newTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModes.add("New Mode");
                mSectionsPagerAdapter.notifyDataSetChanged();

                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);

                // Goto the new tab
                mViewPager.setCurrentItem(mModes.size());
            }
        });

        // Delete Tab Button
        renameModeBtn = (Button) findViewById(R.id.btnDelTab);
        renameModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = tabLayout.getSelectedTabPosition();
                mViewPager.setCurrentItem(pos - 1);
                deleteTab(pos);
            }
        });

        // Rename Tab Button
        deleteTabBtn = (Button) findViewById(R.id.btnRename);
        deleteTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do work here
                // Popup a dialog for the user to type in a new name
                // Set the tab name
            }
        });

        // Start on the first mode tab
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(SUIT_MODES, mModes);
        super.onSaveInstanceState(savedInstanceState);
    }


    public void deleteTab(int pos) {
        if (pos > 0) {
            mModes.remove(pos);
            mSectionsPagerAdapter.notifyDataSetChanged();
        }
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
            if(position == 0) {
                return FileTabFragment.newInstance(position + 1);
            } else {
                return ModesTabFragment.newInstance(position - 1);
            }

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
