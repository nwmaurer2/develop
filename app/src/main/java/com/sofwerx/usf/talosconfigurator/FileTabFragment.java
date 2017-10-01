package com.sofwerx.usf.talosconfigurator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FileTabFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public FileTabFragment() {}

    public static FileTabFragment newInstance(int tabNumber) {
        FileTabFragment fragment = new FileTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, tabNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_tab, container, false);
    }


}
