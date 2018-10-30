package com.example.kotlin.dbflow.bean

import com.raizlabs.android.dbflow.sql.language.Insert
import com.raizlabs.android.dbflow.sql.language.Select

/**
 * @author zcm
 * @create 2018/10/30
 * @Describe
 */
object PeopleUtil {
    fun getAll(): MutableList<PeopleInfo> {
        return Select()
                .from(PeopleInfo::class.java)
                .where()
                .orderBy(PeopleInfo_Table.id,false)
                .queryList()
    }
}