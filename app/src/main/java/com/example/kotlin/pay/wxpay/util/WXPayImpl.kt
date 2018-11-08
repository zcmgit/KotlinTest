package com.example.kotlin.pay.wxpay.util


import com.github.wxpay.sdk.WXPay
import com.github.wxpay.sdk.WXPayConstants

import java.util.HashMap

object WXPayImpl {

    /**
     * 1.生产提交支付需要的参数
     * 2.提交刷卡支付
     * 3.根据返回值result_code 判断是否需要轮询查询订单状态
     *
     * @param "body"         商品名称
     * @param "total_fee"    支付金额
     * @param "out_trade_no" 订单号
     * @param "auth_code"    条形码
     */
//    @Throws(Exception::class)
    fun wxMicroPay(params: MutableMap<String, String>): String {
        //参数校验
        if (!params.containsKey("body") || params["body"]!!.isEmpty()
                || !params.containsKey("out_trade_no") || params["out_trade_no"]!!.isEmpty()
                || !params.containsKey("total_fee") || params["body"]!!.isEmpty()
                || !params.containsKey("auth_code") || params["auth_code"]!!.isEmpty()) {
            return WXPayConstants.FAIL.toString()
        }
        params.put("spbill_create_ip", IPAddressUtils.ipAddress)
        //初始化微信支付
        val wxPayConfig = WXPayConfigImpl()
        val wxPay = WXPay(wxPayConfig)
        //调用刷卡支付
        val resultPay = wxPay.microPay(params)
        //直接失败
        if (resultPay.get("return_code").equals(WXPayConstants.FAIL,true)) {
            return WXPayConstants.FAIL
        } else {
            //其他原因返回的失败
            if (resultPay.get("result_code").equals(WXPayConstants.FAIL,true)) {
                if (resultPay.get("err_code").equals("USERPAYING")) {
                    //等待用户输入密码，延时5s后每隔10s调用查询订单接口3次
                    Thread.sleep(5000)
                    for (i in 0..5) {
                        val orderParams = HashMap<String, String>()
                        orderParams.put("out_trade_no", params.getValue("out_trade_no"))
                        val resultOrder = wxPay.orderQuery(orderParams)
                        if (resultOrder.get("return_code").equals(WXPayConstants.FAIL,true)) {
                            return WXPayConstants.FAIL
                        } else {
                            if (resultOrder.get("result_code").equals(WXPayConstants.FAIL,true)) {
                                return WXPayConstants.FAIL
                            } else {
                                if (resultOrder.get("trade_state").equals(WXPayConstants.SUCCESS,true)) {
                                    return WXPayConstants.SUCCESS
                                } else {
                                    Thread.sleep(5000)
                                    continue
                                }
                            }
                        }
                    }
                } else {
                    return WXPayConstants.FAIL
                }
            } else {
                return WXPayConstants.SUCCESS
            }
        }

        return WXPayConstants.FAIL
    }

}
