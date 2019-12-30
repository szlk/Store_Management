package com.yunsai.ops.store_management.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yunsai.ops.store_management.R;
import com.yunsai.ops.store_management.base.BaseCod;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText mRegisterEditTextPhone;
    private EditText mRegisterEditTextPassword;
    private Button mRegisterRegister;
    private FloatingActionButton mFab;
    private LoginAIP loginAIP;
    String phone;
    String password;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        initView();
    }

    private void initView() {
        mRegisterEditTextPhone = findViewById(R.id.register_editText_phone);
        mRegisterEditTextPassword = findViewById(R.id.register_editText_password);
        mRegisterRegister = findViewById(R.id.register_register);
        mRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mRegisterEditTextPhone.getText().toString();
                password = mRegisterEditTextPassword.getText().toString();
                Log.i("注册账号", "onClick: "+phone+password);

                Observable<BaseCod> observable = loginAIP.register(phone,password);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseCod>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseCod baseCod) {

                                Log.e("OnNext::::","error"+baseCod.getResCode());
                                int resCode = Integer.parseInt(baseCod.getResCode());
                                if (resCode==200){
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(RegisterActivity.this, "注册失败" , Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error::::","error",e);
                                Toast.makeText(RegisterActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }
}
