package com.cottacush.android.libraries;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.support.annotation.StyleRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.Display;
import android.view.View;
import android.widget.GridView;

import com.cottacush.android.libraries.utils.HttpResponseUtils;
import com.cottacush.android.libraries.utils.PrefsUtils;
import com.cottacush.android.libraries.utils.RetrofitClient;
import com.cottacush.android.libraries.utils.TextUtils;
import com.cottacush.android.libraries.utils.ViewUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

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
    private  Context context;
    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useAppContext() throws Exception {

    }

}
