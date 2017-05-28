package com.edricchan.myfirstgame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by edricchan on 23/5/17.
 */

public class AppPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        addPreferencesFromResource(R.xml.preferences);
        CheckBoxPreference showAchDialog = (CheckBoxPreference) findPreference("show_achdialog");
//        showAchDialog.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                /**
//                 * @todo Update this
//                 */
//                super.onPre
//            }
//        });
    }
}
