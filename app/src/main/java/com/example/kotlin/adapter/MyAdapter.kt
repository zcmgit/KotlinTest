package com.example.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin.R

/**
 * @author zcm
 * @create 2018/10/25
 * @Describe
 */
class MyAdapter(context2:Context,listInfo:ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>(){
    var context = context2    //接收变量
    var list = listInfo
    var myInter:MyInter? = null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        var view:View = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)
        var holder = MyHolder(view)
        return  holder
    }

    override fun getItemCount(): Int {
        return list?.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        p0?.text_id?.text = p1.toString()
        p0?.text_info?.text = list.get(p1)
        p0!!.itemView.setOnClickListener {
            myInter?.onclick(p1)
        }
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text_id: TextView = view.findViewById(R.id.text_id) as TextView
        var text_info:TextView = view.findViewById(R.id.text_info) as TextView
    }

    interface MyInter{    //自定义的接口（测试接口方式的使用）
        fun onclick(p0:Int)
    }
    fun setOnClickListener(inter:MyInter){
        this.myInter = inter
    }
}