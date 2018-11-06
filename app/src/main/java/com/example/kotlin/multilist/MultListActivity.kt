package com.example.kotlin.multilist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.kotlin.R
import com.example.kotlin.list.adapter.MyAdapter
import com.example.kotlin.multilist.adapter.MultListAdapter
import kotlinx.android.synthetic.main.mult_list_activity.*

/**
 * @author zcm
 * @create 2018/11/06
 * @Describe
 */
class MultListActivity : AppCompatActivity(){
    var mList : MutableList<UserListInfo> = ArrayList<UserListInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mult_list_activity)
        mList = initData()
        var multAdapter = MultListAdapter(this,mList)
        mult_list.layoutManager = LinearLayoutManager(this)
        mult_list.adapter = multAdapter
        multAdapter.setOnClickListener(object : MultListAdapter.MyInter{
            override fun onclick(p0: Int) {
                mList.removeAt(p0)
                multAdapter.deleteItem(p0)
            }
        })

        add_item.setOnClickListener {
            var userListInfo : UserListInfo = UserListInfo("name"+(mList.size + 1),12,"123456",(mList.size%2 + 1))
            multAdapter.addItem(userListInfo)
        }
    }

    private fun initData() : MutableList<UserListInfo>{
        var mList = ArrayList<UserListInfo>()
        var userListInfo : UserListInfo = UserListInfo("name1",12,"123456",1)
        var userListInfo1 : UserListInfo = UserListInfo("name2",13,"123456",2)
        mList.add(userListInfo)
        mList.add(userListInfo1)
        return mList!!
    }


}