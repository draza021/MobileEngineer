
/**
 * Created by Drago on 6/28/2017.
 */

package com.development.id.ns.mobileengineer;

import android.app.Application;

import com.development.id.ns.mobileengineer.backend.RestApi;
import com.facebook.stetho.Stetho;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MobileEngineer extends Application {
    public static MobileEngineer instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initApplication();
    }

    private void initApplication() {
        setRestApi();
        Picasso picassoInstance = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(RestApi.getOkHttpClient()))
                .indicatorsEnabled(BuildConfig.DEBUG)
                .loggingEnabled(BuildConfig.DEBUG)
                .build();
        Picasso.setSingletonInstance(picassoInstance);
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    private void setRestApi() {
        RestApi.setContext(getCacheDir());
    }
}
