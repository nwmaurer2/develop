package com.sofwerx.usf.talosconfigurator;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nicholas Maurer on 10/23/2017.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    String[] groupNames = {"Sports", "Computer", "Food", "Car", "TV"};

    String[][] childNames = {{"Boxing", "Kick Boxing", "Judo", "Football", "Basketball"},
                {"Desktop Computer", "Laptop Computer", "Smartphone Computer"},
                {"Ice cream", "Banana"}, {"Mercedes Benz"}, {"Samsung Tv", "LG TV"}};

    Context context;

    public ExpandableListViewAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupNames.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childNames[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupNames[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childNames[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View View, ViewGroup parent) {
        TextView txtView = new TextView(context);
        txtView.setText(groupNames[groupPosition]);
        txtView.setPadding(0,0,0,0);
        txtView.setTextColor(Color.BLUE);
        txtView.setTextSize(30);
        return txtView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View View, ViewGroup parent) {
        final TextView txtView = new TextView(context);
        txtView.setText(childNames[groupPosition][childPosition]);
        txtView.setPadding(0, 0, 0, 0);
        txtView.setTextColor(Color.RED);
        txtView.setTextSize(20);

        txtView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, txtView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return txtView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
