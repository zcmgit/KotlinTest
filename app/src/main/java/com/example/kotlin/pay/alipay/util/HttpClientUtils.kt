//package com.example.kotlin.pay.alipay.util
//
//import android.annotation.SuppressLint
//import android.util.Log
//
//
//import java.net.URLEncoder
//import java.security.SecureRandom
//import java.security.cert.CertificateException
//import java.security.cert.X509Certificate
//
//import javax.net.ssl.HostnameVerifier
//import javax.net.ssl.SSLContext
//import javax.net.ssl.SSLSession
//import javax.net.ssl.SSLSocketFactory
//import javax.net.ssl.TrustManager
//import javax.net.ssl.X509TrustManager
//
//import okhttp3.MediaType
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.RequestBody
//import okhttp3.logging.HttpLoggingInterceptor
//
//
//class HttpClientUtils {
//    init {
//        if (httpClient == null) {
//            httpClient = OkHttpClient().newBuilder()
//                    .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
//                        override fun log(message: String?) {
//                            Log.d("TAG", message)
//                        }
//                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
//                    .sslSocketFactory(createSSLSocketFactory()!!)
//                    .hostnameVerifier(TrustAllHostnameVerifier())
//                    .build()
//        }
//    }
//
//    @SuppressLint("TrulyRandom")
//    private fun createSSLSocketFactory(): SSLSocketFactory? {
//        var sSLSocketFactory: SSLSocketFactory? = null
//        try {
//            val sc = SSLContext.getInstance("TLS")
//            sc.init(null, arrayOf<TrustManager>(TrustAllManager()), SecureRandom())
//            sSLSocketFactory = sc.socketFactory
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return sSLSocketFactory
//    }
//
//    private inner class TrustAllManager : X509TrustManager {
//        @Throws(CertificateException::class)
//        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
//        }
//
//        @Throws(CertificateException::class)
//        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
//        }
//
//        override fun getAcceptedIssuers(): Array<X509Certificate> {
////            return arrayOfNulls<X509Certificate>>(0)
//            return arrayOf()
//        }
//    }
//
//    private inner class TrustAllHostnameVerifier : HostnameVerifier {
//        override fun verify(hostname: String, session: SSLSession): Boolean {
//            return true
//        }
//    }
//
//    companion object {
//
//        private val MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8")//mdiatype 这个需要和服务端保持一致
//
//        private var mInstance: HttpClientUtils? = null
//
//        private var httpClient: OkHttpClient? = null
//
//        fun getInstance(): HttpClientUtils {
//            if (null == mInstance) {
//                synchronized(HttpClientUtils::class.java) {
//                    if (null == mInstance) {
//                        mInstance = HttpClientUtils()
//                    }
//                }
//            }
//            return mInstance!!
//        }
//
//        fun postSyn(url: String, params: Map<String, String>): String {
//            var url = url
//            val tempParams = StringBuilder()
//            var pos = 0
//            for (key in params.keys) {
//                //处理参数
//                if (pos > 0) {
//                    tempParams.append("&")
//                }
//                //对参数进行URLEncoder
//                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(params[key], "utf-8")))
//                pos++
//            }
//            //补全请求地址
//            url = String.format("%s?%s", url, tempParams.toString())
//            //创建一个请求实体对象 RequestBody
//            val body = RequestBody.create(MEDIA_TYPE_JSON, params.toString())
//            val request = Request.Builder().url(url).build()
//            val call = httpClient!!.newCall(request)
//            val response = call.execute()
//            return if (response.isSuccessful) {
//                response.body()!!.string()
//            } else {
//                throw Exception("connect error")
//            }
//        }
//    }
//
//
//}
package com.eshangm.cloudpos.pay.alipay

import android.annotation.SuppressLint
import android.util.Log


import java.net.URLEncoder

import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import okhttp3.Call
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


class HttpClientUtils {
    init {
        if (httpClient == null) {
            httpClient = OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("OkHttpClient", message) }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .sslSocketFactory(createSSLSocketFactory()!!)
                    .hostnameVerifier(TrustAllHostnameVerifier())
                    .build()
        }
    }

    @SuppressLint("TrulyRandom")
    private fun createSSLSocketFactory(): SSLSocketFactory? {
        var sSLSocketFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf<TrustManager>(TrustAllManager()), SecureRandom())
            sSLSocketFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sSLSocketFactory
    }

    fun postSyn(url: String, params: HashMap<String, String>): String {
        var url = url
        val tempParams = StringBuilder()
        var pos = 0
        for (key in params.keys) {
            //处理参数
            if (pos > 0) {
                tempParams.append("&")
            }
            //对参数进行URLEncoder
            tempParams.append(String.format("%s=%s", key, URLEncoder.encode(params[key], "utf-8")))
            pos++
        }
        //补全请求地址
        url = String.format("%s?%s", url, tempParams.toString())
        //创建一个请求实体对象 RequestBody
        val body = RequestBody.create(MEDIA_TYPE_JSON, params.toString())
        val request = Request.Builder().url(url).build()
        val call = httpClient!!.newCall(request)
        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()!!.string()
        } else {
            throw Exception("connect error")
        }
    }

    private inner class TrustAllManager : X509TrustManager {
        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate?> {
            return arrayOfNulls(0)
        }
    }

    private inner class TrustAllHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }

    companion object {

        private val MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8")//mdiatype 这个需要和服务端保持一致

        private var mInstance: HttpClientUtils? = null

        private var httpClient: OkHttpClient? = null

        fun getInstance(): HttpClientUtils {
            if (null == mInstance) {
                synchronized(HttpClientUtils::class.java) {
                    if (null == mInstance) {
                        mInstance = HttpClientUtils()
                    }
                }
            }

            return mInstance!!
        }
    }


}
