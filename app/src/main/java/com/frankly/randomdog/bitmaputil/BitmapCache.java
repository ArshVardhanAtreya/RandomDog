package com.frankly.randomdog.bitmaputil;

import android.content.Context;
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

public class BitmapCache extends LruCache<String, Bitmap> {
    public BitmapCache(int maxSize) {
        super(maxSize);
    }

    public static int getCacheSize() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return maxMemory / 8;
    }

    @Override
    protected int sizeOf( String key, Bitmap value ) {
        return value.getByteCount()/1024;
    }

    public Bitmap getBitmap(String key) {
        return this.get(key);
    }

    public void setBitmapOrDownload(final String key, String url, final ImageView iv) {
        if (hasBitmap(key)) {
            iv.setImageBitmap(getBitmap(key));
        } else {
            RequestOptions glideOptions = new RequestOptions();
            glideOptions.placeholder(R.drawable.ic_launcher_background);
            glideOptions.override(200, 200);
            glideOptions.centerCrop();
            BaseTarget bs = new BaseTarget<Bitmap>(){
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    put(key, resource);
                    iv.setImageBitmap(resource);
                }

                @Override
                public void removeCallback(SizeReadyCallback cb) {}

                @Override
                public void getSize(SizeReadyCallback cb) {}
            };

            Glide.with(RandomDogApplication.getContext()).asBitmap()
                    .load(url)
                    .apply(glideOptions)
                    .into(bs);
        }
    }

    public void setBitmap(String key, Bitmap bitmap) {
        if (!hasBitmap(key)) {
            this.put(key, bitmap);
        }
    }

    public boolean hasBitmap(String key) {
        return getBitmap(key) != null;
    }
}
