package com.example.kotlin.dbflow.bean

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import mobi.porquenao.poc.kotlin.core.AppDatabase

/**
 * @author zcm
 * @create 2018/10/30
 * @Describe
 */

@Table(name = "user_info",database = AppDatabase :: class)
class PeopleInfo : BaseModel(){

    @PrimaryKey(autoincrement = true)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "name")
    var name : String = ""

    @Column(name = "pwd")
    var pwd : String = ""
}