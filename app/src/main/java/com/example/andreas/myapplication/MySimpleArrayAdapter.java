package com.example.andreas.myapplication;

/**
 * Created by andreas on 10.05.16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final String[] url;
    private final String[] syn;

    public MySimpleArrayAdapter(Context context, String[] values, String[] url, String[] syn) {
        super(context, R.layout.test, values);
        this.context = context;
        this.values = values;
        this.url = url;
        this.syn = syn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.test, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txV);
        TextView textSub = (TextView) rowView.findViewById(R.id.tsub);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);
        textSub.setText(syn[position]);
        Picasso.with(getContext()).load(url[position]).into(imageView);



        return rowView;
    }
}