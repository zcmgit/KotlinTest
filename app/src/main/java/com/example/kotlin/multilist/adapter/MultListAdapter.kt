package com.example.kotlin.multilist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin.R
import com.example.kotlin.list.adapter.MyAdapter
import com.example.kotlin.multilist.UserListInfo

/**
 * @author zcm
 * @create 2018/11/06
 * @Describe
 */
class MultListAdapter(mContext: Context, mList: MutableList<UserListInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var context = mContext
    var mListInfo = mList
    var myInter: MultListAdapter.MyInter? = null
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if (p0 is MyHolder) {
            var holder: MyHolder = p0
            holder.user_name.text = mListInfo.get(p1).name
            holder.delete_icon.setOnClickListener {
                myInter?.onclick(p1)
            }
        } else if (p0 is MyHolderSecond) {
            var holder: MyHolderSecond = p0
            holder.user_age.text = mListInfo.get(p1).age.toString()
            holder.user_name.text = mListInfo.get(p1).name
            holder.user_pwd.text = mListInfo.get(p1).pwd
            holder.delete_icon.setOnClickListener {
                myInter?.onclick(p1)
            }
        }
    }

    fun deleteItem(p0: Int) {
        notifyDataSetChanged()
    }

    fun addItem(userListInfo: UserListInfo) {
        mListInfo.add(userListInfo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view: View
        var holder: RecyclerView.ViewHolder? = null
        when (p1) {
            0 -> {
                view = LayoutInflater.from(context).inflate(R.layout.mult_list_item_1, p0, false)
                holder = MyHolder(view)
            }

            1 -> {
                view = LayoutInflater.from(context).inflate(R.layout.mult_list_item_2, p0, false)
                holder = MyHolderSecond(view)
            }
        }
        return holder!!
    }

    override fun getItemCount(): Int {
        return mListInfo.size
    }

    override fun getItemViewType(position: Int): Int {
        var type = mListInfo.get(position).type
        when (type) {
            1 -> return 0
            2 -> return 1
        }
        return 0
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_name: TextView = itemView.findViewById(R.id.user_name)
        var delete_icon: ImageView = itemView.findViewById(R.id.delete_icon)
    }

    class MyHolderSecond(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_name: TextView = itemView.findViewById(R.id.user_name)
        var delete_icon: ImageView = itemView.findViewById(R.id.delete_icon)
        var user_pwd: TextView = itemView.findViewById(R.id.user_pwd)
        var user_age: TextView = itemView.findViewById(R.id.user_age)
    }

    interface MyInter {    //自定义的接口（测试接口方式的使用）
        fun onclick(p0: Int)
    }

    fun setOnClickListener(inter: MyInter) {
        this.myInter = inter
    }
}
