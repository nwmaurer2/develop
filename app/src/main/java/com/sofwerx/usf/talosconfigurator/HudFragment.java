package com.sofwerx.usf.talosconfigurator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


public class HudFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    ExpandableListView expandableListView;

    public HudFragment() {
        // Required empty public constructor
    }

//    public static HudFragment newInstance(int tabNumber) {
//        HudFragment fragment = new HudFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, tabNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hud, container, false);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.ExList1);


        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getActivity().getBaseContext());

        expandableListView.setAdapter(adapter);

        return rootView;
    }



}
