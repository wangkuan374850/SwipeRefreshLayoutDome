package com.example.wangkuan.swiperefreshlayoutdome;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout sr;
    private RecyclerView rv;
    private ArrayList<String> ls = new ArrayList<>();

    private MyAdapter m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        sr = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        //设置刷新时动画的颜色，可以设置4个
        sr.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sr.setSize(SwipeRefreshLayout.DEFAULT);//设置大小

      /*  // 这句话是为了，第一次进入页面的时候显示加载进度条
        sr.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));*/


        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sr.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ls.clear();
                        for (int i = 0; i < 20; i++) {
                            ls.add("SwipeRefreshLayout下拉刷新" + i);
                        }
                        m.notifyDataSetChanged();
                        sr.setRefreshing(false);
                    }
                }, 3000);//延迟3秒钟的时间
            }
        });

        rv = (RecyclerView) findViewById(R.id.a1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 50; i++) {
            ls.add("哈哈" + i);
        }
        m = new MyAdapter(ls, MainActivity.this);
        rv.setAdapter(m);
    }
}
