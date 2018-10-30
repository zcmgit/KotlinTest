package com.example.kotlin.greendao.utils

import com.example.kotlin.App
import com.example.kotlin.greendao.bean.UserInfo

/**
 * @author zcm
 * @create 2018/10/29
 * @Describe
 */
class UserInfoUtil (){

    //添加数据
    fun insertUserInfo(userInfo: UserInfo){
        App().getDaoInstant().userInfoDao.insert(userInfo)
    }

    fun deleteUsserInfoById(id : Long){
        App().getDaoInstant().userInfoDao.deleteByKey(id)
    }

    fun updateUserInfo(userInfo: UserInfo){
        App().getDaoInstant().userInfoDao.update(userInfo)
    }

    fun queryAllInfo() : List<UserInfo>{
        return App().getDaoInstant().userInfoDao.loadAll()
    }
}