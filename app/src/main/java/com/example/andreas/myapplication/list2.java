package com.example.andreas.myapplication;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import us.feras.mdv.MarkdownView;

/**
 * Created by andreas on 12.05.16.
 */
public class list2 extends Fragment {

    String titl , bod , pi, da, fa, im, av, us;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        titl = null;
        bod = null;
        pi = null;
        da = null;
        fa = null;
        im = null;
        av =null;
        us =null;
        titl = this.getArguments().getString("tit");
        bod = this.getArguments().getString("bod");
        pi = this.getArguments().getString("pic");
        da = this.getArguments().getString("da");
        fa = this.getArguments().getString("fa");
        im = this.getArguments().getString("im");
        av =this.getArguments().getString("av");
        us =this.getArguments().getString("us");

        View v = inflater.inflate(R.layout.activity_list2, null);
        TextView Like = (TextView) v.findViewById(R.id.likes);
        TextView Wat = (TextView) v.findViewById(R.id.textView3);
        TextView Dat = (TextView) v.findViewById(R.id.textView4);
        TextView Zag = (TextView) v.findViewById(R.id.textView5);
        MarkdownView tRead = (MarkdownView) v.findViewById(R.id.textView6);
        ImageView imV = (ImageView) v.findViewById(R.id.imageView2);
        ImageView ava = (ImageView) v.findViewById(R.id.imageView3);

        Zag.setText(titl);
        tRead.loadMarkdown(bod);
        Like.setText("Likes: "+fa);
        Dat.setText("Post created by "+us+" at "+da);
        Wat.setText("Количество просмотров: "+im);



        String avat ="http://192.168.1.15:3000"+av;
        Picasso.with(this.getActivity()).load(pi).into(imV);
        Picasso.with(this.getActivity()).load(avat).into(ava);
        return v;
    }
}
