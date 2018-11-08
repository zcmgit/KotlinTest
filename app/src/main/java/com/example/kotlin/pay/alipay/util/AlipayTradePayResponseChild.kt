package com.example.kotlin.pay.alipay.util

import com.alipay.api.response.AlipayTradePayResponse
import com.google.gson.annotations.SerializedName

class AlipayTradePayResponseChild {

    @SerializedName("alipay_trade_pay_response")
    var alipayTradePayResponse: AlipayTradePayResponse? = null

}
