package com.example.kotlin.pay.wxpay.util

import com.github.wxpay.sdk.WXPayConfig
import java.io.InputStream


class WXPayConfigImpl : WXPayConfig {

    override fun getAppID(): String {
        return WXPayConstant.APPID
    }

    override fun getMchID() : String {
        return WXPayConstant.MCHID
    }

    override fun getKey(): String {
        return WXPayConstant.KEY
    }

    override fun getCertStream(): InputStream? {
        return null
    }

    override fun getHttpConnectTimeoutMs(): Int {
        return 10000
    }

    override fun getHttpReadTimeoutMs(): Int {
        return 10000
    }

}
