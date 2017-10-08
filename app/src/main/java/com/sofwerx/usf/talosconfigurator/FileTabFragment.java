package com.sofwerx.usf.talosconfigurator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FileTabFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button btn_save;
    private EditText filename;

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
        View view = inflater.inflate(R.layout.fragment_file_tab, container, false);


        // Setup the view elements
        btn_save = (Button) view.findViewById(R.id.btn_save);
        filename = (EditText) view.findViewById(R.id.filename);

        // Set the onClickListener to the Fragment
        btn_save.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    // Override Parent Activity onClick so the Fragment can contain button logic
    @Override
    public void onClick(View v) {
        // Switch against the ID of the View clicked
        switch (v.getId()) {
            case R.id.btn_save:
                save(v);
                break;
            default:
                break;
        }
    }

    // When save button is pressed
    public void save(View view) {


        filename.setText("swiggity swooty");

    }

}
