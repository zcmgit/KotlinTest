package com.example.kotlin.pay.alipay.util

import com.google.gson.Gson

abstract class RequestBuilder {

    // 获取bizContent对象，用于下一步转换为json字符串
    abstract val bizContent: Any

    // 验证请求对象
    abstract fun validate(): Boolean

    // 将bizContent对象转换为json字符串
    fun toJsonString(): String {
        // 使用gson将对象转换为json字符串
        /**
         * See https://sites.google.com/site/gson/gson-user-guide#TOC-Using-Gson
         * Object Examples
         *
         * class BagOfPrimitives {
         * private int value1 = 1;
         * private String value2 = "abc";
         * private transient int value3 = 3;
         * BagOfPrimitives() {
         * // no-args constructor
         * }
         * }
         *
         * (Serialization)
         * BagOfPrimitives obj = new BagOfPrimitives();
         * Gson gson = new Gson();
         * String json = gson.toJson(obj);
         * ==> json is {"value1":1,"value2":"abc"}
         */
        return Gson().toJson(this.bizContent)
    }

    override fun toString(): String {
        val sb = StringBuilder("RequestBuilder{")
        return sb.toString()
    }

}

