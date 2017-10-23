package com.sofwerx.usf.talosconfigurator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static com.sofwerx.usf.talosconfigurator.R.id.listofelements;


public class ModesTabFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String CURRENT_CONFIG = "current_config";
    private static final String TAG = "ModesTab";
    private static String configMode;
    private Button btnConfig;



    public ModesTabFragment() {}

    public static ModesTabFragment newInstance(int tabNumber) {
        ModesTabFragment fragment = new ModesTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, tabNumber);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_modes_tab, container, false);
        btnConfig = (Button) rootView.findViewById(R.id.button_config);
        btnConfig.setOnClickListener(this);
        configMode = getString(R.string.hud_mode);

        String[] menuitems2;
        menuitems2 = new String[]{"Do something",
                "Do Something else!",
                "Do another thing!",
                "Do",
                "It",
                "All",
                "The",
                "Time",
                "Every",
                "Night",
                "Every",
                "Day"};
        ListView listViewitems =  (ListView) rootView.findViewById(listofelements);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuitems2
        );

        listViewitems.setAdapter(listViewAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        HudFragment hud = new HudFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mode_container, hud).commit();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_config:
                //switch view from buttons_mode to hud_mode
                if(configMode == null || configMode.equals(getString(R.string.buttons_mode))){

                    HudFragment hudFragment = new HudFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.mode_container, hudFragment);
                    transaction.addToBackStack(null).commit();

                    btnConfig.setText(getString(R.string.buttons_mode));
                    configMode = getString(R.string.hud_mode);
                }

                //switch view from hud_mode to buttons_mode
                else if(configMode.equals(getString(R.string.hud_mode))) {

                    ButtonsTabFragment buttonsTabFragment = new ButtonsTabFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.mode_container, buttonsTabFragment);
                    transaction.addToBackStack(null).commit();

                    btnConfig.setText(getString(R.string.hud_mode));
                    configMode = getString(R.string.buttons_mode);
                }

                break;
        }

    }


}
