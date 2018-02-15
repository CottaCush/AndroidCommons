package com.cottacush.android.libraries.utils.factories;

import android.content.Context;
import android.net.ConnectivityManager;

public class SystemServicesFactory {

    public static ConnectivityManager connectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}
