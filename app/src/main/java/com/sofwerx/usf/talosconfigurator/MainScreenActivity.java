package com.sofwerx.usf.talosconfigurator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
//from hud-btn-config-nav
import android.support.v4.app.FragmentTransaction;
//end from hud-btn-config-nav
//from BtnConfigDev
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
//end from BtnConfigDev
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static SectionsPagerAdapter mSectionsPagerAdapter;
    static final String SUIT_MODES = "suit_modes";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;
    private static ArrayList<CharSequence> mModes;
    private ArrayList<Fragment> modeFragments;
    private TabLayout tabLayout;
    private Button deleteTabBtn, renameModeBtn, buttonsConfig;
   // private boolean btnConfigEnabled;


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
        mModes.add("1");
        mModes.add("2");
        mModes.add("3");

        //btnConfigEnabled = false;

        setContentView(R.layout.activity_main_screen);

        // Create the adapter that will return a fragment for each of the four
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Disables swipe paging
        // https://stackoverflow.com/questions/9650265/how-do-disable-paging-by-swiping-with-finger-in-viewpager-but-still-be-able-to-s/13392198#13392198
        mViewPager.beginFakeDrag();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager, true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Hide the delete mode button and rename mode button when on the File Screen
                if (tab.getPosition() == 0) {
                    deleteTabBtn.setVisibility(View.INVISIBLE);
                    renameModeBtn.setVisibility(View.INVISIBLE);
                   // buttonsConfig.setVisibility(View.INVISIBLE);

                } else {
                    deleteTabBtn.setVisibility(View.VISIBLE);
                    renameModeBtn.setVisibility(View.VISIBLE);
                   // buttonsConfig.setVisibility(View.VISIBLE);
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
                mModes.add("" + mModes.size()); //This will create modes with duplicate names if a mode is added (say 4), then a preceding mode is deleted (say 3), and another mode is added (will be named 4 as well).
                mSectionsPagerAdapter.notifyDataSetChanged();

                // Goto the new tab
                mViewPager.setCurrentItem(mModes.size());
            }
        });

        // Delete Tab Button
        deleteTabBtn = (Button) findViewById(R.id.btnDelTab);
        deleteTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prompt the user if they are sure they want to delete the mode
                PromptDelete del = PromptDelete.newInstance("Delete Mode?");
                FragmentManager fm = getSupportFragmentManager();
                del.show(fm, "DEL");
            }
        });

        // Rename Tab Button
        renameModeBtn = (Button) findViewById(R.id.btnRename);
        renameModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Popup a dialog for the user to type in a new name
                PromptRename rename = PromptRename.newInstance("Rename Mode");
                FragmentManager fm = getSupportFragmentManager();
                rename.show(fm, "Rename");
                // Set the tab name
            }
        });

        // Buttons Config button
//        buttonsConfig = (Button) findViewById(R.id.button_config);
//        buttonsConfig.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("main activity", "buttons config clicked");
//                btnConfigEnabled = !btnConfigEnabled;
//
//            }
//        });

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

    public void renameTab(CharSequence newName, int tabNumber) {
        if( tabNumber > 0){
            mModes.set(tabNumber, newName);
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
            if(position == 0) {
                return FileTabFragment.newInstance(position + 1);
            } else {
                return ModesTabFragment.newInstance(position - 1);
            }

        }

        @Override
        public int getCount() {
            return mModes.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mModes.get(position);
        }
    }

    public static class PromptDelete extends DialogFragment {
        public static PromptDelete newInstance(String title) {
            PromptDelete frag = new PromptDelete();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        // DELETE Mode dialog prompt
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder
            .setMessage("Delete this Mode?")
            .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Call the calling Activity's deleteTab function
                    MainScreenActivity main = (MainScreenActivity) getActivity();
                    main.deleteTab(main.tabLayout.getSelectedTabPosition());
                }
            })
            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            // Create and return the Dialog
            return builder.create();
        }
    }


    public static class PromptRename extends DialogFragment {
        public static PromptRename newInstance(String title) {
            PromptRename frag = new PromptRename();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        // Rename Mode dialog prompt
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            final EditText newName = new EditText(getActivity());
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder
                    .setMessage("Enter a new name for this mode: ")
                    .setView(newName)
                    .setPositiveButton("RENAME", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Call the calling Activity's deleteTab function
                            MainScreenActivity main = (MainScreenActivity) getActivity();
                            CharSequence name = newName.getText();
                            main.renameTab(name,main.tabLayout.getSelectedTabPosition());
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

            // Create and return the Dialog
            return builder.create();
        }
    }

}
