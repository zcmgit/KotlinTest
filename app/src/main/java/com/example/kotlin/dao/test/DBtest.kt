package com.example.kotlin.dao.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.R
import com.example.kotlin.dao.bean.UserInfo
import com.example.kotlin.dao.utils.UserInfoUtil
import kotlinx.android.synthetic.main.db_test.*
import kotlinx.android.synthetic.main.list_item.*

/**
 * @author zcm
 * @create 2018/10/29
 * @Describe
 */
class DBtest : AppCompatActivity(){
    private var userInfoUtil : UserInfoUtil ? = null
    private var userInfo : UserInfo ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_test)
        userInfo = UserInfo()
        userInfo!!.password = "pwd"
        userInfo!!.name = "name"
        userInfoUtil = UserInfoUtil()
        userInfoUtil!!.insertUserInfo(userInfo!!)

        var userInfos : List<UserInfo> ? = null
        userInfos = userInfoUtil!!.queryAllInfo()

        var txt =  "插入数据库之后："+userInfos.size+"条"+"\r\n"
        for (i in 0 .. (userInfos.size - 1)){
            txt = txt + userInfos.get(i).id+" "+userInfos.get(i).name +" "+userInfos.get(i).password+"\r\n"
        }

        userInfo!!.password = "123456"
        userInfoUtil!!.updateUserInfo(userInfo!!)
        userInfos = userInfoUtil!!.queryAllInfo()
        txt = txt + "更新数据之后："+userInfos.size+"条"+"\r\n"
        for (i in 0 .. (userInfos.size - 1)){
            txt = txt + userInfos.get(i).id+" "+userInfos.get(i).name +" "+userInfos.get(i).password+"\r\n"
        }

        userInfoUtil!!.deleteUsserInfoById(userInfo!!.id)
        userInfos = userInfoUtil!!.queryAllInfo()
        txt = txt + "删除数据之后："+userInfos.size+"条"+"\r\n"
        for (i in 0 .. (userInfos.size - 1)){
            txt = txt + userInfos.get(i).id+" "+userInfos.get(i).name +" "+userInfos.get(i).password+"\r\n"
        }

        db_content_text.text = txt

    }
}