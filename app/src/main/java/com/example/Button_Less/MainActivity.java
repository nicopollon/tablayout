package com.example.Button_Less;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        //english
        String en = "en";
        String it = "it";

        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String langPref = pref.getString(SettingsActivity.KEY_PREF_LANGUAGE, "0");

        if (langPref.equals("0")){
            setAppLocale(en);
        }
        else{
            setAppLocale(it);
        }




        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab2));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.bringToFront();


        viewPager = findViewById(R.id.pager);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(this);




    }

    public void selectedTab(int position) {

        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

            if (id == R.id.settings){
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
            return super.onOptionsItemSelected(item);
    }

    public void setAppLocale(String localCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(new Locale(localCode.toLowerCase()));

        }
        else {
            conf.locale =  new Locale(localCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }




}
