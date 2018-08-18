package com.example.administrator.testjson;

import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public class geturl {
    public static String url = "https://www.apiopen.top/meituApi?page=1";

    public static class call implements Callable<String> {

        @Override
        public String call() throws Exception {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            String url1 = response.body().string();
            return url1;
        }
    }
}
