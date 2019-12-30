package com.yunsai.ops.store_management.home1;


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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yunsai.ops.store_management.home.ParticularsActivity;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.adapter.ListAdapterto;
import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.CodBean;
import com.yunsai.ops.store_management.base.Listbean;
import com.yunsai.ops.store_management.base.Listbeanss;
import com.yunsai.ops.store_management.mvp.MVPBaseFragment;
import com.yunsai.ops.store_management.util.ShareUtils;
import com.yunsai.ops.store_management.util.UrlUtil;

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

public class Home1Fragment extends MVPBaseFragment<Home1Contract.View, Home1Presenter> implements Home1Contract.View {
    Context context;
    private View view;
    private HttpApiHome1 httpApi;
    private Listbean listbean = new Listbean();
    private FrameLayout mF1fragmentContainer;
    private FloatingActionButton mF1floating;
    private EditText mF1shopSearch;
    private ImageView mF1shopimgviewbuttom;
    private SwipeRefreshLayout mF1swiperfreshlayout;
    private ListView mF1listview;
    private ListAdapterto listAdapter;
    private Listbeanss listbeanss;
    private String shopId;
    String key = null;
    String Keyss = "0";
    String keys = "1";
    String keyst="2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpApi = retrofit.create(HttpApiHome1.class);
        key = ShareUtils.get(getContext(),"key");
        initView(view);
        initNet();
        initDate();
        initlong();
        initReshi();
        return view;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            listAdapter = new ListAdapterto(getContext(), listbeanss.getRECORDS());
            mF1listview.setAdapter(listAdapter);
            mF1swiperfreshlayout.setRefreshing(false);
        }
    };

    private void initView(View view) {
        mF1fragmentContainer = view.findViewById(R.id.f1fragment_container);
        mF1floating = view.findViewById(R.id.f1floating);
        mF1floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (key.equals(Keyss)||key.equals(keys)){
                    Intent intent = new Intent(getContext(),ParticularsFloatToActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "权限不足", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //搜索功能
        mF1shopSearch = view.findViewById(R.id.f1shop_search);
        mF1shopimgviewbuttom = view.findViewById(R.id.f1shopimgviewbuttom);
        mF1shopSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mF1shopSearch.getText()==null) {
                    mF1listview.clearTextFilter();//搜索文本为空时，清除ListView的过滤
                }
                else {
//                    listview.setFilterText(s.toString().trim());//设置过滤关键字
                    listAdapter.getFilter().filter(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mF1shopimgviewbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = mF1shopSearch.getText().toString().trim();
                if (TextUtils.isEmpty(search)) {
                    mF1listview.clearTextFilter();//搜索文本为空时，过滤设置
                } else
//                    search_list.clearTextFilter();
                    mF1listview.setFilterText(search);//设置过滤关键字
            }
        });

        mF1swiperfreshlayout = view.findViewById(R.id.f1swiperfreshlayout);
        mF1listview = view.findViewById(R.id.f1listview);
    }
    private void initDate() {
        mF1listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("DJL:::", "onItemClick: "+"djdjdjdjdj");
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
                Intent intent = new Intent(getContext(), ParticularsToActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
    }
    private void initReshi() {
        mF1swiperfreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

        mF1listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (key.equals(keys)||key.equals(Keyss)) {
                    shopId = listbeanss.getRECORDS().get(i).getShopId();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("确定删除?");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapterView.getItemAtPosition(i);
//                        listbean.setShopId(shopId);
//                        String s = JSON.toJSONString(listbean);
                            Log.i("好了：", "onClick: " + shopId);

                            Observable<BaseCod> observable = httpApi.getData(shopId);
                            observable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<BaseCod>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(BaseCod baseCod) {
                                            int parseInt = Integer.parseInt(baseCod.getResCode());
                                            if (parseInt==200) {
                                                Toast.makeText(getContext(), "删除列表项", Toast.LENGTH_SHORT).show();
                                                Log.i("onNEXT", "onNext: " + baseCod.getResCode().toString());
                                            }else {
                                                Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                                            }
                                            handler.sendEmptyMessage(1);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Toast.makeText(getContext(), "服务器连接超时", Toast.LENGTH_SHORT).show();
                                            Log.e("onError", "onError: ", e);
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });


//                        adapterView.getItemAtPosition(i);
                            mF1listview.removeFooterView(getView());
                            listAdapter.notifyDataSetChanged();
                            mF1listview.invalidate();

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
}
