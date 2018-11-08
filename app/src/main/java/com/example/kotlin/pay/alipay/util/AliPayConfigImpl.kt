package com.example.kotlin.pay.alipay.util


class AliPayConfigImpl : AliPayConfig() {
    override val publicKey: String
        get() = AliPayConstants.PUBLICKEY
    override val privateKey: String
        get() = AliPayConstants.PRIVATEKEY
    override val appID: String
        get() = AliPayConstants.APPID
}
