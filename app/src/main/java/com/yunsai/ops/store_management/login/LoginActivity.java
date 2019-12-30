package com.yunsai.ops.store_management.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yunsai.ops.store_management.MainActivity;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.mvp.MVPBaseActivity;
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


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    private EditText mEditTextPhone;
    private EditText mEditTextPassword;
    private Button mLogin;
    private TextView mRegister;
    private LoginAIP loginAIP;
    String phone;
    String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        initView();
        initNet();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.urls)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        loginAIP = retrofit.create(LoginAIP.class);
    }


    private void initNet() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mEditTextPhone.getText().toString();
                password = mEditTextPassword.getText().toString();
                Log.i("INITNET::", "onClick: "+phone+password);
                initData();
            }
        });
    }

    private void initData() {
        Observable<BaseCod> observable = loginAIP.login(phone,password);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCod>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseCod baseCod) {

                        int resCode = Integer.parseInt(baseCod.getResCode());
                        //存储权限key值
                        ShareUtils.save(getContext(),"key",baseCod.getUserKey());

                        if (resCode==200){
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), PHoneOnActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();
                        Log.e("登录失败", "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void initView() {
        mEditTextPhone = findViewById(R.id.editText_phone);
        mEditTextPassword = findViewById(R.id.editText_password);
        mLogin = findViewById(R.id.login);
        mRegister = findViewById(R.id.register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
