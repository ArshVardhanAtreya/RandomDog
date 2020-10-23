package com.randomdog.randomdogapp

import android.app.Application
import android.content.Context
import com.randomdog.bitmaputil.BitmapCache
import com.randomdog.sharedpreferences.SharedPref

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
class RandomDogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        lruCache = BitmapCache(BitmapCache.getCacheSize())
        appContext = applicationContext
        sharedPref = SharedPref(appContext)
    }

    companion object {
        var lruCache: BitmapCache? = getCache()
        var sharedPref: SharedPref? = getPref()
        var appContext: Context? = getContext()

        fun getCache(): BitmapCache? {
            return lruCache
        }

        fun getPref(): SharedPref? {
            return sharedPref
        }

        fun getContext(): Context? {
            return appContext
        }
    }
}