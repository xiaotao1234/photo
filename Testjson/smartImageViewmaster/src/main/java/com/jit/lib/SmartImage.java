package com.jit.lib;


import android.content.Context;
import android.graphics.Bitmap;

public interface SmartImage {
    public Bitmap getBitmap(Context context,int w,int h,int scale);
}
