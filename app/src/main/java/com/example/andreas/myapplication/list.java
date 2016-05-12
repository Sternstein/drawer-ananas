package com.example.andreas.myapplication;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class list extends ListFragment {
    OnHeadlineSelectedListener mCallback;
    String [] titleb, picb, synb;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleb=null;
        picb=null;
        synb=null;
        titleb=this.getArguments().getStringArray("tit");
        picb=this.getArguments().getStringArray("pic");
        synb=this.getArguments().getStringArray("syn");

    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list, null);
        /*View view = inflater.inflate(R.layout.list, container, false);
        ListView lv = (ListView) view.findViewById(R.id.listview);
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getActivity(), titleb, picb, synb);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;*/
    }

    @Override
    public void onListItemClick(ListView lv, View view, int position, long id) {
        // Send the event to the host activity
        mCallback.onArticleSelected(position);
    }

}
