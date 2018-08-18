package com.jit.lib;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebImage implements SmartImage {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 10000;

    private static WebImageCache webImageCache;

    private String url;
    private int window_width;
    private int window_height;
    private int scale1;
    public WebImage(String url) {
        this.url = url;
    }

    public Bitmap getBitmap(Context context,int w,int h,int scale) {
        //初始化图片缓存类
        window_width = w;
        window_height = h;
        this.scale1 = scale;
        if(webImageCache == null) {
            webImageCache = new WebImageCache(context);
        }

        //读取图片依次顺序，内存->本地->网络
        Bitmap bitmap = null;
        if(url != null) {
        	//内存->本地
            bitmap = webImageCache.get(url);
            if(bitmap == null) {
            	//网络
                bitmap = getBitmapFromUrl(url);
                if(bitmap != null){
                	//bitmap加入缓存
                    webImageCache.put(url, bitmap);
                }
            }
        }
        return bitmap;
    }

    private Bitmap getBitmapFromUrl(String url) {
        Bitmap bitmap = null;

        try {
            Rect rect = new Rect();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(url,options);
            int width = options.outWidth;
            int height = options.outHeight;
            int scale = 1;
            int scalex = width/window_width;
            int scaley = height/window_height;
            if(scalex >= scaley){
                scale = scalex;
            }else {
                scale = scaley;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            scale = scale1;
            options.inSampleSize = scale;
            URLConnection conn = new URL(url).openConnection();
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            bitmap = BitmapFactory.decodeStream((InputStream) conn.getContent(),rect,options);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void removeFromCache(String url) {
        if(webImageCache != null) {
            webImageCache.remove(url);
        }
    }
}

