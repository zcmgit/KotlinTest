package com.example.kotlin.pay.wxpay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlin.R
import com.example.kotlin.pay.wxpay.util.WXPayImpl
import com.github.wxpay.sdk.WXPayConstants
import kotlinx.android.synthetic.main.pay_activity.*
import java.util.HashMap
import kotlin.concurrent.thread

/**
 * @author zcm
 * @create 2018/11/08
 * @Describe
 */
class WXPayActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_activity)

        pay_btn.setOnClickListener {
            var code = wx_pay_edit.text.toString()
            thread {
                val map = HashMap<String, String>()
                map["body"] = "牛栏山"
                map["total_fee"] = ((0.01 * 100)).toInt().toString()
                map["out_trade_no"] = "999011811080907310001"
                map["auth_code"] = code
                val payResult = WXPayImpl.wxMicroPay(map)
                if (payResult.equals(WXPayConstants.SUCCESS, true)) {
                    Log.d("TAG", "支付成功")
                } else {
                    Log.d("TAG", "支付失败")
                }
            }

        }
    }
}