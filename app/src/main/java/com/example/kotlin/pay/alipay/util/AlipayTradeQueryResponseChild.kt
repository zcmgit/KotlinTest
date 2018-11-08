package com.example.kotlin.pay.alipay.util

import com.alipay.api.response.AlipayTradePayResponse
import com.alipay.api.response.AlipayTradeQueryResponse
import com.google.gson.annotations.SerializedName

class AlipayTradeQueryResponseChild {

    private var alipayTradePayResponse: AlipayTradePayResponse? = null

    fun getAlipayTradePayResponse(): AlipayTradePayResponse? {
        return alipayTradePayResponse
    }

    fun setAlipayTradePayResponse(alipayTradePayResponse: AlipayTradePayResponse) {
        this.alipayTradePayResponse = alipayTradePayResponse
    }
}
