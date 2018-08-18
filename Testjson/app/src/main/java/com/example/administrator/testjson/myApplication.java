package com.example.administrator.testjson;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public class myApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
