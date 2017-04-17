package com.example.annabujak.listazakupow;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;


public class MyPreferencesActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //add the prefernces.xml layout
        addPreferencesFromResource(R.xml.preferences);

        //get the specified preferences using the key declared in preferences.xml
        ListPreference dataPref = (ListPreference) findPreference("kolor");

        //get the description from the selected item
        dataPref.setSummary(dataPref.getEntry());

        //when the user choose other item the description changes too with the selected item
        }

//    public static class SettingsFragment extends PreferenceFragment
//            implements SharedPreferences.OnSharedPreferenceChangeListener {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//            addPreferencesFromResource(R.xml.preferences);
//        }
//
//        @Override
//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            if (key.equals("kolor")) {
//                Preference pref = findPreference(key);
//                pref.setSummary(sharedPreferences.getString(key, ""));
//            }
//        }
//    }

}