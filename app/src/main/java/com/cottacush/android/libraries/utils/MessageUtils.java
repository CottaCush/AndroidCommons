package com.cottacush.android.libraries.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;


/**
 * Created by codedentwickler on 10/11/17.
 */


public class MessageUtils {

    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showMessageIndef(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).show();
    }

    public static void showMessage(View view, String message, String action, View.OnClickListener listener, int colorResId) {

        Context context = view.getContext();
        Snackbar snackbar = Snackbar.make(view, message
                , Snackbar.LENGTH_LONG)
                .setAction(action, listener)
                .setActionTextColor(Color.WHITE);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(
                context.getResources().getColor(colorResId));
        snackbar.show();
    }
}