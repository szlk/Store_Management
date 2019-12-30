package com.yunsai.ops.store_management.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.home.FragmentActivity;
import com.yunsai.ops.store_management.home1.FragmenttoActivity;
import com.yunsai.ops.store_management.util.ShareUtils;


public class PHoneOnActivity extends AppCompatActivity {

    private TextView mTextviewphone;
    private TextView mTextviewphoneto;
    String Keyss = "0";
    String keys = "1";
    String keyst="2";
    String key = null;
    private TextView mSuperPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_on);
        key = ShareUtils.get(this, "key");
        initView();
    }

    private void initView() {
        mTextviewphone = findViewById(R.id.textviewphone);
        mTextviewphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.equals(keys)||key.equals(keyst)||key.equals(Keyss)) {
                    Intent intent = new Intent(PHoneOnActivity.this, FragmenttoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PHoneOnActivity.this, "暂无权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mTextviewphoneto = findViewById(R.id.textviewphoneto);
        mTextviewphoneto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (key.equals(keys)||key.equals(keyst)||key.equals(Keyss)) {
                    Intent intent = new Intent(PHoneOnActivity.this, FragmentActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PHoneOnActivity.this, "暂无权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSuperPhone = findViewById(R.id.super_phone);
        if (key.equals(Keyss)){
            mSuperPhone.setVisibility(View.VISIBLE);
        }
        mSuperPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(PHoneOnActivity.this, SuperPhoneActivity.class);
                    startActivity(intent);
                }
        });
    }
    private long time;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);

    }

}
