package com.example.kotlin.multilist

import com.google.gson.annotations.SerializedName

/**
 * @author zcm
 * @create 2018/11/06
 * @Describe
 */
data class UserListInfo(

        @SerializedName("name")
        var name : String?,

        @SerializedName("age")
        var age : Int ?,

        @SerializedName("pwd")
        var pwd : String ?,

        @SerializedName("type")
        var type : Int

)
