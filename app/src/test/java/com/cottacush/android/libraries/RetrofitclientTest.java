package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.RetrofitClient;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Rasheed on 11/23/17.
 */
//TODO come back and write more realistic tests
public class RetrofitclientTest {
    @Test
    public void testBuildWithParams() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("key", "value");
        String expected = "http://testurl.com/";
        Retrofit retrofit = new RetrofitClient().build(expected, params);
        String actual = retrofit.baseUrl().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildWithInterceptor() throws  Exception {
        String expected = "http://testurl.com/";
        Retrofit retrofit = new RetrofitClient().build(expected, HttpLoggingInterceptor.Level.BASIC);
        //TODO come back and continue this
    }
}