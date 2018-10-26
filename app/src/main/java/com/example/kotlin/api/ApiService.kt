package com.zhangqie.ko1.mvp_retrofit_rxjava.api

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface ApiService {

    @FormUrlEncoded
    @POST("url")
    abstract fun login(@FieldMap params: Map<String, String>): Observable<String>

}