package com.frankly.randomdog.randomdogapp;

import android.app.Application;
import android.content.Context;

import com.frankly.randomdog.bitmaputil.BitmapCache;
import com.frankly.randomdog.sharedpreferences.SharedPref;


public class RandomDogApplication extends Application {
    static BitmapCache  cache = null;
    static SharedPref pref = null;
    static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        cache = new BitmapCache(BitmapCache.getCacheSize());
        context = getApplicationContext();
        pref = new SharedPref(context);

    }

    public static BitmapCache getCache() {
        return cache;
    }

    public static SharedPref getPref() {
        return pref;
    }

    public static Context getContext() {
        return context;
    }
}


