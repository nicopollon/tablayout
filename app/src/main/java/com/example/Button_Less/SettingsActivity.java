package com.example.Button_Less;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    public static final String
            KEY_PREF_EXAMPLE_SWITCH = "example_switch";

    public static final String
            KEY_PREF_EDIT_FLOOR_NUMBER = "edit_floor_number";
    public static final String
            KEY_PREF_INTEGER = "integer_pref";
    public static final String
            KEY_PREF_LANGUAGE = "language";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);






        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();



    }

    @Override
    protected void onResume() {

            PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        super.onResume();

    }

    private void  getPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String myPref = prefs.getString(KEY_PREF_INTEGER, "32");

    }
}