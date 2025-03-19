package com.sapan.preferencesample.settings;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.sapan.preferencesample.R;

public class DualAppSettingsFragement extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }

    @Override
    public boolean onPreferenceClick(@NonNull Preference preference) {
        Log.d("SAPAN", "preference is clicked");
        return true;
    }
}
