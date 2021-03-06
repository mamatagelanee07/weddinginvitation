package com.andigeeky.weddinginvitation;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import timber.log.Timber;


public class WeddingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
