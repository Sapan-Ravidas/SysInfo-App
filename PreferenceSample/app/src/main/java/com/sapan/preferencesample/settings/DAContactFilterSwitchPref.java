/*
package com.sapan.preferencesample.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreferenceCompat;

import com.sapan.preferencesample.R;

public class DAContactFilterSwitchPref extends SwitchPreferenceCompat {

    private Switch switchPref;

    private boolean mState; // To store the switch state
//    private DBHelper mDbHelper; // Database helper instance
    private Context mContext; //
    public DAContactFilterSwitchPref(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        mDbHelper = new DBHelper(context);
        setLayoutResource(R.layout.contact_filter_preference);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        TextView title = (TextView) holder.itemView.findViewById(R.id.contact_filtering_head);
        TextView subTitle = (TextView) holder.itemView.findViewById(R.id.contact_filtering_sub);
        switchPref = holder.itemView.findViewById(R.id.contact_filtering_switch);
       */
/* boolean isFilterEnabled = mDbHelper.getContactFilterStatus() == Consts.ContactFilteringStatus.ON;
        switchPref.setChecked(isFilterEnabled);
        mState = isFilterEnabled;*//*

        switchPref.setChecked(mState);
        switchPref.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mState = true;
                   */
/* if (mDbHelper.getContactFilterStatus() == Consts.ContactFilteringStatus.OFF) {
                        sendEventLog(mContext.getString(R.string.sender_screen_id),
                                mContext.getString(R.string.sa_event_id_ContactSelectionOnOffIn), 1);
                    }
                    mDbHelper.setContactFilterStatus(Consts.ContactFilteringStatus.ON);
                    mState = true;*//*

                } else {
                    */
/*if (mDbHelper.getContactFilterStatus() == Consts.ContactFilteringStatus.ON) {
                        sendEventLog(mContext.getString(R.string.sa_screen_id_DualMessenger),
                                mContext.getString(R.string.sa_event_id_ContactSelectionOnOff), 0);
                        // Show a popup or perform other actions
                        Intent in = new Intent(mContext, PopupActivity.class);
                        in.putExtra(Consts.IntentString.EXTRA_FROM_MY_APP, true);
                        in.putExtra(Consts.IntentString.EXTRA_POPUP_TYPE,
                                Consts.PopupType.TURN_OFF_CONTACT_FILTERING_POPUP);
                        mContext.startActivity(in);
                    }
                    mDbHelper.setContactFilterStatus(Consts.ContactFilteringStatus.OFF);
                    mState = false;*//*

                    mState = false;
                }
                Log.d("SAPAN", "oncheckedchange ="  + isChecked);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !(state instanceof Bundle)) {
            // Didn't save state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Restore the state from the Bundle
        Bundle bundle = (Bundle) state;
        Parcelable superState = bundle.getParcelable("superState");
        super.onRestoreInstanceState(superState);
        mState = bundle.getBoolean("switch_state", false);

        if (switchPref != null) {
            switchPref.setChecked(mState);
        }
        notifyChanged();
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !(state instanceof Bundle)) {
            // Didn't save state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Restore the state from the Bundle
        Bundle bundle = (Bundle) state;
        Parcelable superState = bundle.getParcelable("superState");
        super.onRestoreInstanceState(superState);
        mState = bundle.getBoolean("switch_state", false);
        setChecked(mState); // Update the switch state
    }
}
*/
package com.sapan.preferencesample.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreferenceCompat;

import com.sapan.preferencesample.R;

public class DAContactFilterSwitchPref extends SwitchPreferenceCompat {

    private Switch switchPref;
    private Context mContext;

    public DAContactFilterSwitchPref(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setLayoutResource(R.layout.contact_filter_preference);

        // Initialize the switch state from SharedPreferences
        if (isPersistent()) {
            setChecked(getPersistedBoolean(false)); // Default value is false
        }
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        Log.d("SAPAN", "onBindViewHolder called");

        TextView title = holder.itemView.findViewById(R.id.contact_filtering_head);
        TextView subTitle = holder.itemView.findViewById(R.id.contact_filtering_sub);
        switchPref = holder.itemView.findViewById(R.id.contact_filtering_switch);

        if (title == null || subTitle == null || switchPref == null) {
            Log.e("SAPAN", "One or more views are null in onBindViewHolder");
            return;
        }

        // Set the initial state of the switch from SharedPreferences
        switchPref.setChecked(getPersistedBoolean(false));

        switchPref.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Save the new state to SharedPreferences
                persistBoolean(isChecked);
                Log.d("SAPAN", "onCheckedChanged = " + isChecked);
            }
        });
    }
}