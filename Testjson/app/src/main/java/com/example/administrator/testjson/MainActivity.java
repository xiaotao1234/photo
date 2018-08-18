package com.example.administrator.testjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {
    public String forgeturl;
    public jsonparsej jsonparsej1;
    private List<jsonresult> result = new ArrayList<>();
    private List<jsonresult> resultonly = new ArrayList<>();
    private RecyclerView recyclerView;
    private StringBuilder a1 = new StringBuilder();
    private StringBuilder a2 = new StringBuilder();
    private StringBuilder a3 = new StringBuilder();
    private StringBuilder a4 = new StringBuilder();
    public int window_width;
    public int window_height;
    private DrawerLayout drawerLayout;
    private imageadapter imageadapter1;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.item1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPhoto();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.cc);
        }
        recyclerView = (RecyclerView) findViewById(R.id.imageview);
        final Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        window_width = windowManager.getDefaultDisplay().getWidth() / 2;
        window_height = windowManager.getDefaultDisplay().getHeight() / 2;
        ExecutorService executorService = Executors.newCachedThreadPool();
        geturl.call ct = new geturl.call();
        FutureTask<String> f = new FutureTask<String>(ct);
        executorService.submit(f);
        executorService.shutdown();
        try {
            forgeturl = f.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonparsej1 = new jsonparsej();
        result = jsonparsej1.parsejsonwithjsonobject(forgeturl);
        resultonly = result.subList(0,result.size()/2);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);
        imageadapter1 = new imageadapter(resultonly, window_width, window_height, 2);
        recyclerView.setAdapter(imageadapter1);

    }

    public void changeActivity() {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    private void refreshPhoto(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
