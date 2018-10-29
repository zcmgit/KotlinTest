package com.example.kotlin.drawer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.R
import com.example.kotlin.drawer.fragment.Fragment1
import com.example.kotlin.drawer.fragment.Fragment2
import com.example.kotlin.drawer.fragment.Fragment3
import kotlinx.android.synthetic.main.drawer_main.*
import kotlinx.android.synthetic.main.drawer_toolbar_layout.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/10/29
 * @Describe
 */
class DrawerMainActivity : AppCompatActivity(){

    private var fragment1 : Fragment1 ? = null
    private var fragment2 : Fragment2 ? = null
    private var fragment3 : Fragment3 ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_main)

        toolbar.title = ""
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        tv_bar_title.text = "item1"

        val mToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(mToggle)
        /*同步drawerlayout的状态*/
        mToggle.syncState()


        /*设置监听器*/
        setListener()

        initFragment(savedInstanceState)

        return_text.setOnClickListener {
            finish()
        }
    }
    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is Fragment1) {
                    fragment1 = item
                }
                if (item is Fragment2) {
                    fragment2 = item
                }
                if (item is Fragment3) {
                    fragment3 = item
                }
            }
        } else {
            fragment1 = Fragment1()
            fragment2 = Fragment2()
            fragment3 = Fragment3()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, fragment1!!)
            fragmentTrans.add(R.id.fl_content, fragment2!!)
            fragmentTrans.add(R.id.fl_content, fragment3!!)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(this!!.fragment1!!)
                .hide(this!!.fragment2!!)
                .hide(this!!.fragment3!!)
                .commit()
    }

    /*设置监听器*/
    private fun setListener() {
        navigetion_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item1 -> {
                    tv_bar_title.text = "Item1"
                    supportFragmentManager.beginTransaction().show(this!!.fragment1!!)
                            .hide(this!!.fragment2!!)
                            .hide(this!!.fragment3!!)
                            .commit()
                    toast("item1")
                }
                R.id.nav_item2 -> {
                    tv_bar_title.text = "Item2"
                    supportFragmentManager.beginTransaction().show(this!!.fragment2!!)
                            .hide(this!!.fragment1!!)
                            .hide(this!!.fragment3!!)
                            .commit()
                    toast("item2")
                }
                R.id.nav_item3 -> {
                    tv_bar_title.text = "Item3"
                    supportFragmentManager.beginTransaction().show(this!!.fragment3!!)
                            .hide(this!!.fragment1!!)
                            .hide(this!!.fragment2!!)
                            .commit()
                    toast("item3")
                }
                R.id.nav_item_stockings -> {
                    tv_bar_title.text = "nav_item_stockings"
                    toast("nav_item_stockings")
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

}