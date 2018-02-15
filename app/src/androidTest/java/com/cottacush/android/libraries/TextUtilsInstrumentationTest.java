package com.cottacush.android.libraries;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.view.View;
import android.widget.EditText;

import com.cottacush.android.libraries.utils.TextUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextUtilsInstrumentationTest {

    private Context context;
    private View view;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getContext();
        view = new View(context);
    }

    @Test
    public void testCheckFields() {
        EditText editText = new EditText(context);
        editText.setText("");
        assertEquals(false, TextUtils.checkFields(editText));
        editText.setText("Something is here");
        assertEquals(true, TextUtils.checkFields(editText));
    }

    @Test
    public void testIsEmailValid() {
        String email = "example@cottacush.com";
        String irregularEmail = "example.com";
        boolean actual = TextUtils.isEmailValid(email);
        assertEquals(true, actual);
        assertEquals(false, TextUtils.isEmailValid(irregularEmail));
    }
}
