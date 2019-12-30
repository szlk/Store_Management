package com.yunsai.ops.store_management.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.yunsai.ops.store_management.MainActivity;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.Listbean;
import com.yunsai.ops.store_management.base.UtilData;
import com.yunsai.ops.store_management.util.ToastUtil;
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

public class ParticularsFloatActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwiperfreshlayoutIO;
    private ImageView mShopReturn;
    private TextView mShopName;
    private ImageView mShopSubmit;
    private EditText mFshopName;
    private EditText mFshopNum;

    private EditText mFshopPrice;
    private EditText mFshopMon;


    private Listbean listbean = new Listbean();
    private HttpApi httpApi;
    private String TAG = "ParticularsFloatActivity";
    private Context context;
    private EditText mFshopaddtime;
    private EditText mFshopparticulars;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particularsfloat);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApi.class);
        initView();
        initDate();
    }

    private void initDate() {
        mShopSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listbean.setShopName(mFshopName.getText().toString());
                listbean.setShopNum(mFshopNum.getText().toString());
                listbean.setShopPrice(mFshopPrice.getText().toString());
                listbean.setShopMon(mFshopMon.getText().toString());
                listbean.setShopAddTime(mFshopaddtime.getText().toString());
                listbean.setShopParticulars(mFshopparticulars.getText().toString());
                Object o = JSON.toJSON(listbean);
                String s = JSON.toJSONString(o);
                Log.i(TAG, "onClick: " + s);

                Observable<BaseCod> baseCodObservable = httpApi.postData(s);
                baseCodObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseCod>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseCod baseCod) {
                                Log.i(TAG, "onNext:::: " + baseCod.getResCode());
                                String resCode = baseCod.getResCode();

                                int i = Integer.parseInt(resCode);
                                if (i == 200) {
                                    Intent intent = new Intent(ParticularsFloatActivity.this, MainActivity.class);
                                    intent.putExtra("id",1);
                                    startActivity(intent);
                                    Toast.makeText(ParticularsFloatActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "onNext: CODE");
                                    finish();
                                }else {
                                    Toast.makeText(ParticularsFloatActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(ParticularsFloatActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onError: " + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                Log.i("JSON:::", "initView: " + o);
            }
        });
    }


    private void initView() {
        mSwiperfreshlayoutIO = findViewById(R.id.fswiperfreshlayout_IO);
        mShopReturn = findViewById(R.id.shop_return);
        mShopReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        mShopName = findViewById(R.id.shop_Name);
        mShopSubmit = findViewById(R.id.shop_submit);

        mFshopName = findViewById(R.id.fshop_Name);

        mFshopNum = findViewById(R.id.fshopNum);
//        mShopyu = findViewById(R.id.shopyu);
//        mShopke = findViewById(R.id.shopke);
        mFshopPrice = findViewById(R.id.fshopPrice);
        mFshopMon = findViewById(R.id.fshopMon);
        mFshopaddtime = findViewById(R.id.fshopaddtime);
        String getinitgetData = UtilData.getinitgetData();
        mFshopaddtime.setText(getinitgetData);
        mFshopparticulars = findViewById(R.id.fshopparticulars);
    }
}
