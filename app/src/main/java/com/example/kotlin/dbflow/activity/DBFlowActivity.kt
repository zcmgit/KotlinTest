package com.example.kotlin.dbflow.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.R
import com.example.kotlin.dbflow.bean.PeopleInfo
import com.example.kotlin.dbflow.bean.PeopleUtil
import kotlinx.android.synthetic.main.db_test.*

/**
 * @author zcm
 * @create 2018/10/30
 * @Describe
 */
class DBFlowActivity : AppCompatActivity(){

    private var mPeopleInfos: MutableList<PeopleInfo> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_test)

        var peopleInfo = PeopleInfo()
        peopleInfo.name = "张三"
        peopleInfo.pwd = "123456"
        peopleInfo.save()

        mPeopleInfos = PeopleUtil.getAll()
        var txt =  "插入数据库之后："+ mPeopleInfos?.size+"条"+"\r\n"
        for (i in 0 .. (mPeopleInfos?.size!!.minus(1))){
            txt = txt + mPeopleInfos?.get(i)?.id +" "+mPeopleInfos?.get(i)?.name+"  "+mPeopleInfos?.get(i)?.pwd+"\r\n"
        }

        peopleInfo.pwd = "54321"
        peopleInfo.update()
        mPeopleInfos = PeopleUtil.getAll()
        txt =  txt + "更新数据库之后："+ mPeopleInfos?.size+"条"+"\r\n"
        for (i in 0 .. (mPeopleInfos?.size!!.minus(1))){
            txt = txt + mPeopleInfos?.get(i)?.id +" "+mPeopleInfos?.get(i)?.name+"  "+mPeopleInfos?.get(i)?.pwd+"\r\n"
        }

        peopleInfo.delete()
        mPeopleInfos = PeopleUtil.getAll()
        txt =  txt + "删除数据之后："+ mPeopleInfos?.size+"条"+"\r\n"
        for (i in 0 .. (mPeopleInfos?.size!!.minus(1))){
            txt = txt + mPeopleInfos?.get(i)?.id +" "+mPeopleInfos?.get(i)?.name+"  "+mPeopleInfos?.get(i)?.pwd+"\r\n"
        }

        db_content_text.text = txt
    }
}