package com.example.kotlin.pay.alipay.util

import com.google.gson.annotations.SerializedName
import java.util.regex.Pattern


/**
 * Created by liuyangkly on 16/3/3.
 */
class AlipayTradePayRequestBuilder : RequestBuilder() {

    override val bizContent = BizContent()

    val scene: String?
        get() = bizContent.scene

    val authCode: String?
        get() = bizContent.authCode

    val outTradeNo: String?
        get() = bizContent.outTradeNo

    val totalAmount: String?
        get() = bizContent.totalAmount

    val subject: String?
        get() = bizContent.subject


    override fun validate(): Boolean {
        if (null == bizContent.scene || bizContent.scene!!.isEmpty()) {
            throw NullPointerException("scene should not be NULL!")
        }
        if (null == bizContent.authCode || bizContent.authCode!!.isEmpty()) {
            throw NullPointerException("auth_code should not be NULL!")
        }
        if (null == bizContent.authCode || bizContent.authCode!!.isEmpty() || !Pattern.matches("^\\d{10,}$", bizContent.authCode)) {
            throw IllegalStateException("invalid auth_code!")
        }
        if (bizContent.outTradeNo!!.isEmpty()) {
            throw NullPointerException("out_trade_no should not be NULL!")
        }
        if (null == bizContent.totalAmount || bizContent.totalAmount!!.isEmpty()) {
            throw NullPointerException("total_amount should not be NULL!")
        }
        if (null == bizContent.subject || bizContent.subject!!.isEmpty()) {
            throw NullPointerException("subject should not be NULL!")
        }

        return true
    }

    override fun toString(): String {
        val sb = StringBuilder("AlipayTradePayRequestBuilder{")
        sb.append("bizContent=").append(bizContent)
        sb.append(", super=").append(super.toString())
        sb.append('}')
        return sb.toString()
    }

    fun setScene(scene: String): AlipayTradePayRequestBuilder {
        bizContent.scene = scene
        return this
    }

    fun setAuthCode(authCode: String): AlipayTradePayRequestBuilder {
        bizContent.authCode = authCode
        return this
    }

    fun setOutTradeNo(outTradeNo: String): AlipayTradePayRequestBuilder {
        bizContent.outTradeNo = outTradeNo
        return this
    }

    fun setTotalAmount(totalAmount: String): AlipayTradePayRequestBuilder {
        bizContent.totalAmount = totalAmount
        return this
    }

    fun setSubject(subject: String): AlipayTradePayRequestBuilder {
        bizContent.subject = subject
        return this
    }

    class BizContent {
        // 支付场景，条码支付场景为bar_code
        @SerializedName("scene")
        internal var scene: String? = null

        // 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码
        @SerializedName("auth_code")
        internal var authCode: String? = null

        // 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        @SerializedName("out_trade_no")
        internal var outTradeNo: String? = null

        // 订单总金额，整形，此处单位为元，精确到小数点后2位，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        @SerializedName("total_amount")
        internal var totalAmount: String? = null

        // 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
        @SerializedName("subject")
        internal var subject: String? = null

        override fun toString(): String {
            val sb = StringBuilder("BizContent{")
            sb.append("scene='").append(scene).append('\'')
            sb.append(", authCode='").append(authCode).append('\'')
            sb.append(", outTradeNo='").append(outTradeNo).append('\'')
            sb.append(", totalAmount='").append(totalAmount).append('\'')
            sb.append(", subject='").append(subject).append('\'')
            sb.append('}')
            return sb.toString()
        }

    }
}
