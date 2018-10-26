package com.example.kotlin.list

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kotlin.R
import com.example.kotlin.adapter.MyAdapter
import kotlinx.android.synthetic.main.list.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/10/26
 * @Describe
 */
class ListActivity : AppCompatActivity() {

    var listDatas = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)
        var adapter = MyAdapter(this,initData()!!)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnClickListener(object :MyAdapter.MyInter{     //自定义接口回调
            override fun onclick(p0: Int) {
                toast("click：："+p0+"::"+listDatas.get(p0))
            }
        })
    }

    private fun initData(): ArrayList<String>? {
        for (i in 0..50){
            var test = "测试Test"
            listDatas.add(i,test)
        }
        Log.d("tag","list.size------"+ listDatas.size)
        return listDatas
    }
}