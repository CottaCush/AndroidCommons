package com.cottacush.android.libraries;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.view.View;

import com.cottacush.android.libraries.utils.PrefsUtils;
import com.cottacush.android.libraries.utils.ViewUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;

import static org.junit.Assert.assertEquals;

/**
 * Created by rasheed on 11/22/17.
 */
@RunWith(AndroidJUnit4.class)

public class ViewUtilsInstrumentationTest {


    Context context;
    View view;
    View view1;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getContext();
        view = new View(context);
        view1 = new View(context);

    }


    @Test
    public void show() throws Exception {
        view.setVisibility(View.INVISIBLE);
        ViewUtils.show(view);
        assertEquals(View.VISIBLE , view.getVisibility());

    }

    @Test
    public void hide() throws Exception {
        view.setVisibility(View.VISIBLE);
        ViewUtils.hide(view);
        assertEquals(View.GONE , view.getVisibility());
    }

    @Test
    public void invisible() throws Exception {
        view.setVisibility(View.VISIBLE);
        ViewUtils.invisible(view);
        assertEquals(View.INVISIBLE , view.getVisibility());
    }

    @Test
    public void convertDpToPixels() throws Exception {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int expected = Math.round(metrics.density * 20f);
        int actual = ViewUtils.convertDpToPixels(20);
        assertEquals(expected, actual);
    }

    @Test
    public void adjustGridViewSizeBasedOnData() throws Exception {

    }

    @Test
    public void makeTranslucentStatusBar() throws Exception {

    }

    @Test
    public void showManyViews() throws Exception {
        view.setVisibility(View.GONE);
        view1.setVisibility(View.GONE);
        ViewUtils.show(view , view1);
        assertEquals(View.VISIBLE , view.getVisibility());
        assertEquals(View.VISIBLE , view1.getVisibility());
    }

    @Test
    public void hideManyViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);
        ViewUtils.hide(view , view1);
        assertEquals(View.GONE , view.getVisibility());
        assertEquals(View.GONE , view1.getVisibility());
    }

    @Test
    public void invisibleManyViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);
        ViewUtils.invisible(view , view1);
        assertEquals(View.INVISIBLE , view.getVisibility());
        assertEquals(View.INVISIBLE , view1.getVisibility());
    }

}
