package com.example.kotlin.pay.alipay.util

import com.google.gson.annotations.SerializedName

class AlipayTradeQueryRequestBuilder : RequestBuilder() {

    override val bizContent = BizContent()

    val tradeNo: String?
        get() = bizContent.tradeNo

    val outTradeNo: String?
        get() = bizContent.outTradeNo

    override fun validate(): Boolean {
        if (bizContent.tradeNo!!.isEmpty() && bizContent.outTradeNo!!.isEmpty()) {
            throw IllegalStateException("tradeNo and outTradeNo can not both be NULL!")
        }
        return true
    }

    override fun toString(): String {
        val sb = StringBuilder("AlipayTradeQueryRequestBuilder{")
        sb.append("bizContent=").append(bizContent)
        sb.append(", super=").append(super.toString())
        sb.append('}')
        return sb.toString()
    }

    fun setTradeNo(tradeNo: String): AlipayTradeQueryRequestBuilder {
        bizContent.tradeNo = tradeNo
        return this
    }

    fun setOutTradeNo(outTradeNo: String): AlipayTradeQueryRequestBuilder {
        bizContent.outTradeNo = outTradeNo
        return this
    }

    class BizContent {
        // 支付宝交易号,和商户订单号不能同时为空, 如果同时存在则通过tradeNo查询支付宝交易
        @SerializedName("trade_no")
        internal var tradeNo: String? = null

        // 商户订单号，通过此商户订单号查询当面付的交易状态
        @SerializedName("out_trade_no")
        internal var outTradeNo: String? = null

        override fun toString(): String {
            val sb = StringBuilder("BizContent{")
            sb.append("tradeNo='").append(tradeNo).append('\'')
            sb.append(", outTradeNo='").append(outTradeNo).append('\'')
            sb.append('}')
            return sb.toString()
        }
    }

}

