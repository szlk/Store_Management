package com.yunsai.ops.store_management.login;

import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.Cartogram;
import com.yunsai.ops.store_management.base.PhoneBase;
import com.yunsai.ops.store_management.base.PhoneCode;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginAIP {
    @GET("RegisterParticulars")
    Observable<BaseCod> login(@Query("userAccount") String apiaccount, @Query("userPassword") String apipassword);

    @GET("Register")
    Observable<BaseCod> register(@Query("userAccount") String RegisterServletaccount,
                                 @Query("userPassword") String RegisterServletpassword);
    @GET("ShopPhone")
    Observable<PhoneCode> phoneba();

    @GET("PhoneDelete")
    Observable<BaseCod> phoneDelete(@Query("userId") String phone);

    @GET("PhoneInterpositionServer")
    Observable<BaseCod> InterpositionServer(@Query("userId") String Interposition,@Query("jurisdiction")String  add);

    @GET("ShopIdS")
    Observable<Cartogram> CARTOGRAM_OBSERVABLE();
}
