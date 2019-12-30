package com.yunsai.ops.store_management.home;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import com.yunsai.ops.store_management.home1.FragmenttoActivity;
import com.yunsai.ops.store_management.util.ShareUtils;
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

public class ParticularsActivity extends AppCompatActivity {


    private EditText mShopId;
    private TextView mShopName;
    private EditText mShopPrice;
    private EditText mShopMon;

    private EditText mShopNum;
    private ImageView mShopReturn;
    private ImageView mShopSubmit;
    private SwipeRefreshLayout mSwiperfreshlayoutIO;
    private HttpApi httpApi;
    Listbean listbean = new Listbean();
    String s = null;
    String s1 = null;
    String s2 = null;
    String s3 = null;
    String s4 = null;
    String s5 = null;
    String s6 = null;
    String s7 = null;
    String s8 = null;
    String s9 = null;
    String shopParticulars = null;
    private EditText mShopYul;
    private TextView mShopTime;
    private TextView mShopAddTime;
    private EditText mShopParticulars;
    String shopId=null;
    String shopName=null;
    String shopNum=null;
    String shopPrice=null;
    String shopMon=null;
    String shopTime=null;
    String shopYu=null;
    String shopAddTime=null;

    String key = null;
    String Keyss = "0";
    String keys = "1";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particulars);
        key=ShareUtils.get(this,"key");
        initView();
//        initDate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }


    private void initPost() {
        s = mShopId.getText().toString();
        s1 = mShopName.getText().toString();
        s2 = mShopPrice.getText().toString();
        s3 = mShopMon.getText().toString();
        s4 = mShopTime.getText().toString();
        s9 = mShopNum.getText().toString();

        Log.i("PST:::", "initPost: " + s);
        listbean.setShopId(s);
        listbean.setShopName(s1);
        listbean.setShopPrice(s2);
        listbean.setShopMon(s3);

        listbean.setShopNum(s9);
    }

    private void initDate() {
        //刷新控件
        mSwiperfreshlayoutIO.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
            }
        });
    }

    private void initView() {

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        shopId = bundle.getString("shopId");
        shopName = bundle.getString("shopName");
        shopNum = bundle.getString("shopNum");
        shopPrice = bundle.getString("shopPrice");
        shopMon = bundle.getString("shopMon");
        shopTime = bundle.getString("shopTime");
        shopYu = bundle.getString("shopYu");
        shopAddTime = bundle.getString("shopAddTime");
        shopParticulars = bundle.getString("shopParticulars");

        mShopId = findViewById(R.id.shop_id);
        mShopId.setText(shopId);
        mShopName = findViewById(R.id.shop_Name);
        mShopName.setText(shopName);
        mShopPrice = findViewById(R.id.shopPrice);
        mShopPrice.setText(shopPrice);
        mShopMon = findViewById(R.id.shopMon);
        mShopMon.setText(shopMon);
        mShopNum = findViewById(R.id.shopNum);
        mShopNum.setText(shopNum);
        mShopYul = findViewById(R.id.shop_yul);
        mShopYul.setText(shopYu);
        mShopTime = findViewById(R.id.shop_time);
        mShopTime.setText(shopTime);
        mShopAddTime = findViewById(R.id.shop_add_time);
        mShopAddTime.setText(shopAddTime);
        mShopParticulars = findViewById(R.id.shop_particulars);
        mShopParticulars.setText(shopParticulars);
//        String s = shopNum.toString();
//        int i = Integer.parseInt(s);
//        int D = 1;
//        int A = i - D;

//        Log.i("DDDDD;;;;", "initView: " + A);
        Log.i("BUNDLE:", "initView: " + shopPrice);

        mShopReturn = findViewById(R.id.shop_return);
        mShopReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParticularsActivity.this, FragmentActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
                finish();
            }
        });
        mShopSubmit = findViewById(R.id.shop_submit);
        mShopSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.equals(keys)||key.equals(Keyss)){
                initPost();
                String getinitgetData = UtilData.getinitgetData();
                listbean.setShopId(ParticularsActivity.this.s);
                listbean.setShopName(s1);
                listbean.setShopPrice(s2);
                listbean.setShopMon(s3);
                listbean.setShopNum(s9);
                listbean.setShopYu(mShopYul.getText().toString());
                listbean.setShopParticulars(mShopParticulars.getText().toString());
                listbean.setShopTime(getinitgetData);

                String SSS = JSON.toJSONString(listbean);
                Log.i("SSS:::", "onClick:::: " + SSS);

                Observable<BaseCod> observable = httpApi.post(SSS);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseCod>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseCod baseCod) {
                                Log.i("onNEXT", "onNext: " + baseCod.getResCode().toString());
                                int anInt = Integer.parseInt(baseCod.getResCode());
                                if (anInt==200){
                                    Intent intent = new Intent(ParticularsActivity.this, FragmentActivity.class);
                                    intent.putExtra("id",1);
                                    startActivity(intent);
                                    Toast.makeText(ParticularsActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(ParticularsActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("onError", "onError: ", e);
                                Toast.makeText(ParticularsActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onComplete() {

                            }

                        });
                }else {
                    Toast.makeText(ParticularsActivity.this, "暂无权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSwiperfreshlayoutIO = findViewById(R.id.swiperfreshlayout_IO);
//        shopYul.setText(""+D);
//        shopKe.setText(""+A);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(ParticularsActivity.this, FragmentActivity.class);
            intent.putExtra("id",1);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
