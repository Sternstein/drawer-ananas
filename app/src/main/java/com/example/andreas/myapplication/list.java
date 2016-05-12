package com.example.andreas.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by andreas on 12.05.16.
 */
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class list extends ListFragment {

    String [] titleb, picb, synb;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getActivity(), titleb, picb, synb);

        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, null);
    }

}
