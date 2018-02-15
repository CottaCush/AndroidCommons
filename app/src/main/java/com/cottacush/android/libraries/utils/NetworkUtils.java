package com.cottacush.android.libraries.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.cottacush.android.libraries.utils.factories.SystemServicesFactory;

public final class NetworkUtils {

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        ConnectivityManager cm = SystemServicesFactory.connectivityManager(context);
        return cm != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = SystemServicesFactory.connectivityManager(context);
        return cm != null && cm.getActiveNetworkInfo().isConnected();
    }

}
