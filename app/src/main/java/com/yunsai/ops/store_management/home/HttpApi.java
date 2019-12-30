package com.yunsai.ops.store_management.home;


import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.CodBean;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpApi {
    @POST("InterpositionServer")
    Observable<BaseCod> post(@Body String add);
    @POST("AddTab")
    Observable<BaseCod> postData(@Body String addData);
    @GET("Delete")
    Observable<BaseCod> getData(@Query("shopId")String shopID);
    @GET("ShopId")
    Observable<CodBean>getId();
}
