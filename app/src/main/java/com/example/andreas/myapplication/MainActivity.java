package com.example.andreas.myapplication;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, list.OnHeadlineSelectedListener {
    int leng;
    public String myurl, siteurl;
    list lis;
    list2 lis2;
    public static String LOG_TAG = "my_log";
    public static String LOG_POS = "pos_log";
    String dat;
    public String[] titleb, bodyb, picb, synb, favb, impb, datb, useb, avab, nav;
    public int[] z;
    FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        siteurl ="http://192.168.1.15:3000";
        myurl=siteurl+"/categories/newest.json";

        if (lis!=null) {
            fTrans = getFragmentManager().beginTransaction();
            fTrans.remove(lis);
            fTrans.commit();
        }

        if (lis2!=null) {
            fTrans = getFragmentManager().beginTransaction();
            fTrans.remove(lis2);
            fTrans.commit();
        }

        new ParseTask().execute();








        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            Log.d(LOG_POS, "popping backstack");
            /*if (lis!=null) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.remove(lis);
                fTrans.commit();
            }*/
            getFragmentManager().popBackStack();
        } else {
            Log.d(LOG_POS, "nothing on backstack, calling super");
            openQuitDialog();
            //super.onBackPressed();
        }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav1:

                myurl =siteurl+"/categories/1.json";
                new ParseTask().execute();
                break;
            case R.id.nav2:

                myurl =siteurl+"/categories/2.json";
                new ParseTask().execute();
                break;
            case R.id.nav3:

                myurl =siteurl+"/categories/3.json";
                new ParseTask().execute();
                break;
            case R.id.nav4:

                myurl =siteurl+"/categories/4.json";
                new ParseTask().execute();
                break;
            case R.id.nav5:

                myurl =siteurl+"/categories/5.json";
                new ParseTask().execute();
                break;
            case R.id.nav6:

                myurl =siteurl+"/categories/6.json";
                new ParseTask().execute();
                break;
            case R.id.nav7:

                myurl =siteurl+"/categories/7.json";
                new ParseTask().execute();
                break;
        }






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private class ParseTask extends AsyncTask<Void, Void, String> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL(myurl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJson = buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }
        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            // выводим целиком полученную json-строку

            JSONObject dataJsonObj = null;
            JSONObject userJson = null;
            try {
                dataJsonObj = new JSONObject(strJson);
                JSONArray info = dataJsonObj.getJSONArray("posts");
                leng =info.length();

                titleb= new String[leng];
                bodyb= new String[leng];
                picb= new String[leng];
                synb= new String[leng];
                favb= new String[leng];
                impb= new String[leng];
                datb= new String[leng];
                useb= new String[leng];
                avab= new String[leng];
                for (int i = 0; i < leng; i++) {
                    JSONObject friend = info.getJSONObject(i);
                    String title = friend.getString("title");
                    String body = friend.getString("body");
                    String picpre =friend.getString("avatarMediumUrl");
                    String pic = siteurl+picpre;
                    String syn = friend.getString("sinopsis");
                    String dat = friend.getString("createdAt");
                    String fav = friend.getString("favoritesCount");
                    String imp = friend.getString("impressions");

                    userJson = friend.getJSONObject("user");
                    String user =userJson.getString("username");
                    String ava = userJson.getString("avatarSmallUrl");
                    Log.d(LOG_TAG, "Title " + title);
                    Log.d(LOG_TAG, "Body: " + body);
                    Log.d(LOG_TAG, "Url for pic: " + pic);

                    synb[i]=syn;
                    datb[i]=dat;
                    favb[i]=fav;
                    impb[i]=imp;
                    titleb[i]=title;
                    bodyb[i]=body;
                    picb[i]=pic;
                    useb[i]=user;
                    avab[i]=ava;
                    Picasso.with(MainActivity.this).load(pic).fetch();
                    Picasso.with(MainActivity.this).load(ava).fetch();


                }
                Bundle bundle = new Bundle();
                bundle.putStringArray("tit",titleb);
                bundle.putStringArray("pic",picb);
                bundle.putStringArray("syn",synb);

                if (lis!=null) {

                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.remove(lis);
                    fTrans.commit();
                }

                if (lis2!=null) {
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.remove(lis2);
                    fTrans.commit();
                }

                fTrans = getFragmentManager().beginTransaction();
                lis = new list();
                lis.setArguments(bundle);
                fTrans.add(R.id.frag,lis);
               // fTrans.addToBackStack(null);
                fTrans.commit();










            } catch (JSONException e) {
                e.printStackTrace();

            }


        }
    }
    @Override
    public void onArticleSelected(int position) {
        int i = position;
        String titl = titleb[i];
        String bod = bodyb[i];
        String pi = picb[i];
        String da = datb[i];
        String fa = favb[i];
        String im = impb[i];
        String av =avab[i];
        String us =useb[i];
        DateFormat format = new SimpleDateFormat("dd, MM, yyyy");
        try {
            Date date = format.parse(da);
            Log.d(LOG_POS,"Date Format to String : "+ date);

            dat =  format.format(date);
            Log.d(LOG_POS,"Date Formatting : "+ dat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle bundle2 = new Bundle();
        bundle2.putString("tit",titl);
        bundle2.putString("pic",pi);
        bundle2.putString("bod",bod);
        bundle2.putString("da",dat);
        bundle2.putString("fa",fa);
        bundle2.putString("im",im);
        bundle2.putString("av",av);
        bundle2.putString("us",us);


        Log.d(LOG_POS, "____________________________________________________");
        Log.d(LOG_POS, "Title : " + titl);
        Log.d(LOG_POS, "Body : " + bod);
        Log.d(LOG_POS, "Picture : " + pi);
        Log.d(LOG_POS, "Date : " + da);
        Log.d(LOG_POS, "Avatar " + av);



        if (lis2!=null) {
            fTrans = getFragmentManager().beginTransaction();
            fTrans.remove(lis2);
            fTrans.commit();
        }

        fTrans = getFragmentManager().beginTransaction();
        lis2 = new list2();
        lis2.setArguments(bundle2);
        fTrans.replace(R.id.frag,lis2);
        fTrans.addToBackStack(null);


        fTrans.commit();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Таки да!",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();

            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }
}
