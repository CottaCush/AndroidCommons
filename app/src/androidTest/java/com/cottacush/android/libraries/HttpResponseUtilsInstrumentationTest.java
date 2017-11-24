package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.HttpResponseUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rasheed on 11/23/17.
 *
 */

public class HttpResponseUtilsInstrumentationTest {

    public static final String ERROR_MESSAGE = "An unexpected network error occurred.";
    String exampleErrorResponseString = "{\n" +
            "  \"status\": \"error\",\n" +
            "  \"message\": \"The access token provided has expired\",\n" +
            "  \"code\": \"E0004\"\n" +
            "}";
    String irregularDataString = "{ \"example\": \"irregular json\"}";
    String errorDataString = "Example error data";
    HttpResponseUtils responseUtilsWithObjectBody;
    HttpResponseUtils responseUtilsWithArrayBody;
    HttpResponseUtils responseUtilsWithIrregularBody;

    @Before
    public void setUp() throws Exception {
        ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                exampleErrorResponseString
        );
        responseUtilsWithObjectBody = new HttpResponseUtils(null, responseBody);

        ResponseBody irregularResonseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                errorDataString
        );
        JsonElement successObjectBodyElement = new JsonParser().parse(irregularDataString);
        responseUtilsWithIrregularBody = new HttpResponseUtils(successObjectBodyElement , irregularResonseBody);
    }


    @Test
    public void getErrorMessage() throws Exception {
        String expected = "The access token provided has expired";
        String actual = responseUtilsWithObjectBody.getErrorMessage();
        assertEquals(expected, actual);
        assertEquals(ERROR_MESSAGE , responseUtilsWithIrregularBody.getErrorMessage());
    }

    @Test
    public void getCode() throws Exception {
        String expected = "E0004";
        String actual = responseUtilsWithObjectBody.getCode();
        assertEquals(expected , actual);
        assertEquals("-1" , responseUtilsWithIrregularBody.getCode());
    }
}
