package org.lxl.work.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.lxl.work.Activity_BookSave;
import org.lxl.work.Activity_Login;
import org.lxl.work.MyFragment.MyFragmentPagerAdapter;
import org.lxl.work.R;
import org.lxl.work.entity.Book;

import java.util.ArrayList;


/**
 * Created by Coder-pig on 2015/8/28 0028.
 */
public class BNB extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    //UI Objects
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;


    public void submit(View view) {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
    }


    public void sc(View view) {
        Intent intent = new Intent(this, BNB.class);
        startActivity(intent);
    }
    public void back(View view){
        SharedPreferences pre = getSharedPreferences("myDemo", MODE_PRIVATE);
     /*   TextView t = findViewById(R.id.t1);
        t.setText("" + pre.getBoolean("autoLogin", true));*/
        SharedPreferences.Editor editor = pre.edit();
        editor.putBoolean("checkboxButton", false);
        editor.commit();
        Intent intent = new Intent(this, Activity_Login.class);
        startActivity(intent);
        finish();
    }

    public void add(View view){
        //跳转到主页面
        Intent intent = new Intent(this, Activity_BookSave.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_bar);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setChecked(true);

    }

    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_channel:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_message:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_better:
                vpager.setCurrentItem(PAGE_THREE);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_channel.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_message.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_better.setChecked(true);
                    break;
            }
        }
    }
}