package com.example.kotlin.tablayout

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @author zcm
 * @create 2018/11/01
 * @Describe
 */
class PageAdapter (var mList : List<Fragment>,fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return mList[p0]
    }

    override fun getCount(): Int {
        return mList.size
    }

}