
/**
 * Created by Drago on 6/28/2017.
 */

package com.development.id.ns.mobileengineer.backend;

import android.util.Log;

import com.development.id.ns.mobileengineer.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    private static final String API_BASE_URL = BuildConfig.API_BASE;
    private static final long SIZE_OF_CACHE = 60 * 1024 * 1024; // 60 MB
    private static final long TIMEOUT_SECONDS = 30;

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    static public void setContext(File cacheDir) {
        if (okHttpClient != null)
            return;

        // Create Cache
        Cache cache = null;
        try {
            cache = new Cache(new File(cacheDir, "http"), SIZE_OF_CACHE);
        } catch (Exception e) {
            Log.e(RestApi.class.getSimpleName(), "Could not create Cache!", e);
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        HttpLoggingInterceptor.Level level = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        logging.setLevel(level);

        // Create OkHttpClient V3
        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(logging)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        retrofit = builder
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    static public ApiEndpointInterfaces getRestService() {
        return retrofit.create(ApiEndpointInterfaces.class);
    }

    static public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}
