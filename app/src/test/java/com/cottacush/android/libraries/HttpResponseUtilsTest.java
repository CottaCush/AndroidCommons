package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.HttpResponseUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpResponseUtilsTest {
    private static final String ERROR_MESSAGE = "An unexpected network error occurred.";

    String irregularData = "example irregular json";
    String errorData = "Example error data";

    private HttpResponseUtils responseUtilsWithObjectBody;
    private HttpResponseUtils responseUtilsWithArrayBody;
    private HttpResponseUtils responseUtilsWithErrorObjectBody;

    @Before
    public void setUp() throws Exception {
        String exampleJsonResponseDataAsObjectString = "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"data\": {\n" +
                "    \"access_token\": \"36a378c54edbdf61c4e004fa4c06f841ac6a3b57\",\n" +
                "    \"expires_in\": 21600,\n" +
                "    \"token_type\": \"Bearer\",\n" +
                "    \"scope\": null\n" +
                "  }\n" +
                "}\n";
        JsonElement successObjectBodyElement = new JsonParser().parse(exampleJsonResponseDataAsObjectString);
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
        JsonElement successArrayBodyElement = new JsonParser().parse(exampleResponseDataAsArrayString);
        String exampleErrorResponseString = "{\n" +
                "  \"status\": \"error\",\n" +
                "  \"message\": \"The access token provided has expired\",\n" +
                "  \"code\": \"E0004\"\n" +
                "}";
        JsonElement errorObjectBodyElement = new JsonParser().parse(exampleErrorResponseString);
        responseUtilsWithArrayBody = new HttpResponseUtils(successArrayBodyElement, null);
        responseUtilsWithObjectBody = new HttpResponseUtils(successObjectBodyElement, null);
        responseUtilsWithErrorObjectBody = new HttpResponseUtils(errorObjectBodyElement, null);
    }

    @Test
    public void isSuccess() throws Exception {
        assertEquals(true, responseUtilsWithObjectBody.isSuccess());
        assertEquals(false, responseUtilsWithErrorObjectBody.isSuccess());
    }

    @Test
    public void getData() throws Exception {
        String expected = "Bearer";
        String actual = responseUtilsWithObjectBody.getData().getAsJsonObject().get("token_type").getAsString();
        assertEquals(expected, actual);
    }

    @Test
    public void getDataAsObject() throws Exception {
        String expected = "Bearer";
        String actual = responseUtilsWithObjectBody.getDataAsObject().getAsJsonObject()
                .get("token_type").getAsString(); //if there is no exception here, test passed
        assertEquals(expected, actual);
    }

    @Test
    public void getDataAsArray() throws Exception {
        String expected = "12-17";
        String actual = responseUtilsWithArrayBody.getDataAsArray().getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("name").getAsString();
        assertEquals(expected, actual);
    }

    @Test
    public void getErrorMessage() throws Exception {
        String actual = responseUtilsWithObjectBody.getErrorMessage();
        assertEquals(ERROR_MESSAGE, actual);
    }

    @Test
    public void getCode() throws Exception {
        String expected = "-1";
        String actual = responseUtilsWithObjectBody.getCode();
        assertEquals(expected, actual);
    }
}