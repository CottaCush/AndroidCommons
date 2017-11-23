package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.HttpResponseUtils;
import com.cottacush.android.libraries.utils.RetrofitClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rasheed on 11/23/17.
 */

public class HttpResponseUtilsInstrumentationTest {

    String exampleErrorResponseString = "{\n" +
            "  \"status\": \"error\",\n" +
            "  \"message\": \"The access token provided has expired\",\n" +
            "  \"code\": \"E0004\"\n" +
            "}";

    HttpResponseUtils responseUtilsWithObjectBody;

    @Before
    public void setUp() throws Exception {

        ResponseBody responseBody =    ResponseBody.create(
                MediaType.parse("application/json"),
                exampleErrorResponseString
        );
        responseUtilsWithObjectBody = new HttpResponseUtils(null, responseBody);


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
