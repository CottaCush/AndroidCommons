package com.cottacush.android.libraries.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

public class MessageUtils {

    /**
     * @param view
     * @param message
     */
    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * @param view
     * @param message
     */
    public static void showMessageIndef(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).show();
    }

    /**
     * @param view
     * @param message
     * @param action
     * @param listener
     */
    public static void showMessage(View view, String message, String action, View.OnClickListener listener, int colorId) {
        Context context = view.getContext();
        Snackbar snackbar = Snackbar.make(view, message
                , Snackbar.LENGTH_LONG)
                .setAction(action, listener)
                .setActionTextColor(Color.WHITE);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(
                context.getResources().getColor(colorId));
        snackbar.show();
    }

}