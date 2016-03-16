package me.chunsheng.hongbao.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.view.View;

import me.chunsheng.hongbao.R;
import me.chunsheng.hongbao.utils.UpdateTask;

/**
 * Created by Zhongyi on 1/19/16.
 * Settings page.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadUI();
        setPrefListeners();
    }

    private void setPrefListeners() {
        // Check for updates
//        Preference updatePref = findPreference("pref_etc_check_update");
//        updatePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            public boolean onPreferenceClick(Preference preference) {
//                new UpdateTask(getApplicationContext(), true).update();
//                return false;
//            }
//        });

        // 打开使用说明
        Preference issuePref = findPreference("pref_etc_issue");
        issuePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent settingsIntent = new Intent(SettingsActivity.this, WebViewActivity.class);
                startActivity(settingsIntent);
                return false;
            }
        });
    }

    private void loadUI() {
        addPreferencesFromResource(R.xml.preferences);

        // Get rid of the fucking additional padding
        getListView().setPadding(0, 0, 0, 0);
        getListView().setBackgroundColor(0xfffaf6f1);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void performBack(View view) {
        super.onBackPressed();
    }

    public void enterAccessibilityPage(View view) {
        Intent mAccessibleIntent =
                new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(mAccessibleIntent);
    }
}
