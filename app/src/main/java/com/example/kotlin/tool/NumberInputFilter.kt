package com.example.kotlin.tool

import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils

import java.util.regex.Matcher
import java.util.regex.Pattern

class NumberInputFilter(private val minValue: Double//最小值
                        , private val maxValue: Double//最大值
                        , private val pointNum: Int//小数位数
                        , negative: Boolean) : InputFilter {
    private val mPattern: Pattern

    init {
        this.mPattern = if (negative == true) Pattern.compile("(\\-|[0-9]|\\.)*") else Pattern.compile("([0-9]|\\.)*")
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence {
        val sourceText = source.toString()
        val destText = dest.toString()
        //        String allText = destText + sourceText;
        val allText = destText.substring(0, dstart) + sourceText + destText.substring(dstart, destText.length)
        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return ""
        }
        //验证正则
        val matcher = mPattern.matcher(sourceText)
        if (!matcher.matches()) {
            return ""
        }
        //输入"."
        if ("." == sourceText) {
            if (destText.isEmpty()) {
                return "0."
            } else {
                if (destText.contains(".")) {
                    return ""
                }
                if (destText.length == 1 && destText == "-") {
                    return "0."
                }
            }
        }
        //输入"0"/"00"
        if ("0" == sourceText || "00" == sourceText) {
            if (destText.isEmpty()) {
                return "0."
            } else {
                if (destText.length == 1 && destText == "0") {
                    return "."
                }
                if (destText.length == 1 && destText == "-") {
                    return "0."
                }
            }
        }
        //输入"-"
        if ("-" == sourceText) {
            if (destText.isEmpty()) {

            } else {
                return ""
            }
        }

        //限制小数点位数
        if (destText.contains(".")) {
            val destLength = destText.length - 1 - destText.indexOf(".")
            val length = destLength + sourceText.length
            if (dstart <= destText.indexOf(".")) {

            } else {
                if (length > pointNum) {
                    return sourceText.subSequence(0, pointNum - destLength)
                }
            }
        }
        //验证输入金额的大小
        var sumText = 0.00
        try {
            sumText = java.lang.Double.parseDouble(allText)
        } catch (e: NumberFormatException) {
            sumText = 0.0
        }

        return if (sumText > maxValue || sumText < minValue) {
            ""
        } else dest.subSequence(dstart, dend).toString() + sourceText

    }

}
