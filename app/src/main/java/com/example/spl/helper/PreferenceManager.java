package com.example.spl.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {
    private static String SPL_PREF = "com.spl.pro";
    private static String SPL_LANG = "com.spl.pro.lang";

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SPL_PREF, Context.MODE_PRIVATE);
    }

    public static String getCurrentLanguage(Context context) {
        return getPrefs(context).getString(SPL_LANG, "en");
    }

    public static void setCurrentLanguage(Context context, String value) {
        getPrefs(context).edit().putString(SPL_LANG, value).commit();
    }

    public static String getPatientName(String mStrSerNumber, Context context) {
        return getPrefs(context).getString(mStrSerNumber, "0");
    }

    public static void setPatientName(Context context,String mStrSerNumber, String value) {
        getPrefs(context).edit().putString(mStrSerNumber, value).commit();
    }

}