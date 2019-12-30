package com.yunsai.ops.store_management.home;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.adapter.ListAdapter;
import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.CodBean;
import com.yunsai.ops.store_management.base.Listbean;
import com.yunsai.ops.store_management.base.Listbeanss;
import com.yunsai.ops.store_management.base.UtilData;
import com.yunsai.ops.store_management.mvp.MVPBaseFragment;
import com.yunsai.ops.store_management.util.ShareUtils;
import com.yunsai.ops.store_management.util.UrlUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View{
    Context context;
    private ListView listview;
    private ListAdapter listAdapter;
    private Listbeanss listbeanss;

    private List<Listbeanss> listbeansses = new ArrayList<>();
    private String shopId;

    private SwipeRefreshLayout mSwiperfreshlayout;
    private FloatingActionButton mFloating;
    private View view;
    private HttpApi httpApi;
    private Listbean listbean = new Listbean();
    private EditText mShopSearch;
    private ImageView mShopimgviewbuttom;
    String key = null;
    String Keyss = "0";
    String keys = "1";
    String keyst="2";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        key=ShareUtils.get(getContext(),"key");
        view = inflater.inflate(R.layout.fragment, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApi.class);
        initView(view);
        initNet();
        initReshi();
        initDate();
        Date netTime = UtilData.getNetTime();
        long time1 = netTime.getTime();
        Log.i("DATAUTIL:", "onCreateView: " + time1);
        String time = UtilData.getinitgetData();
        Log.i("时间戳：：", "onCreateView: " + time);
        Log.i("时间戳：：", "onCreateView: " + netTime);

        String geOLtinitgetData = UtilData.geOLtinitgetData();
        Log.i("时间戳：：", "geOLtinitgetData: " + geOLtinitgetData);

        initlong();
        return view;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            listAdapter = new ListAdapter(getContext(), listbeanss.getRECORDS());
            listview.setAdapter(listAdapter);
            listbeansses.add(listbeanss);
            mSwiperfreshlayout.setRefreshing(false);
        }
    };

    private void initView(View view) {

        listview = view.findViewById(R.id.listview);
        mSwiperfreshlayout = view.findViewById(R.id.swiperfreshlayout);
        mFloating = view.findViewById(R.id.floating);
        mFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.equals(Keyss)||key.equals(keys)){
                    Intent intent = new Intent(getContext(), ParticularsFloatActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "权限不足", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mShopSearch = view.findViewById(R.id.shop_search);

        listview.setTextFilterEnabled(true);

        mShopSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                listAdapter.getFilter().filter(s);
                if (mShopSearch.getText()==null) {
                    listview.clearTextFilter();//搜索文本为空时，清除ListView的过滤
                }
                else {
//                    listview.setFilterText(s.toString().trim());//设置过滤关键字
                    listAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mShopimgviewbuttom = view.findViewById(R.id.shopimgviewbuttom);
        mShopimgviewbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = mShopSearch.getText().toString().trim();
                if (TextUtils.isEmpty(search)) {
                    listview.clearTextFilter();//搜索文本为空时，过滤设置
                } else
//                    search_list.clearTextFilter();
                    listview.setFilterText(search);//设置过滤关键字
            }
        });
    }

    private void initDate() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                listbeanss = listbeansses.get(i);
                adapterView.getAdapter().getItem(i);
                Bundle bundle = new Bundle();
                bundle.putString("shopId", listbeanss.getRECORDS().get(i).getShopId());
                bundle.putString("shopName", listbeanss.getRECORDS().get(i).getShopName());
                bundle.putString("shopNum", listbeanss.getRECORDS().get(i).getShopNum());
                bundle.putString("shopPrice", listbeanss.getRECORDS().get(i).getShopPrice());
                bundle.putString("shopMon", listbeanss.getRECORDS().get(i).getShopMon());
                bundle.putString("shopTime", listbeanss.getRECORDS().get(i).getShopTime());
                bundle.putString("shopYu", listbeanss.getRECORDS().get(i).getShopYu());
                bundle.putString("shopAddTime", listbeanss.getRECORDS().get(i).getShopAddTime());
                bundle.putString("shopParticulars",listbeanss.getRECORDS().get(i).getShopParticulars());
                Log.i("BUNDLE:", "onItemClick123: " + listbeanss.getRECORDS().get(i).getShopId());
                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });

    }

    private void initReshi() {
        mSwiperfreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initNet();
                initDate();
                initView(view);
            }
        });
    }

    public void initNet() {

        Observable<CodBean> observable = httpApi.getId();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CodBean recordsBean) {
                        String toJSON = JSON.toJSONString(recordsBean);
                        Log.e("返回值", "onNext: " + toJSON);
                        listbeanss = JSON.parseObject(toJSON, Listbeanss.class);
                        listbeansses.add(listbeanss);
                        handler.sendEmptyMessage(1);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getIdOnError:::", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initlong() {

            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                    if (key.equals(keys)||key.equals(Keyss)) {
                    shopId = listbeanss.getRECORDS().get(position).getShopId();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("确定删除?");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapterView.getItemAtPosition(i);
                            Log.i("ShopId：", "onClick: " + shopId);

                            listAdapter.notifyDataSetChanged();
                            Observable<BaseCod> observable = httpApi.getData(shopId);
                            observable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<BaseCod>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(BaseCod baseCod) {
                                            Log.i("onNEXT", "onNext: " + baseCod.getResCode().toString());
                                            mSwiperfreshlayout.setRefreshing(false);
                                            int parseInt = Integer.parseInt(baseCod.getResCode());
                                            if (parseInt == 200) {
                                                listAdapter.UpView(position,view);
                                                Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            mSwiperfreshlayout.setRefreshing(false);
                                            Log.e("onError", "onError: ", e);
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                            Log.i("SHOPID::", "onClick: " + shopId);
                        }
                    });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                    builder.create().show();
                }else {
                    Toast.makeText(getContext(), "权限不足", Toast.LENGTH_SHORT).show();
                }
                    return true;
                }
            });

    }

    //    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity()));
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.intent.action.CART_BROADCAST");
//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String msg = intent.getStringExtra("data");
//                if ("refresh".equals(msg)) {
//                    initNet();
//                }
//
//            }
//        };
//    }
}
