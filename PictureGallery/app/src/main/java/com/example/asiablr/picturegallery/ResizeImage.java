package com.example.asiablr.picturegallery;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ResizeImage {

    private Integer newWidth, newHeight;
    private Integer orgWidth, orgHeight;
    private WeakReference<ImageView> imageReference;
    private WeakReference<Resources> imageResource;
    private static LruCache<String, BitmapDrawable> memCache;

    public ResizeImage() {
        this.newWidth = 0;
        this.newHeight = 0;
        this.orgWidth = 0;
        this.orgHeight = 0;

        final int maxMem = (int) (Runtime.getRuntime().maxMemory() / 1024);
        memCache = new LruCache<String, BitmapDrawable>(maxMem/8) {
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                // cache size is tracked in Kb
                return value.getBitmap().getByteCount()/1024;
            }
        };

    }

    private class AsyncResize extends AsyncTask<Integer, Void, BitmapDrawable> {
        int resId;

        @Override
        protected BitmapDrawable doInBackground(Integer... params) {
            resId = params[0];
            return resizeImage(imageResource.get(), resId, newWidth, newHeight);
        }

        @Override
        protected void onPostExecute(BitmapDrawable bitmap) {
            if ((imageReference != null) && (bitmap != null)) {
                final ImageView imageView = imageReference.get();
                if (imageView != null) {
                    imageView.setImageDrawable(bitmap);
                }
                //put this image in cache for future use
                memCache.put(String.valueOf(resId), bitmap);
            }
        }
    }

    private int getInSampleCount(int nWidth, int nHeight) {
        int p = 1;

        if ((orgWidth != 0) && (orgHeight != 0)) {
            int width = orgWidth;
            int height = orgHeight;

            // identify optimal power of 2 division factor
            while ((width > nWidth) || (height > nHeight)) {
                width = width / 2;
                height = height / 2;
                p++;
            }
        }

        return p;
    }

    // resize the given image to given width and height, this is synchronous activity
    public BitmapDrawable resizeImage(Resources res, int resId, int width, int height) {
        final BitmapFactory.Options opt = new BitmapFactory.Options();

        // identify the source image widht and height
        opt.inJustDecodeBounds = true; // will return null for Bitmap, but image properties will be filled
        BitmapFactory.decodeResource(res, resId, opt);

        orgHeight = opt.outHeight;
        orgWidth = opt.outHeight;
        opt.inSampleSize = 1; //getInSampleCount(width, height);

        opt.inJustDecodeBounds = false; // need Bitmap ouput
        return new BitmapDrawable(res, BitmapFactory.decodeResource(res, resId, opt));
    }

    // asynchronously resize and display the image
    public void resizeAndDisplayAsync(ImageView imageView, Resources res, int resId, int width, int height) {
        BitmapDrawable bitmapDrawable;

        // check if image is available in cache
        bitmapDrawable = memCache.get(String.valueOf(resId));
        if (bitmapDrawable != null) {
            imageView.setImageDrawable(bitmapDrawable);
        }
        else {
            AsyncResize resizer = new AsyncResize();
            imageReference = new WeakReference<ImageView>(imageView);
            imageResource = new WeakReference<Resources>(res);
            newHeight = height;
            newHeight = width;
            resizer.execute(resId);
        }
    }

}

