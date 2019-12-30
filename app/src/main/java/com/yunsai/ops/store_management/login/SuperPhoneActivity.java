package com.yunsai.ops.store_management.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.yunsai.ops.store_management.R;

import com.yunsai.ops.store_management.adapter.ListAdapter_Phone;
import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.PhoneBase;
import com.yunsai.ops.store_management.base.PhoneCode;

import com.yunsai.ops.store_management.util.UrlUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuperPhoneActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter_Phone listAdapter;
    private PhoneBase mphoneBase;
    private LoginAIP loginAIP;
    Context context;
    String jurisdiction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_phone);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        loginAIP = retrofit.create(LoginAIP.class);
        initNet();
        initView();
    }


    private void initNet() {
        Observable<PhoneCode> phoneBaseObservable = loginAIP.phoneba();
        phoneBaseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneCode>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhoneCode phoneBase) {

                        String s = JSON.toJSONString(phoneBase);
                        mphoneBase = JSON.parseObject(s,PhoneBase.class);
                        Log.i("ONNEXT:", "onNext: "+s);
                        handler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void refresh() {
        finish();
        Intent intent = new Intent(SuperPhoneActivity.this, SuperPhoneActivity.class);
        startActivity(intent);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            listAdapter = new ListAdapter_Phone(SuperPhoneActivity.this,mphoneBase.getRECORDS());
            listView.setAdapter(listAdapter);
        }
    };
    private void initView() {
        listView = findViewById(R.id.phone_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getAdapter().getItem(i);
                String userId = mphoneBase.getRECORDS().get(i).getUserId();
                Log.i("那倒没有::", "onItemClick: "+userId);
                AlertDialog.Builder builder = new AlertDialog.Builder(SuperPhoneActivity.this);
                AlertDialog dialog = builder.setTitle("请选择授权等级")
                        .setMessage("1、一级权限可增删改查\n"+"2、二级权限仅可查看")
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setNeutralButton("一级权限", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                jurisdiction = "1";
                                initData(userId);
                                refresh();
                                Toast.makeText(SuperPhoneActivity.this, "一级权限", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("二级权限", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                jurisdiction ="2";
                                initData(userId);
                                refresh();
                                Toast.makeText(SuperPhoneActivity.this, "二级权限", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
                Button mNegativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                Button mPositiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button mNeutralButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);

                LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) mPositiveButton.getLayoutParams();
                positiveButtonLL.weight = 1;
                mPositiveButton.setLayoutParams(positiveButtonLL);

                LinearLayout.LayoutParams mNegativeButtonLL = (LinearLayout.LayoutParams) mNegativeButton.getLayoutParams();
                mNegativeButtonLL.weight = 1;
                mNegativeButton.setLayoutParams(mNegativeButtonLL);

                LinearLayout.LayoutParams mNeutralButtonLL = (LinearLayout.LayoutParams) mNeutralButton.getLayoutParams();
                mNeutralButtonLL.weight = 1;
                mNeutralButton.setLayoutParams(mNeutralButtonLL);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }
    private void initData(String s) {
        Log.i("获取id", "initData: "+s);
        Observable<BaseCod> baseCodObservable = loginAIP.InterpositionServer(s,jurisdiction);
        baseCodObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCod>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseCod baseCod) {
                        int resCode = Integer.parseInt(baseCod.getResCode());
                        if (resCode==200){
                            Toast.makeText(SuperPhoneActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SuperPhoneActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SuperPhoneActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
