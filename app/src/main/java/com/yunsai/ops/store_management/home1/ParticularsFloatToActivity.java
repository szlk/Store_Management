package com.yunsai.ops.store_management.home1;


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
import com.yunsai.ops.store_management.home.HttpApi;
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

public class ParticularsFloatToActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwiperfreshlayoutIO;
    private ImageView mShopReturn;
    private TextView mShopName;
    private ImageView mShopSubmit;
    private EditText mFshopName;
    private EditText mFshopNum;
    private EditText mFshopPrice;
    private EditText mFshopMon;
    private EditText mFshopaddtime;
    private EditText mFshopparticulars;

    private Listbean listbean = new Listbean();
    private HttpApiHome1 httpApi;
    private String TAG = "ParticularsFloatActivity";
    private Context context;
    String getinitgetData = UtilData.geOLtinitgetData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particularsfloatto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApiHome1.class);
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
                                    Intent intent = new Intent(ParticularsFloatToActivity.this, FragmenttoActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(ParticularsFloatToActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "onNext: CODE");
                                }else {
                                    Toast.makeText(ParticularsFloatToActivity.this, "提交失败", Toast.LENGTH_SHORT).show();

                                }
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {

                                Toast.makeText(ParticularsFloatToActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();

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
        mSwiperfreshlayoutIO = findViewById(R.id.fswiperfreshlayout_IOt);
        mShopReturn = findViewById(R.id.shop_returnt);
        mShopReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        mShopName = findViewById(R.id.shop_Name);
        mShopSubmit = findViewById(R.id.shop_submitt);

        mFshopName = findViewById(R.id.fshop_Namet);

        mFshopNum = findViewById(R.id.fshopNumt);
//        mShopyu = findViewById(R.id.shopyu);
//        mShopke = findViewById(R.id.shopke);
        mFshopPrice = findViewById(R.id.fshopPricet);
        mFshopMon = findViewById(R.id.fshopMont);
        mFshopaddtime = findViewById(R.id.fshopaddtimet);

        mFshopaddtime.setText(getinitgetData);
        mFshopparticulars = findViewById(R.id.fshopparticularst);
    }
}
