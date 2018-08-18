package com.example.administrator.testjson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public class onefragment extends Fragment {
    private Myimagereal myimagereal;
    private String URL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imagefrag, container, false);
        myimagereal = (Myimagereal) view.findViewById(R.id.myphoto);
        myimagereal.setimageurl(URL);
        return view;
    }

    public onefragment(String URL) {
        this.URL = URL;
    }
}
