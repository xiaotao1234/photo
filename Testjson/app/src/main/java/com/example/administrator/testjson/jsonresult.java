package com.example.administrator.testjson;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public class jsonresult {
    public String createdAt;
    public String publishedAt;
    public String type;
    public String url;

    public jsonresult(String a, String b, String c, String d) {
        createdAt = a;
        publishedAt = b;
        type = c;
        url = d;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
