package com.cottacush.android.libraries;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private final String prefName = "TEST_PREF";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
    /*    Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.cottacush.android.libraries", appContext.getPackageName());*/

    }


    public void test_sharedPref(){
        Context mContext = InstrumentationRegistry.getContext();
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("key", "value");
        editor.apply();
        SharedPreferences mSharedPreferences2 = mContext.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        assertThat("value", is(mSharedPreferences2.getString("key", null)));
    }

}
