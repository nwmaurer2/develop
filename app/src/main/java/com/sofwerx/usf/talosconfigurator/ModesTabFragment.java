package com.sofwerx.usf.talosconfigurator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ModesTabFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public ModesTabFragment() {}

    public static ModesTabFragment newInstance(int tabNumber) {
        ModesTabFragment fragment = new ModesTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, tabNumber);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_modes_tab, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

}
