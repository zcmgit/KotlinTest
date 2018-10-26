package com.zhangqie.ko1.mvp_retrofit_rxjava.api

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by zhangqie on 2016/8/26.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("/api/services/cloudpos/organ/login")
    abstract fun login(@FieldMap params: Map<String, String>): Observable<String>


}