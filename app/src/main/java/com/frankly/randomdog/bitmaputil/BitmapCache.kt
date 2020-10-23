package com.frankly.randomdog.bitmaputil

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.collection.LruCache
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.transition.Transition
import com.frankly.randomdog.R
import com.frankly.randomdog.randomdogapp.RandomDogApplication

/**
 * @author: ArshVardhanAtreya <arshvardhan></arshvardhan>@yahoo.co.in>
 */
open class BitmapCache(maximumSize: Long) : LruCache<String?, Bitmap?>(maximumSize.toInt()) {

    fun getBitmap(key: String?): Bitmap? {
        return key?.let { this.get(it) }
    }

    @SuppressLint("CheckResult")
    fun setBitmapOrDownload(key: String?, imageUrl: String?, imageView: ImageView?) {
        val options = RequestOptions()
        options.placeholder(R.drawable.ic_launcher_background)
        options.override(200, 200)
        options.centerCrop()
        if (hasBitmap(key)) {
            imageView?.let {
                RandomDogApplication.getContext()?.let { it1 ->
                    Glide.with(it1).asBitmap()
                            .load(getBitmap(key))
                            .apply(options)
                            .into(it)
                }
            }
        } else {
            val bs: BaseTarget<*> = object : BaseTarget<Bitmap?>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                    key?.let { put(it, resource) }
                    RandomDogApplication.getContext()?.let {
                        imageView?.let { it1 ->
                            Glide.with(it).asBitmap()
                                    .load(getBitmap(key))
                                    .apply(options)
                                    .into(it1)
                        }
                    }
                }

                override fun removeCallback(cb: SizeReadyCallback) {}
                override fun getSize(cb: SizeReadyCallback) {}
            }
            RandomDogApplication.getContext()?.let {
                Glide.with(it).asBitmap()
                        .load(imageUrl)
                        .apply(options)
            }!!
        }
    }

    fun hasBitmap(key: String?): Boolean {
        return getBitmap(key) != null
    }

    companion object {
        fun getCacheSize(): Long {
            val maximumMemory = (Runtime.getRuntime().maxMemory() / 1024)
            return maximumMemory / 8
        }
    }
}