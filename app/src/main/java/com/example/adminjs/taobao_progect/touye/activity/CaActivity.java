package com.example.adminjs.taobao_progect.touye.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.example.adminjs.taobao_progect.R;
import com.example.adminjs.taobao_progect.fenlei.fragment_fen.Fragment_02;
import com.example.adminjs.taobao_progect.gouwuche.fragment3.Fragment_03;
import com.example.adminjs.taobao_progect.my.Fragmenter4;
import com.example.adminjs.taobao_progect.shouye.fragment_shou.Fragment_01;
import com.example.adminjs.taobao_progect.touye.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by Adminjs on 2017/11/7.
 */
public class CaActivity extends FragmentActivity{

    private RadioGroup group;
    private ArrayList<Fragment> list;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);
        pager = (ViewPager)findViewById(R.id.pager);
        group = (RadioGroup) findViewById(R.id.group);
        list = new ArrayList<>();
        list.add(new Fragment_01());
        list.add(new Fragment_02());
        list.add(new Fragment_03());
        list.add(new Fragmenter4());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(myAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                group.check(group.getChildAt(position).getId());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_01:
                        pager.setCurrentItem(0,false);
                        break;
                    case R.id.radio_02:
                        pager.setCurrentItem(1,false);
                        break;
                    case R.id.radio_03:
                        pager.setCurrentItem(2,false);
                        break;
                    case R.id.radio_04:
                        pager.setCurrentItem(3,false);
                        break;
                }
            }
        });
       pager.setOffscreenPageLimit(4);
    }
}
