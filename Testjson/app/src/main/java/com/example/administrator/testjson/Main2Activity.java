package com.example.administrator.testjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private String URL;
    private Fragment fragment;
    private Fragment fragment1;
    private int idfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        idfragment = R.id.myfragment1;
        Intent intent = getIntent();
        URL = intent.getStringExtra("URL");
        replacefragment(URL);
    }

    private void replacefragment(String URL) {
        fragment = new onefragment(URL);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(idfragment, fragment);
        transaction.commit();
    }

    private void re1(String URL) {
        fragment1 = new onefragment(URL);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.myfragment2, fragment1);
        transaction.commit();
    }
}