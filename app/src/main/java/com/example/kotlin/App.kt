package com.example.kotlin

import android.app.Application

/**
 * @author zcm
 * @create 2018/10/25
 * @Describe
 */
class App : Application() {
    companion object {
        // 伴生对象
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}