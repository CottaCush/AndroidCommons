package com.cottacush.android.libraries;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringDef;
import android.support.annotation.StyleRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.Display;
import android.view.View;

import com.cottacush.android.libraries.utils.HttpResponseUtils;
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


    String exampleJsonResponseDataAsObjectString = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"data\": {\n" +
            "    \"access_token\": \"36a378c54edbdf61c4e004fa4c06f841ac6a3b57\",\n" +
            "    \"expires_in\": 21600,\n" +
            "    \"token_type\": \"Bearer\",\n" +
            "    \"scope\": null\n" +
            "  }\n" +
            "}\n";
    String exampleErrorResponseString = "{\n" +
            "  \"status\": \"error\",\n" +
            "  \"message\": \"The access token provided has expired\",\n" +
            "  \"code\": \"E0004\"\n" +
            "}";

    String exampleResponseDataAsArrayString = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"name\": \"12-17\",\n" +
            "      \"key\": \"12-17\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"18-24\",\n" +
            "      \"key\": \"18-24\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    HttpResponseUtils responseUtilsWithObjectBody;
    HttpResponseUtils responseUtilsWithArrayBody;

    @Before
    public void setUp() throws Exception {
        JsonElement successObjectBodyElement = new JsonParser().parse(exampleJsonResponseDataAsObjectString);

        JsonElement successArrayBodyElement = new JsonParser().parse(exampleResponseDataAsArrayString);
        responseUtilsWithArrayBody = new HttpResponseUtils(successArrayBodyElement, null);

        ResponseBody responseBody =    ResponseBody.create(
                MediaType.parse("application/json"),
                exampleErrorResponseString
        );
        responseUtilsWithObjectBody = new HttpResponseUtils(successObjectBodyElement, responseBody);


    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
    /*    Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.cottacush.android.libraries", appContext.getPackageName());*/

    }



    @Test
    public void getErrorMessage() throws Exception {
        String expected = "The access token provided has expired";
        String actual = responseUtilsWithObjectBody.getErrorMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void getCode() throws Exception {
        String expected = "E0004";
        String actual = responseUtilsWithObjectBody.getCode();
        assertEquals(expected , actual);
    }


}
