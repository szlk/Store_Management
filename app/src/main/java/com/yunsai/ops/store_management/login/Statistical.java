package com.yunsai.ops.store_management.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.base.Cartogram;
import com.yunsai.ops.store_management.util.Division;
import com.yunsai.ops.store_management.util.MyPieChart;
import com.yunsai.ops.store_management.util.UrlUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Statistical extends AppCompatActivity {

    private MyPieChart Mypiechart;
    private LoginAIP loginAIP;
    private Cartogram cartograms;
    Cartogram.RECORDSBean recordsBean = new Cartogram.RECORDSBean();
    List<Cartogram.RECORDSBean> recordsBeanList = new ArrayList<>();
    float a = 0;
    float parseInt=0;
    float parseInt1=0;
    float parseInt2=0;
    float parseInt3=0;
    float parseInt4=0;

    float getParseInt = 0;
    float getParseInt1 = 0;
    float getParseInt2 = 0;
    float getParseInt3 = 0;
    float getParseInt4 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);
        Mypiechart=findViewById(R.id.mypiechart);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        loginAIP = retrofit.create(LoginAIP.class);

        initData();

        initView();


    }



    private void initData() {

        Observable<Cartogram> observable = loginAIP.CARTOGRAM_OBSERVABLE();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cartogram>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Cartogram cartogram) {

                        String toJSON = JSON.toJSONString(cartogram);
                        Log.e("返回值", "onNext: " + toJSON);

                        for (int i = 0; i < cartogram.getRECORDS().size(); i++) {

//                            String shopCup = cartogram.getRECORDS().get(i).getShopCup();
//                            String shopPhone = cartogram.getRECORDS().get(i).getShopPhone();
//                            String shopComputer = cartogram.getRECORDS().get(i).getShopComputer();
//                            String shopHarddisk = cartogram.getRECORDS().get(i).getShopHarddisk();
//                            String shopMemory = cartogram.getRECORDS().get(i).getShopMemory();

                            parseInt = Integer.parseInt(cartogram.getRECORDS().get(i).getShopCup());
                            parseInt1 = Integer.parseInt(cartogram.getRECORDS().get(i).getShopPhone());
                            parseInt2 = Integer.parseInt(cartogram.getRECORDS().get(i).getShopComputer());
                            parseInt3 = Integer.parseInt(cartogram.getRECORDS().get(i).getShopHarddisk());
                            parseInt4 = Integer.parseInt(cartogram.getRECORDS().get(i).getShopMemory());
                            a = parseInt+parseInt1+parseInt2+parseInt3+parseInt4;
                            Log.e("返回值", "onNext: " + a);
                        }

                        getParseInt = parseInt/a;
                        getParseInt1 = parseInt1/a;
                        getParseInt2 = parseInt2/a;
                        getParseInt3 = parseInt3/a;
                        getParseInt4 = parseInt4/a;

                        Log.e("返回值", "SSS: " +  getParseInt);
//                        cartograms = JSON.parseObject(toJSON, Cartogram.class);
//                        String s = JSON.toJSONString(recordsBean);
//                        Log.i("ssssss", "initView: "+s);
//                        double s = parseInt/a;
//                        double s1 = parseInt1/a;
//                        double s2 = parseInt2/a;
//                        double s3 = parseInt3/a;
//                        double s4 = parseInt4/a;
                        initETC();
//                        Log.e("返回值", "onNext: " + s+","+s1+","+s2+","+s3+","+s4+parseInt+a);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        Mypiechart = findViewById(R.id.mypiechart);
        Mypiechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        pieEntries.add(new MyPieChart.PieEntry(5, R.color.chart_turquoise, false));


    }

    private void initETC() {
        Log.e("返回值", "onNextssss: " +  getParseInt);
        List<MyPieChart.PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new MyPieChart.PieEntry(getParseInt, R.color.chart_orange, true));
        pieEntries.add(new MyPieChart.PieEntry(getParseInt1, R.color.chart_green, false));
        pieEntries.add(new MyPieChart.PieEntry(getParseInt2, R.color.chart_blue, false));
        pieEntries.add(new MyPieChart.PieEntry(getParseInt3, R.color.chart_purple, false));
        pieEntries.add(new MyPieChart.PieEntry(getParseInt4, R.color.chart_mblue, false));
        Mypiechart.setPieEntries(pieEntries);
    }
}
