package com.cottacush.android.libraries;

import com.cottacush.android.libraries.utils.RetrofitClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import static com.cottacush.android.libraries.RetrofitClientInstrumentationTest.CallTestService.TEST_END_POINT;
import static org.junit.Assert.assertEquals;

public class RetrofitClientInstrumentationTest {

    private MockWebServer server;

    private String exampleJsonResponseDataAsObjectString = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"data\": {\n" +
            "    \"access_token\": \"36a378c54edbdf61c4e004fa4c06f841ac6a3b57\",\n" +
            "    \"expires_in\": 21600,\n" +
            "    \"token_type\": \"Bearer\",\n" +
            "    \"scope\": null\n" +
            "  }\n" +
            "}\n";

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer(); // Fake server
        server.enqueue(new MockResponse().setBody(exampleJsonResponseDataAsObjectString));
        //Start the server.
        server.start();
    }

    @Test
    public void testBuildWithOkHttpClient() throws Exception {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //make request to a url pointing to our fake server
        String serverUrl = server.url("").toString();
        Retrofit retrofit = new RetrofitClient(true).build(serverUrl, builder);
        CallTestService callTestService = retrofit.create(CallTestService.class);
        JsonElement jsonElementReturnedFromServer = callTestService.getAccessToken().execute().body();
        // Grab the request recieved
        RecordedRequest request1 = server.takeRequest();
        //Check if our request path is correct by comparing it with the one the server received
        //test Successful means  that our Retrofitclient  class successfully set the base url
        String expected = "/" + TEST_END_POINT;
        assertEquals(expected, request1.getPath());

        //Check if the body returned from the server is actually correct., We can do this by comparing  one of the nodes.
        JsonElement expectedJsonObject = new JsonParser().parse(exampleJsonResponseDataAsObjectString);

        assertEquals(expectedJsonObject.getAsJsonObject().get("status").getAsString()
                , jsonElementReturnedFromServer.getAsJsonObject().get("status").getAsString());
        //If we reach here , the retrofit object returned from RetrofitClient.build(baseUrl ,client) was able to make valid  a request.
    }


    @Test
    public void testBuildWithInterceptor() throws Exception {
        //make request to our fake server
        String serverUrl = server.url("").toString();
        Retrofit retrofit = new RetrofitClient(true).build(serverUrl, HttpLoggingInterceptor.Level.BODY);
        CallTestService callTestService = retrofit.create(CallTestService.class);
        JsonElement jsonElementReturnedFromServer = callTestService.getAccessToken().execute().body();
        RecordedRequest request1 = server.takeRequest();
        //Check if our request path is correct by comparing it with the one the server received
        //Pass would mean our Retrofitclient  class successfully set the base url
        String expected = "/" + TEST_END_POINT;
        assertEquals(expected, request1.getPath());

        //Check if the body returned from the server is actually correct., We can do this by comparing  one of the nodes.
        JsonElement expectedJsonObject = new JsonParser().parse(exampleJsonResponseDataAsObjectString);

        assertEquals(expectedJsonObject.getAsJsonObject().get("status").getAsString()
                , jsonElementReturnedFromServer.getAsJsonObject().get("status").getAsString());
        //If we reach here , the retrofit object returned from RetrofitClient.build(baseUrl , LoggingInterceptor.Level) was able to make valid  a request.
    }


    @Test
    public void testBuildWithParams() throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("key", "value");
        params.put("Key1", "value1");
        //make request to our fake server
        String serverUrl = server.url("").toString();
        Retrofit retrofit = new RetrofitClient(true).build(serverUrl, params);
        CallTestService callTestService = retrofit.create(CallTestService.class);
        JsonElement jsonElementReturnedFromServer = callTestService.getAccessToken().execute().body();
        RecordedRequest request1 = server.takeRequest();

        //Check if our request path is correct by comparing it with the one the server received
        //successful assertio would mean our Retrofitclient  class  successfuly set the query params for the request.
        //Here , append the sent query params to the path

        String expected = "/" + TEST_END_POINT + "?key=value&Key1=value1";
        assertEquals(expected, request1.getPath());


        //Check if the body returned from the server is actually correct., We can do this by comparing  one of the nodes.
        JsonElement expectedJsonObject = new JsonParser().parse(exampleJsonResponseDataAsObjectString);

        assertEquals(expectedJsonObject.getAsJsonObject().get("status").getAsString()
                , jsonElementReturnedFromServer.getAsJsonObject().get("status").getAsString());
        //If we reach here without Exceoption , the retrofit object returned from RetrofitClient.build(baseUrl , params)
        // was able to make valid  a request.
    }

    @Test
    public void testBuildWithUrlOnly() {
        String expected = "http://testurl.com/";
        Retrofit retrofit = new RetrofitClient(true).build(expected);
        String actual = retrofit.baseUrl().toString();
        assertEquals(expected, actual);
    }

    public interface CallTestService {
        String TEST_END_POINT = "data/age_ranges";

        @GET(TEST_END_POINT)
        Call<JsonElement> getAccessToken();
    }

    @Test
    public void testGetBasicRetrofitBuilder() throws Exception {
        assertEquals(true, (new RetrofitClient(true).getBasicRetrofitBuilder() != null));
    }

    @Test
    public void testGetBasicHttpClientBuilder() throws Exception {
        assertEquals(true, (new RetrofitClient(true).getBasicHttpClientBuilder() != null));
    }

}
