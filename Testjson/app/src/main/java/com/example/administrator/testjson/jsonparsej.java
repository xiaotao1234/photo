package com.example.administrator.testjson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public class jsonparsej {
    public ArrayList<jsonresult> listjson1 = new ArrayList<>();

    public ArrayList<jsonresult> parsejsonwithjsonobject(String jsondata) {
        try {
            JSONObject jsonObject = new JSONObject(jsondata);
            String data1 = jsonObject.getString("data");
            JSONArray jsonArray = new JSONArray(data1);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String createdAt = jsonObject1.getString("createdAt");
                String publishedAt = jsonObject1.getString("publishedAt");
                String type = jsonObject1.getString("type");
                String url = jsonObject1.getString("url");
                jsonresult pr = new jsonresult(createdAt, publishedAt, type, url);
                listjson1.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listjson1;
    }
}
