package com.example.kotlin.pay.alipay.util

import com.alipay.api.internal.util.AlipaySignature
import com.eshangm.cloudpos.pay.alipay.HttpClientUtils
import com.google.gson.Gson

import java.text.SimpleDateFormat
import java.util.Date
import java.util.HashMap
import java.util.TimeZone

object AliPayImpl {

    fun aliTradePay(params: MutableMap<String, String>): String {
        //参数校验
        if (!params.containsKey("body") || params["body"]!!.isEmpty()
                || !params.containsKey("out_trade_no") || params["out_trade_no"]!!.isEmpty()
                || !params.containsKey("total_fee") || params["total_fee"]!!.isEmpty()
                || !params.containsKey("auth_code") || params["auth_code"]!!.isEmpty()) {
            return AliPayConstants.FAIL
        }
        //初始化config
        val aliPayConfigImpl = AliPayConfigImpl()
        //签名
        val contentMap = HashMap<String, String>()
        contentMap["app_id"] = aliPayConfigImpl.appID
        contentMap["method"] = AliPayConstants.METHOD
        contentMap["charset"] = AliPayConstants.CHARSET
        contentMap["sign_type"] = AliPayConstants.SIGN_TYPE_RSA2
        val timestamp = System.currentTimeMillis()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        df.timeZone = TimeZone.getTimeZone("GMT+8")
        contentMap["timestamp"] = df.format(Date(timestamp))
        contentMap["version"] = "1.0"
        val alipayTradePayRequestBuilder = AlipayTradePayRequestBuilder()
                .setScene("bar_code")
                .setOutTradeNo(params.getValue("out_trade_no"))
                .setAuthCode(params.getValue("auth_code"))
                .setSubject(params.getValue("body"))
                .setTotalAmount(params.getValue("total_fee"))
        contentMap["biz_content"] = alipayTradePayRequestBuilder.toJsonString()
        val content = AlipaySignature.getSignContent(contentMap)
        val sign = AlipaySignature.rsa256Sign(content, aliPayConfigImpl.privateKey, AliPayConstants.CHARSET)
        contentMap["sign"] = sign
        val response = HttpClientUtils.getInstance().postSyn(AliPayConstants.URL, contentMap)
        val alipayTradePayResponseChild = Gson().fromJson<AlipayTradePayResponseChild>(response, AlipayTradePayResponseChild::class.java)
        val alipayTradePayResponse = alipayTradePayResponseChild.alipayTradePayResponse
        System.out.println(alipayTradePayResponse.toString())
        when (alipayTradePayResponse?.getCode()) {
            "10000" -> return AliPayConstants.SUCCESS
            "40004" -> return AliPayConstants.FAIL
            "10003" -> {
                Thread.sleep(5000)
                for (i in 0..2) {
                    val orderMap = HashMap<String, String>()
                    orderMap["app_id"] = aliPayConfigImpl.appID
                    orderMap["method"] = AliPayConstants.METHOD
                    orderMap["charset"] = AliPayConstants.CHARSET
                    orderMap["sign_type"] = AliPayConstants.SIGN_TYPE_RSA2
                    val timestamp1 = System.currentTimeMillis()
                    val df1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    df1.timeZone = TimeZone.getTimeZone("GMT+8")
                    orderMap["timestamp"] = df1.format(Date(timestamp1))
                    orderMap["version"] = "1.0"
                    val alipayTradeQueryRequestBuilder = AlipayTradeQueryRequestBuilder()
                            .setOutTradeNo(params.getValue("out_trade_no"))
                    orderMap["biz_content"] = alipayTradeQueryRequestBuilder.toJsonString()
                    val content1 = AlipaySignature.getSignContent(orderMap)
                    val sign1 = AlipaySignature.rsa256Sign(content1, aliPayConfigImpl.privateKey, AliPayConstants.CHARSET)
                    orderMap["sign"] = sign1
//                    val response1 = HttpClientUtils.getInstance().postSyn(AliPayConstants.URL, orderMap)
                    var response1 = HttpClientUtils.getInstance().postSyn(AliPayConstants.URL, orderMap)
                    val alipayTradeQueryResponseChild = Gson().fromJson<AlipayTradeQueryResponseChild>(response1, AlipayTradeQueryResponseChild::class.java)
                    val alipayTradeQueryResponse = alipayTradeQueryResponseChild.getAlipayTradePayResponse()
                    when (alipayTradeQueryResponse?.isSuccess) {
//                        "WAIT_BUYER_PAY" -> continue
                        false -> return AliPayConstants.FAIL
                        true -> return AliPayConstants.SUCCESS
                    }
                    Thread.sleep(5000)
                }
                return AliPayConstants.FAIL
            }
            "20000" -> return AliPayConstants.FAIL
        }

        return AliPayConstants.FAIL
    }

}
