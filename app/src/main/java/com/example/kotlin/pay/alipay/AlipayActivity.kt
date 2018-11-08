package com.example.kotlin.pay.alipay

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlin.R
import com.example.kotlin.pay.alipay.util.AliPayImpl
import com.github.wxpay.sdk.WXPayConstants
import kotlinx.android.synthetic.main.pay_activity.*
import org.jetbrains.anko.toast
import java.util.HashMap
import kotlin.concurrent.thread

/**
 * @author zcm
 * @create 2018/11/08
 * @Describe
 */
class AlipayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_activity)

        pay_btn.setOnClickListener {
            thread {
                val code = wx_pay_edit.text.toString()
                val map = HashMap<String, String>()
                map["body"] = "农夫山泉"
                map["total_fee"] = 0.01.toString()
                map["out_trade_no"] = "999011811080907310001"
                map["auth_code"] = code
                val payResult = AliPayImpl.aliTradePay(map)
                if (payResult == WXPayConstants.SUCCESS) {
                    Log.d("TAG","支付成功")
                } else {
                    Log.d("TAG","支付失败")
                }
            }
        }
    }
}