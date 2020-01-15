package com.frankly.randomdog.bitmaputil;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.collection.LruCache;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.frankly.randomdog.R;
import com.frankly.randomdog.randomdogapp.RandomDogApplication;

/**
 * @author: ArshVardhanAtreya <arshvardhan@yahoo.co.in>
 */


public class BitmapCache extends LruCache<String, Bitmap> {
    public BitmapCache(int maximumSize) {
        super(maximumSize);
    }

    public static int getCacheSize() {
        int maximumMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return maximumMemory / 8;
    }

    @Override
    protected int sizeOf(String key, Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }

    public Bitmap getBitmap(String key) {
        return this.get(key);
    }

    public void setBitmapOrDownload(final String key, String imageUrl, final ImageView imageView) {

        final RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_launcher_background);
        options.override(200, 200);
        options.centerCrop();

        if (hasBitmap(key)) {
            Glide.with(RandomDogApplication.getContext()).asBitmap()
                    .load(getBitmap(key))
                    .apply(options)
                    .into(imageView);
        } else {

            BaseTarget bs = new BaseTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    put(key, resource);
                    Glide.with(RandomDogApplication.getContext()).asBitmap()
                            .load(getBitmap(key))
                            .apply(options)
                            .into(imageView);
                }

                @Override
                public void removeCallback(SizeReadyCallback cb) {
                }

                @Override
                public void getSize(SizeReadyCallback cb) {
                }
            };

            Glide.with(RandomDogApplication.getContext()).asBitmap()
                    .load(imageUrl)
                    .apply(options)
                    .into(bs);
        }
    }

    public boolean hasBitmap(String key) {
        return getBitmap(key) != null;
    }
}
