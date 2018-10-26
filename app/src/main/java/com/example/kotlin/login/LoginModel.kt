package com.zhangqie.mvphttp.mvp_retrofit_rxjava.model

import com.example.kotlin.base.BaseModel
import com.example.kotlin.api.ApiManager
import rx.Subscriber


class LoginModel : BaseModel(),IMode{

    var strApiService = ApiManager.getInstance().strApiService

    var jsonApiService = ApiManager.getInstance().jsonApiService
    override fun onRequest(map: Map<String, String>, callBackListenter: IMode.CallBackListenter) {
        addSubscription(jsonApiService.login(map), object : Subscriber<String>() {
            override fun onCompleted() {

            }

            override fun onNext(result: String) {
                callBackListenter.onDataCallBackListenter(result)

            }

            override fun onStart() {
                super.onStart()
            }

            override fun onError(e: Throwable) {
                callBackListenter.onError("数据加载失败"+e?.message)
            }
        })
    }



}