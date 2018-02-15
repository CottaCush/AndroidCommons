package com.cottacush.android.libraries.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Adegoke Obasa <goke@cottacush.com>
 */
public class RetrofitClient {

    private Retrofit.Builder builder;
    private boolean isDebug;

    public RetrofitClient(boolean isDebug) {
        this.isDebug = isDebug;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Retrofit build(String baseUrl) {
        return builder
                .baseUrl(baseUrl)
                .client(getHttpClient().build())
                .build();
    }

    public Retrofit build(String baseUrl, HttpLoggingInterceptor.Level level) {
        return builder
                .baseUrl(baseUrl)
                .client(getHttpClient(level).build())
                .build();
    }

    public Retrofit build(String baseUrl, HashMap<String, String> queryParams) {
        return builder
                .baseUrl(baseUrl)
                .client(getHttpClient(queryParams).build())
                .build();
    }

    public Retrofit build(String baseUrl, OkHttpClient.Builder clientBuilder) {
        return builder
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .build();
    }

    private OkHttpClient.Builder getHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES);
    }

    private OkHttpClient.Builder getHttpClient(HttpLoggingInterceptor.Level level) {
        OkHttpClient.Builder httpClient = getHttpClient();
        if (isDebug) {
            httpClient.addInterceptor(getLoggingInterceptor(level));
        }
        return httpClient;
    }

    private HttpLoggingInterceptor getLoggingInterceptor(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level);
        return interceptor;
    }

    public Retrofit.Builder getBasicRetrofitBuilder() {
        return builder;
    }

    public OkHttpClient.Builder getBasicHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }

    private OkHttpClient.Builder getHttpClient(final HashMap<String, String> params) {
        return getHttpClient()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();
                        HttpUrl.Builder urlBuilder = originalHttpUrl.newBuilder();
                        for (String key : params.keySet()) {
                            urlBuilder.addQueryParameter(key, params.get(key));
                        }
                        HttpUrl url = urlBuilder.build();
                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
    }
}
