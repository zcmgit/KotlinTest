package com.example.kotlin

import android.app.Application
import android.content.Context
import com.example.kotlin.dao.gen.DaoMaster
import com.example.kotlin.dao.gen.DaoSession

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
        lateinit var daoSession : DaoSession
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDao()
        context = applicationContext
    }

    fun initDao(){
        val helper = DaoMaster.DevOpenHelper(this,"User")//创建的数据库名。
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }

    open fun getDaoInstant(): DaoSession { //用于获得对象
        return daoSession
    }

    open fun getContext():Context{
        return context
    }
}