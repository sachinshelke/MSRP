package com.sachinshelke.msrp.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sachin K. Shelke on 05/03/17.
 */

public class MsrpSharedPref {

    private Context context;
    private static MsrpSharedPref mInstance = null;
    private static SharedPreferences preferences;

    private MsrpSharedPref(Context context) {
        this.context = context;

        preferences = context.getSharedPreferences("MSRP", Context.MODE_PRIVATE);
    }


    public synchronized static MsrpSharedPref init(Context context) {
        if (mInstance == null) {
            mInstance = new MsrpSharedPref(context);
        }
        return mInstance;
    }

    public static void addLoggedUser(String username) {
        preferences.edit().putString("username", username).apply();
    }

    public static String getLoggedUser() {
        return preferences.getString("username", null);
    }


}
