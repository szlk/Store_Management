package com.yunsai.ops.store_management.home1;


import com.yunsai.ops.store_management.base.BaseCod;
import com.yunsai.ops.store_management.base.CodBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpApiHome1 {
    @POST("InterpositionServerTo")
    Observable<BaseCod> post(@Body String add);
    @POST("AddTabTo")
    Observable<BaseCod> postData(@Body String addData);
    @GET("DeleteTo")
    Observable<BaseCod> getData(@Query("shopId") String shopID);
    @GET("ShopIdTo")
    Observable<CodBean>getId();
}
