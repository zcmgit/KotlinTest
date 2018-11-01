package com.example.kotlin.tablayout

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.R
import com.example.kotlin.tablayout.fragment.TabHomeFragment
import com.example.kotlin.tablayout.fragment.TabMeFragment
import com.example.kotlin.tablayout.fragment.TabShopFragment
import kotlinx.android.synthetic.main.tab_layout_activity.*

/**
 * @author zcm
 * @create 2018/11/01
 * @Describe
 */
class TablayoutActivity : AppCompatActivity() , TabLayout.OnTabSelectedListener{


    private var tabLayout : TabLayout ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_layout_activity)
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        setTab()
        setItem()
    }

    fun setTab(){
        val tab1:TabHomeFragment = TabHomeFragment.newInstance("tab_home_fragment")
        val tab2:TabShopFragment = TabShopFragment.newInstance("tab_shop_fragment")
        var tab3:TabMeFragment = TabMeFragment.newInstance("tab_me_fragment")
        var list = listOf <Fragment> (tab1, tab2,tab3)
        view_pager.adapter = PageAdapter(list, supportFragmentManager) //让tab和viewpager关联起来
        tab_layout!!.setupWithViewPager(view_pager)
    }

    fun setItem(){
        val list = listOf < String > ("Home", "Shop","Me")
        tab_layout.getTabAt(0) ?.text = list[0]
        tab_layout.getTabAt(1) ?.text = list[1]
        tab_layout.getTabAt(2) ?.text = list[2]
    }
    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        view_pager.currentItem = p0!!.position
    }


}