package com.sofwerx.usf.talosconfigurator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ButtonsTabFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public ButtonsTabFragment() {}

    public static ButtonsTabFragment newInstance(int tabNumber) {
        ButtonsTabFragment fragment = new ButtonsTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, tabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buttons_tab, container, false);
    }

}
