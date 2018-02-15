package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.HttpResponseUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

import static org.junit.Assert.assertEquals;

public class HttpResponseUtilsInstrumentationTest {

    private HttpResponseUtils responseUtilsWithObjectBody;
    private HttpResponseUtils responseUtilsWithIrregularBody;

    @Before
    public void setUp() throws Exception {
        String exampleErrorResponseString = "{\n" +
                "  \"status\": \"error\",\n" +
                "  \"message\": \"The access token provided has expired\",\n" +
                "  \"code\": \"E0004\"\n" +
                "}";
        ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                exampleErrorResponseString
        );
        responseUtilsWithObjectBody = new HttpResponseUtils(null, responseBody);

        String errorDataString = "Example error data";
        ResponseBody irregularResonseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                errorDataString
        );
        String irregularDataString = "{ \"example\": \"irregular json\"}";
        JsonElement successObjectBodyElement = new JsonParser().parse(irregularDataString);
        responseUtilsWithIrregularBody = new HttpResponseUtils(successObjectBodyElement, irregularResonseBody);
    }

    @Test
    public void getErrorMessage() throws Exception {
        String expected = "The access token provided has expired";
        String actual = responseUtilsWithObjectBody.getErrorMessage();
        assertEquals(expected, actual);
        String ERROR_MESSAGE = "An unexpected network error occurred.";
        assertEquals(ERROR_MESSAGE, responseUtilsWithIrregularBody.getErrorMessage());
    }

    @Test
    public void getCode() throws Exception {
        String expected = "E0004";
        String actual = responseUtilsWithObjectBody.getCode();
        assertEquals(expected, actual);
        assertEquals("-1", responseUtilsWithIrregularBody.getCode());
    }
}
