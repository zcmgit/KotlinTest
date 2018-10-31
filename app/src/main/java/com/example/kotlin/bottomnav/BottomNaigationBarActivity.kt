package com.example.kotlin.bottomnav

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.example.kotlin.R
import com.example.kotlin.bottomnav.fragment.HomeFragment
import com.example.kotlin.bottomnav.fragment.MeFragment
import com.example.kotlin.bottomnav.fragment.ShopFragment
import kotlinx.android.synthetic.main.bottom_navigation_bar_actvity.*
import android.view.Gravity
import com.ashokvarma.bottomnavigation.ShapeBadgeItem.SHAPE_OVAL
import com.ashokvarma.bottomnavigation.ShapeBadgeItem.SHAPE_STAR_3_VERTICES


/**
 * @author zcm
 * @create 2018/10/31
 * @Describe
 */
class BottomNaigationBarActivity : AppCompatActivity(),BottomNavigationBar.OnTabSelectedListener{

    private lateinit var homeFragment: HomeFragment
    private lateinit var meFragment: MeFragment
    private lateinit var shopFragment: ShopFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation_bar_actvity)

        initView()
    }
    private fun initView(){
        var numberBadgeItem = TextBadgeItem()
        var shapeBadgeItem = ShapeBadgeItem()
        bottom_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        bottom_bar.setMode(BottomNavigationBar.MODE_FIXED)
        bottom_bar.addItem(BottomNavigationItem(R.mipmap.bottom_home,"首页").setActiveColorResource(R.color.colorPrimaryDark).setInActiveColor(Color.GRAY))
                .addItem(BottomNavigationItem(R.mipmap.bottom_shop,"商城").setActiveColorResource(R.color.colorPrimaryDark).setInActiveColor(Color.GRAY).setBadgeItem(shapeBadgeItem))
                .addItem(BottomNavigationItem(R.mipmap.bottom_me,"我的").setActiveColorResource(R.color.colorPrimaryDark).setInActiveColor(Color.GRAY)
                        .setBadgeItem(numberBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise()
        bottom_bar.setTabSelectedListener(this)
        onTabSelected(0)

        numberBadgeItem.setText("9") //显示的文本
                .setBorderWidth(10) //border宽度px
                .setBackgroundColorResource(R.color.colorAccent) //背景色，资源文件获取
                .setBorderColorResource(R.color.colorAccent) //border颜色，资源文件获取
                .setTextColor(Color.WHITE) //文本颜色，资源文件获取
                .setAnimationDuration(30) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.RIGHT or Gravity.TOP) //位置，默认右上角
                .setHideOnSelect(true) //true：当选中状态时消失，非选中状态显示,moren false

    }
    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabSelected(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            when(position){
                0 -> {
                    homeFragment = HomeFragment.newInstance("home_fragment")
                    replace(R.id.fragment_layout,homeFragment)
                }
                1 -> {
                    shopFragment = ShopFragment.newInstance("shop_fragment")
                    replace(R.id.fragment_layout,shopFragment)
                }
                2 -> {
                    meFragment = MeFragment.newInstance("me_fragment")
                    replace(R.id.fragment_layout,meFragment)
                }
            }
        }.commitAllowingStateLoss()
    }

}