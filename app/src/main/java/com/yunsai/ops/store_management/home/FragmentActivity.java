package com.yunsai.ops.store_management.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.home1.Home1Fragment;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.fragmentActivity, new HomeFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentActivity, new HomeFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
