package com.yunsai.ops.store_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yunsai.ops.store_management.adapter.ViewpagerAdapter;
import com.yunsai.ops.store_management.home.HomeFragment;
import com.yunsai.ops.store_management.home1.Home1Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPage;
    private BottomNavigationView bottomnavi;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int id = getIntent().getIntExtra("id", 0);
        switch (id){
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,new HomeFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.f1fragment_container,new Home1Fragment())
                    .addToBackStack(null)
                    .commit();
                break;
        }
    }

    private void initView() {
        viewPage = findViewById(R.id.view_page);
        bottomnavi = findViewById(R.id.bottomnavi);
        viewPage.setAdapter(new ViewpagerAdapter(getSupportFragmentManager()));

        bottomnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.tab_one:
                        viewPage.setCurrentItem(0);
                        return true;
                    case R.id.tab_two:
                        viewPage.setCurrentItem(1);
                        return true;
                    case R.id.tab_three:
                        viewPage.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem!=null){
                    menuItem.setChecked(false);
                }
                menuItem = bottomnavi.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
