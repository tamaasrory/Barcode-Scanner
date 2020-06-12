/*
 * SettingValue.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.presenter;

import android.app.Activity;
import android.preference.PreferenceManager;

/**
 * Created by asrory on 12/08/17.
 */

public class SettingValue {
    public static int getIntValue(Activity activity, String key) {
        return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(activity).getString(key, ""));
    }

    public static double getDoubleValue(Activity activity, String key) {
        return Double.parseDouble(PreferenceManager.getDefaultSharedPreferences(activity).getString(key, ""));
    }

    public static boolean getBoolValue(Activity activity, String key) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(key, true);
    }
}
