package com.zhangqie.mvphttp.mvp_retrofit_rxjava.presenter

import com.zhangqie.mvphttp.mvp_retrofit_rxjava.model.IMode
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.model.LoginModel
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.view.IView

/**
 * Created by zhangqie on 2017/6/28.
 */
class LoginPresenter : BasePresenter<IView>(){

    var model: LoginModel? = null

    init {
        model = LoginModel()
    }

    fun onRequest(map: Map<String,String>){
        var iView = getView()
        if (model != null && iView != null) {

            //验证网络  无网络不加载
            if (false){
                return
            }
            model!!.onRequest( map, object : IMode.CallBackListenter {

                override fun onDataCallBackListenter(data: String) {
                    iView.onLoadContributorComplete(data)

                }
                override fun onError(error: String) {
                    iView.onError(error)
                }

            })
        }
    }


    override fun detachView() {//重写 销毁
        super.detachView()
        if (model != null){
            model!!.detachModel()
        }
    }



}