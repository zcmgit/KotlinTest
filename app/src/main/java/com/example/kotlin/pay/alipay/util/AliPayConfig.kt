package com.example.kotlin.pay.alipay.util

abstract class AliPayConfig {

    internal abstract val appID: String

    internal abstract val publicKey: String

    internal abstract val privateKey: String

}
